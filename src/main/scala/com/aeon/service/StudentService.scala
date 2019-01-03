package com.aeon.service

import com.aeon.api.Constants.Size
import com.aeon.exceptions.ResourceNotFound
import com.aeon.repo.PersistenceModels.{Score, Student}
import com.aeon.repo.StudentRepository
import com.typesafe.scalalogging.LazyLogging
import org.bson.BsonArray
import org.mongodb.scala.Document
import org.mongodb.scala.result.DeleteResult

import scala.collection.JavaConverters._
import scala.concurrent.{ExecutionContext, Future}

class StudentService(repository: StudentRepository)(implicit ec: ExecutionContext) extends LazyLogging {

  def findOne(id: Int): Future[Student] = {
    val futureStudent = repository.findOne(id).collect({
      case Some(doc) => convertToStudent(doc)
      case _ => throw ResourceNotFound(s"Student with id $id not found")
    })
    logger.info("Enumeration result size {}", Size.withName("Large"))
    futureStudent
  }

  private def convertToStudent(document: Document) = {
    val name = document.getString("name")
    val id = document.getInteger("_id")
    val scores = document.get[BsonArray]("scores").fold(Seq.empty[Score])(
      (bsonArray: BsonArray) =>
        bsonArray.getValues.asScala.map(d => mapToScore(d.asDocument())))
    Student(id, name, scores)
  }

  private def mapToScore(document: Document) = {
    val `type` = document.getString("type")
    val score = document.getDouble("score")
    Score(score, `type`)
  }

  def findAll(skip: Int, limit: Int): Future[Seq[Student]] = {
    repository.findAll(skip, limit)
      .collect({
        case list: Seq[Document] => list.map(convertToStudent)
      })
  }

  def save(score: Score) = ???

  def delete(id: Int): Future[Boolean] = {
    repository.delete(id)
      .collect({
        case result: DeleteResult => result.wasAcknowledged()
      })
  }
}
