//package com.aeon
//
//import akka.actor.ActorSystem
//import akka.stream.ActorMaterializer
//import akka.stream.scaladsl.{Flow, Sink, Source}
//import akka.{Done, NotUsed}
//
//import scala.concurrent.Future
//
//object StreamRunner {
//
//  implicit val system = ActorSystem("stream-runner")
//  implicit val materializer = ActorMaterializer()
//  implicit val dispatcher = system.dispatcher
//
//  def main(args: Array[String]): Unit = {
//
//    val numberSource: Source[Int, NotUsed] = Source(1 to 100)
//    val doubleFlow: Flow[Int, Int, NotUsed] = Flow[Int].mapAsync(2)(i => Future {
//      i * 2
//    })
//    val printSink: Sink[Int, Future[Done]] = Sink.foreach(println)
//
//    val futureDone = numberSource
//      .via(doubleFlow)
//      .runWith(printSink)
//
//    futureDone.onComplete(_=>system.terminate())
//
//  }
//}
