package com.aeon

import com.typesafe.config.ConfigFactory

class AppConfig {

  private val config = ConfigFactory.load()

  private val mongoConfig = config.getConfig("mongo")
  val host: String = mongoConfig.getString("host")
  val port: Int = mongoConfig.getInt("port")
  val db: String = mongoConfig.getString("db")

}
