package com.aeon.json

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import com.aeon.repo.PersistenceModels.{Score, Student}
import spray.json._

trait MessageJsonProtocol extends SprayJsonSupport with DefaultJsonProtocol {

  implicit val scoreJsonFormat: RootJsonFormat[Score] = jsonFormat2(Score)
  implicit val studentJsonFormat: RootJsonFormat[Student] = jsonFormat3(Student)
}
