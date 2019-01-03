package com.aeon

import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfter, FunSuite, Matchers}

class AppConfigSpec extends FunSuite with Matchers with BeforeAndAfter with MockitoSugar {

  private val config = new AppConfig

  test("mockConfig should successfully load configurations") {
    config.db shouldBe "develop"
    config.host shouldBe "localhost"
    config.port shouldBe 27017
  }

}
