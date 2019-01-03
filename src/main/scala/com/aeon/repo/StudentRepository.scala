package com.aeon.repo

import com.aeon.Db
import org.mongodb.scala.Document
import org.mongodb.scala.model.Filters._
import org.mongodb.scala.model.Sorts._
import org.mongodb.scala.result.DeleteResult

import scala.concurrent.{ExecutionContext, Future}
import scala.language.postfixOps

class StudentRepository(db: Db)(implicit executionContext: ExecutionContext) {

  private val studentDb = db.studentDb
  private val col = studentDb.getCollection("students")

  def findOne(id: Int): Future[Option[Document]] = {
    val document = col.find(equal("_id", id)).first()
    document.toFutureOption()
  }

  def findAll(skip: Int, limit: Int): Future[Seq[Document]] = {
    col.find()
      .sort(orderBy(ascending("_id")))
      .skip(skip)
      .limit(limit)
      .toFuture()
  }

  def delete(id: Int): Future[DeleteResult] = {
    col.deleteOne(equal("_id", id))
      .toFuture()
  }
}
