package com.aeon.exceptions

case class ResourceNotFound(message: String) extends RuntimeException(message)
