package com.aeon.api

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.ExceptionHandler
import akka.http.scaladsl.server.directives.RouteDirectives._
import com.aeon.exceptions.ResourceNotFound
import com.typesafe.scalalogging.LazyLogging

trait ApiExceptionHandler extends LazyLogging {

  protected val resourceNotFoundExceptionHandler = ExceptionHandler {
    case ex: ResourceNotFound => logger.error("Error", ex)
      complete(StatusCodes.NotFound)
  }

}
