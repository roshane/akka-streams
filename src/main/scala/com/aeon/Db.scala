package com.aeon

import com.mongodb.{ConnectionString, MongoClientSettings}
import org.mongodb.scala.{MongoClient, MongoDatabase}

class Db(config: AppConfig) {

  private val connectionString = s"mongodb://${config.host}:${config.port}"

  private val settings = MongoClientSettings.builder()
    .applyConnectionString(new ConnectionString(connectionString))
    .codecRegistry(MongoClient.DEFAULT_CODEC_REGISTRY)
    .build()

  private val mongoClient = MongoClient(settings)

  val studentDb: MongoDatabase = mongoClient.getDatabase(config.db)
}
