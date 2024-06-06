package example

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import scala.concurrent.Future
import scala.concurrent.duration._
import scala.util.{Failure, Success}

object ScalaClient extends App {
  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val uri = "http://www.google.com"
  val request = HttpRequest(uri = uri)

  val responseFuture: Future[HttpResponse] = Http().singleRequest(request)

  responseFuture.onComplete {
    case Success(response) =>
      response.entity.toStrict(1000.millis).map(_.data.utf8String).foreach(println)
    case Failure(exception) =>
      println(s"Request failed: $exception")
  }
}
