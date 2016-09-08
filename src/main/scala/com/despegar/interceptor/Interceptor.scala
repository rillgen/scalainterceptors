package com.despegar.interceptor

import com.despegar.interceptor.model.Request
import com.despegar.interceptor.model.Response
import scala.util.Try
import com.despegar.interceptor.model.SuccessResponse
import com.despegar.interceptor.model.ErrorResponse

trait Interceptor[REQ <: Request, RES <: SuccessResponse] {
  
  def processRequest(request :REQ) :Try[REQ]
    
  def processResponse(request :REQ, response :Try[Either[ErrorResponse, RES]]) :Try[Either[ErrorResponse, RES]]
  
}