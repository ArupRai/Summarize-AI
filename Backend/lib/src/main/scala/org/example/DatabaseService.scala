package org.example

import java.sql.{Connection, DriverManager, ResultSet}

object DatabaseService {
  val url = "jdbc:postgresql://postgres:5432/SummarizeAI-DB"
  val username = "arup"
  val password = "aruprai842"

  def getConnection(): Connection = {
    DriverManager.getConnection(url, username, password)
  }

  def createSummary(userMacAddress: String, dateOfSearch: String, timeOfSearch: String, webappUrl: String, summary: String): Unit = {
    val connection = getConnection()
    val statement = connection.prepareStatement(
      "INSERT INTO Summary (user_mac_address, date_of_search, time_of_search, webapp_url, summary) VALUES (?, ?, ?, ?, ?)"
    )
    statement.setString(1, userMacAddress)
    statement.setDate(2, java.sql.Date.valueOf(dateOfSearch))
    statement.setTime(3, java.sql.Time.valueOf(timeOfSearch))
    statement.setString(4, webappUrl)
    statement.setString(5, summary)
    statement.executeUpdate()
    statement.close()
    connection.close()
  }

  def readSummary(userMacAddress: String): Option[(String, String, String, String, String)] = {
    val connection = getConnection()
    val statement = connection.prepareStatement(
      "SELECT * FROM Summary WHERE user_mac_address = ?"
    )
    statement.setString(1, userMacAddress)
    val resultSet = statement.executeQuery()
    val result = if (resultSet.next()) {
      Some(
        (
          resultSet.getString("user_mac_address"),
          resultSet.getDate("date_of_search").toString,
          resultSet.getTime("time_of_search").toString,
          resultSet.getString("webapp_url"),
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

  def updateSummary(userMacAddress: String, newSummary: String): Unit = {
    val connection = getConnection()
    val statement = connection.prepareStatement(
      "UPDATE Summary SET summary = ? WHERE user_mac_address = ?"
    )
    statement.setString(1, newSummary)
    statement.setString(2, userMacAddress)
    statement.executeUpdate()
    statement.close()
    connection.close()
  }

  def deleteSummary(userMacAddress: String): Unit = {
    val connection = getConnection()
    val statement = connection.prepareStatement(
      "DELETE FROM Summary WHERE user_mac_address = ?"
    )
    statement.setString(1, userMacAddress)
    statement.executeUpdate()
    statement.close()
    connection.close()
  }
}
