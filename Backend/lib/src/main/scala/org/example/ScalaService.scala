package org.example

import org.springframework.stereotype.Component
import org.example.HistoryEntry
import sttp.client3._
import sttp.client3.circe._
import io.circe.generic.auto._
import io.circe.parser._
import org.slf4j.LoggerFactory
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import scala.jdk.CollectionConverters._

case class SummaryRequest(webapp_link: String)
case class SummaryResponse(summary: String)

@Component
class ScalaService {
  private val logger = LoggerFactory.getLogger(classOf[ScalaService])

  def summarize(url: String): String = {
    val existingSummary = DatabaseService.readSummaryByUrl(url)
    existingSummary match {
      case Some((_, _, summary)) =>
        logger.info(s"Found existing summary for URL: $url")
        summary
      case None =>
        logger.info(s"No existing summary found for URL: $url. Generating new summary.")
        implicit val backend = HttpURLConnectionBackend()
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
              case Right(summaryResponse) =>
                // Store new summary in the database
                val currentDate: String = java.time.LocalDate.now().toString
                val currentTime: String = java.time.LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")).toString
                DatabaseService.createSummary(currentDate, currentTime, url, summaryResponse.summary)
                summaryResponse.summary
              case Left(error) =>
                logger.error(s"Failed to parse JSON response: $error")
                throw new RuntimeException(s"Failed to parse JSON response: $error")
            }
          case Left(error) =>
            logger.error(s"Failed to receive response: $error")
            throw new RuntimeException(s"Failed to receive response: $error")
        }
    }
  }

  def getHistory(): java.util.List[HistoryEntry] = {
    val history = DatabaseService.retrieveHistory()
    logger.debug(s"Retrieved history: $history")
    history.map { case (date, time, url, summary) =>
      new HistoryEntry() {
        setDate(date)
        setTime(time)
        setUrl(url)
        setSummary(summary)
      }
    }.asJava
  }
}
