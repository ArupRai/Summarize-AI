// src/main/scala/com/example/MyScalaLib.scala
package com.example

import scalaj.http.Http

class MyScalaLib {
  def callPythonApi(input: String): String = {
    val endpoint = s"http://127.0.0.1:8000/process?input=$input"
    val response = Http(endpoint).asString
    response.body
  }

  def processRequest(input: String): String = {
    val apiResult = callPythonApi(input)
    s"API Result: $apiResult"
  }
}

object EntryPoint {
  def main(args: Array[String]): Unit = {
    val scalaLib = new MyScalaLib()
    val server = new py4j.GatewayServer(scalaLib)
    server.start()
    println("Gateway Server Started")
  }
}
