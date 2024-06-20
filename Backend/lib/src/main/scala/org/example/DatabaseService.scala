package org.example

import java.time.LocalDateTime
import slick.jdbc.PostgresProfile.api._
import com.typesafe.config.ConfigFactory
import scala.concurrent.Await
import scala.concurrent.duration._
import org.slf4j.LoggerFactory

object Main extends App {
  val logger = LoggerFactory.getLogger(this.getClass)

  val config = ConfigFactory.load()
  val dbConfig = config.getConfig("db")
  val dbUrl = dbConfig.getString("url")
  val dbUser = dbConfig.getString("user")
  val dbPassword = dbConfig.getString("password")
  val dbDriver = dbConfig.getString("driver")

  val db = Database.forURL(dbUrl, user = dbUser, password = dbPassword, driver = dbDriver)
  val summaryTable = TableQuery[SummaryTable]

  try {
    // Initialize the schema
    val setup = DBIO.seq(
      summaryTable.schema.createIfNotExists
    )
    Await.result(db.run(setup), 10.seconds)
    logger.info("Schema created or already exists.")

    // Insert a test record
    val testSummary = Summary("00:11:22:33:44:55", LocalDateTime.now(), "http://example.com", "Test summary")
    val insertAction = (summaryTable returning summaryTable.map(_.id)) += testSummary
    val insertResult = Await.result(db.run(insertAction), 10.seconds)
    logger.info(s"Test record inserted with ID: $insertResult")

    // Query the test record
    val queryAction = summaryTable.filter(_.id === insertResult).result.headOption
    val queryResult = Await.result(db.run(queryAction), 10.seconds)
    queryResult match {
      case Some(summary) => logger.info(s"Query successful: $summary")
      case None => logger.warn("No record found.")
    }

    // Update the test record
    val updateAction = summaryTable.filter(_.id === insertResult).map(_.summary).update("Updated summary")
    Await.result(db.run(updateAction), 10.seconds)
    logger.info(s"Record updated.")

    // Delete the test record
    val deleteAction = summaryTable.filter(_.id === insertResult).delete
    Await.result(db.run(deleteAction), 10.seconds)
    logger.info(s"Record deleted.")
  } catch {
    case e: Exception =>
      logger.error(s"Database operation failed: ${e.getMessage}")
  } finally {
    db.close()
  }
}
