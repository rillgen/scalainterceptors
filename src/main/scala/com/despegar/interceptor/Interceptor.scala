package com.despegar.interceptor

import com.despegar.interceptor.model.Request
import com.despegar.interceptor.model.Response
import scala.util.Try

trait Interceptor[REQ <: Request, RES <: Response] {
  
  def processRequest(request :Try[REQ]) :Try[REQ]
    
  def processResponse(request :REQ, response :Try[RES]) :Try[RES]
  
}