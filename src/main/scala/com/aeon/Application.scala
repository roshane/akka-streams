package com.aeon

import akka.NotUsed
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse, StatusCodes}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Flow
import com.aeon.api.ApiExceptionHandler
import com.aeon.json.MessageJsonProtocol
import com.aeon.repo.StudentRepository
import com.aeon.service.StudentService

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContext}
import scala.io.StdIn

object Application extends MessageJsonProtocol with ApiExceptionHandler {

  implicit val system: ActorSystem = ActorSystem("application-http-actor-system")
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext: ExecutionContext = system.dispatcher

  private val config = new AppConfig
  private val db = new Db(config)
  private val studentRepository = new StudentRepository(db)
  private val studentService = new StudentService(studentRepository)

  def main(args: Array[String]): Unit = {
    val eventualStudent = studentService.findOne(12)
    val result = Await.result(eventualStudent, 1 second)
    val bindingFuture = Http().bindAndHandle(studentRoutes, "localhost", 8080)
    println(s"server started on http://localhost:8080")

    println(studentRoutes)

    StdIn.readLine("Hit Enter to terminate...")
    bindingFuture.flatMap(binding => binding.unbind())
      .onComplete(_ => system.terminate())
  }

  private val studentRoutes: Flow[HttpRequest, HttpResponse, NotUsed] = {
    path("students") {
      get {
        parameters('skip.as[Int] ? 0, 'limit.as[Int] ? 10) { (skip, limit) =>
          complete {
            studentService.findAll(skip, limit)
          }
        } ~ path(IntNumber) { id =>
          handleExceptions(resourceNotFoundExceptionHandler) {
            complete {
              studentService.findOne(id)
            }
          }
        }
      } ~ delete {
        path(IntNumber) { id =>
          complete {
            studentService.delete(id)
            StatusCodes.NoContent
          }
        }
      }
    }
}

}


