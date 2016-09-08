package com.despegar.interceptor

import scala.util.Failure
import scala.util.Try

import com.despegar.interceptor.model.Request
import com.despegar.interceptor.model.Response
import scala.util.Success

class InterceptedOperation[REQ <: Request, RES <: Response](operation: Operation[REQ, RES], interceptors: List[Interceptor[REQ, RES]]) extends Operation[REQ, RES] {
  override def execute(request: REQ): Try[RES] = {
    interceptors.foldLeft(Try(request))((request, interceptor) => interceptor.processRequest(request)) match {
      case Success(request) => interceptors.foldRight(operation.execute(request))((interceptor, response) => interceptor.processResponse(request, response))
      case Failure(ex)      => Failure(ex)
    }
  }
}