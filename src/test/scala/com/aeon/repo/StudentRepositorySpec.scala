package com.aeon.repo

import com.aeon.{AppConfig, Db}
import org.scalatest.{FunSuite, Matchers}
import scala.concurrent.ExecutionContext
import ExecutionContext.Implicits.global

class StudentRepositorySpec extends FunSuite with Matchers {

  val repository = new StudentRepository(new Db(new AppConfig))

  test("studentRepository should successfully find first score") {
    val maybeScore = repository.findOne(1)
//    maybeScore.isDefined shouldBe false
    //    maybeScore.get.`type` shouldBe "hello world"
  }
}
