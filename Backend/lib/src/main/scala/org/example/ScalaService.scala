package org.example

import org.springframework.stereotype.Component
import sttp.client3._
import sttp.client3.circe._
import io.circe.generic.auto._
import io.circe.parser._
import scala.concurrent.Await
import scala.concurrent.duration._
import scala.util.{Failure, Success, Try}
import org.slf4j.LoggerFactory


case class SummaryRequest(webapp_link: String)
case class SummaryResponse(summary: String)

@Component
class ScalaService {
  private val logger = LoggerFactory.getLogger(classOf[ScalaService])

  def summarize(url: String): String = {
    implicit val backend = HttpURLConnectionBackend()
//    return s"HARE KRISHNA ${url}"
    val request = basicRequest
      .post(uri"http://fastapi-service:8000/summarize")
      .body(SummaryRequest(webapp_link = url))
      .response(asString) // Use asString to receive raw response body

    val response = request.send(backend)
    logger.info(s"Raw response body: ${response.body}")

    response.body match {
      case Right(body) =>
        // Attempt to deserialize body into SummaryResponse
        decode[SummaryResponse](body) match {
          case Right(summaryResponse) => summaryResponse.summary
          case Left(error) =>
            logger.error(s"Failed to parse JSON response: $error")
            throw new RuntimeException(s"Failed to parse JSON response: $error")
        }
      case Left(error) =>
        logger.error(s"Failed to receive response: $error")
        throw new RuntimeException(s"Failed to receive response: $error")
    }
  }

  def getHistory(): List[String] = {
    // Your implementation
    List("History1", "History2")
  }
}
