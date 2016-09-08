package com.despegar.interceptor

import com.despegar.interceptor.model.Request
import com.despegar.interceptor.model.Response
import scala.util.Try
import com.despegar.interceptor.model.SuccessResponse
import com.despegar.interceptor.model.ErrorResponse

trait Operation[REQ <: Request, RES <: SuccessResponse] {
  
  def execute(request :REQ) :Try[Either[ErrorResponse, RES]]
  
}