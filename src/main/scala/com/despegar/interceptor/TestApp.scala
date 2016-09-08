package com.despegar.interceptor

import com.despegar.interceptor.model.Request
import com.despegar.interceptor.model.Response
import scala.util.Try
import scala.util.Success

object TestApp extends App {
  
  println("Testing execution order!")
  
  val interceptors = ChattyInterceptor("1")::ChattyInterceptor("2")::ChattyInterceptor("3")::Nil
  val operation = ChattyOperation("myop")
  
  val intercepted = new InterceptedOperation(operation, interceptors)
  
  intercepted.execute(new Request)
  
}

case class ChattyOperation(name :String) extends Operation[Request, Response] {
  
  override def execute(request :Request) :Try[Response] = {
    println(s"${name} on execute operation!")    
    Success(new Response)
  }
  
}

case class ChattyInterceptor(name :String) extends Interceptor[Request, Response] {
  
    override def processRequest(request :Request) :Try[Request] = {
      println(s"${name} on processRequest!")
      Success(request)
    }
    
    override def processResponse(request :Request, response :Try[Response]) :Try[Response] = {
      println(s"${name} on processResponse!")
      response
    }
}