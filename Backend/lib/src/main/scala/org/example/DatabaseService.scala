package org.example

import java.sql.{Connection, DriverManager, ResultSet, SQLException, Statement}

object DatabaseService {
  val url = "jdbc:postgresql://postgres:5432/SummarizeAI-DB"
  val username = "arup"
  val password = "aruprai842"

  // Function to check if the Summary table exists and create it if not
  def setupDatabase(): Unit = {
    var connection: Connection = null
    try {
      connection = DriverManager.getConnection(url, username, password)
      val metaData = connection.getMetaData
      val tables = metaData.getTables(null, null, "Summary", Array("TABLE"))
      if (!tables.next()) {
        // Table does not exist, create it
        val statement = connection.createStatement()
        statement.executeUpdate(
          """
            |CREATE TABLE IF NOT EXISTS Summary (
            |    id SERIAL PRIMARY KEY,
            |    date_of_search DATE NOT NULL,
            |    time_of_search TIME NOT NULL,
            |    webapp_url VARCHAR(255) NOT NULL,
            |    summary TEXT NOT NULL
            |)
          """.stripMargin)
        statement.close()
        println("Created Summary table.")
      }
    } catch {
      case e: SQLException =>
        println("Error checking or creating Summary table: " + e.getMessage)
    } finally {
      if (connection != null) {
        connection.close()
      }
    }
  }

  // Function to get database connection
  def getConnection(): Connection = {
    DriverManager.getConnection(url, username, password)
  }

  // Function to create a summary record
  def createSummary(dateOfSearch: String, timeOfSearch: String, webappUrl: String, summary: String): Unit = {
    val connection = getConnection()
    val statement = connection.prepareStatement(
      "INSERT INTO Summary (date_of_search, time_of_search, webapp_url, summary) VALUES (?, ?, ?, ?)"
    )
    statement.setDate(1, java.sql.Date.valueOf(dateOfSearch))
    statement.setTime(2, java.sql.Time.valueOf(timeOfSearch))
    statement.setString(3, webappUrl)
    statement.setString(4, summary)
    statement.executeUpdate()
    statement.close()
    connection.close()
  }

  // Function to read a summary by webapp_url
  def readSummaryByUrl(webappUrl: String): Option[(String, String, String)] = {
    val connection = getConnection()
    val statement = connection.prepareStatement(
      "SELECT date_of_search, time_of_search, summary FROM Summary WHERE webapp_url = ?"
    )
    statement.setString(1, webappUrl)
    val resultSet = statement.executeQuery()
    val result = if (resultSet.next()) {
      Some(
        (
          resultSet.getDate("date_of_search").toString,
          resultSet.getTime("time_of_search").toString,
          resultSet.getString("summary")
        )
      )
    } else {
      None
    }
    resultSet.close()
    statement.close()
    connection.close()
    result
  }

  // Function to update a summary record
  def updateSummary(webappUrl: String, newSummary: String): Unit = {
    val connection = getConnection()
    val statement = connection.prepareStatement(
      "UPDATE Summary SET summary = ? WHERE webapp_url = ?"
    )
    statement.setString(1, newSummary)
    statement.setString(2, webappUrl)
    statement.executeUpdate()
    statement.close()
    connection.close()
  }

  // Function to delete a summary record
  def deleteSummary(webappUrl: String): Unit = {
    val connection = getConnection()
    val statement = connection.prepareStatement(
      "DELETE FROM Summary WHERE webapp_url = ?"
    )
    statement.setString(1, webappUrl)
    statement.executeUpdate()
    statement.close()
    connection.close()
  }

  // Function to retrieve the history of summary entries sorted by date and time
  def retrieveHistory(): List[(String, String, String, String)] = {
    var connection: Connection = null
    var statement: Statement = null
    var resultSet: ResultSet = null

    try {
      connection = getConnection()
      statement = connection.createStatement()
      resultSet = statement.executeQuery(
        "SELECT date_of_search, time_of_search, webapp_url, summary FROM Summary ORDER BY date_of_search DESC, time_of_search DESC"
      )

      val history = Iterator.continually((resultSet, resultSet.next())).takeWhile(_._2).map { case (rs, _) =>
        (
          rs.getDate("date_of_search").toString,
          rs.getTime("time_of_search").toString,
          rs.getString("webapp_url"),
          rs.getString("summary")
        )
      }.toList

      history
    } catch {
      case e: SQLException =>
        println(s"Error retrieving history: ${e.getMessage}")
        List.empty
    } finally {
      if (resultSet != null) resultSet.close()
      if (statement != null) statement.close()
      if (connection != null) connection.close()
    }
  }
}
