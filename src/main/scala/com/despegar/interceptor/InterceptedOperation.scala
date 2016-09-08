package com.despegar.interceptor

import scala.util.Failure
import scala.util.Try

import com.despegar.interceptor.model.Request
import com.despegar.interceptor.model.Response
import scala.util.Success
import com.despegar.interceptor.model.ErrorResponse
import com.despegar.interceptor.model.SuccessResponse

class InterceptedOperation[REQ <: Request, RES <: SuccessResponse](operation: Operation[REQ, RES], interceptors: List[Interceptor[REQ, RES]]) extends Operation[REQ, RES] {
  override def execute(request: REQ): Try[Either[ErrorResponse, RES]] = {
    // Request Interceptors
    interceptors.foldLeft(Try(request))((request, interceptor) => request.flatMap(interceptor.processRequest(_)))
      // Response Interceptors
      .flatMap { request => interceptors.foldRight(operation.execute(request))((interceptor, response) => interceptor.processResponse(request, response)) }
  }
}