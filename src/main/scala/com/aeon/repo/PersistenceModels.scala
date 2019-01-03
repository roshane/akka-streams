package com.aeon.repo

object PersistenceModels {

  final case class Student(id: Int, name: String, scores: Seq[Score])

  final case class Score(score: Double, `type`: String)

}
