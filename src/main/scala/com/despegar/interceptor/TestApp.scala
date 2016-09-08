package com.despegar.interceptor

import com.despegar.interceptor.model.Request
import com.despegar.interceptor.model.Response
import scala.util.Try
import scala.util.Success
import com.despegar.interceptor.model.SuccessResponse
import com.despegar.interceptor.model.ErrorResponse
import com.despegar.interceptor.model.HappyApiResponse

object TestApp extends App {
  
  println("Testing execution order!")
  
  val interceptors = ChattyInterceptor("1")::ChattyInterceptor("2")::ChattyInterceptor("3")::Nil
  val operation = ChattyOperation("myop")
  
  val intercepted = new InterceptedOperation(operation, interceptors)
  
  intercepted.execute(new Request)
  
}

case class ChattyOperation(name :String) extends Operation[Request, HappyApiResponse] {
  
  override def execute(request :Request) :Try[Either[ErrorResponse, HappyApiResponse]] = {
    println(s"${name} on execute operation!")    
    Success(Right(new HappyApiResponse))
  }
  
}

case class ChattyInterceptor(name :String) extends Interceptor[Request, HappyApiResponse] {
  
    override def processRequest(request :Request) :Try[Request] = {
      println(s"${name} on processRequest!")
      Success(request)
    }
    
    override def processResponse(request :Request, response :Try[Either[ErrorResponse, HappyApiResponse]]) :Try[Either[ErrorResponse, HappyApiResponse]] = {
      println(s"${name} on processResponse!")
      response
    }
}