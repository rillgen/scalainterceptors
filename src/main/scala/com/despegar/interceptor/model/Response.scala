package com.despegar.interceptor.model

class Response

class ErrorResponse extends Response

abstract class SuccessResponse extends Response

class HappyApiResponse extends SuccessResponse