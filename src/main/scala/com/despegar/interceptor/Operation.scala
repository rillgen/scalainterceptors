package com.despegar.interceptor

import com.despegar.interceptor.model.Request
import com.despegar.interceptor.model.Response
import scala.util.Try

trait Operation[REQ <: Request, RES <: Response] {
  
  def execute(request :REQ) :Try[RES]
  
}