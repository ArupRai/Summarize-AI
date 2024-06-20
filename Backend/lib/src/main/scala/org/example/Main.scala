package org.example

object Main {
  def main(args: Array[String]): Unit = {
    println("Starting the Database Service")

    // Example usage
    DatabaseService.createSummary("00:1B:44:11:3A:B7", "2024-06-19", "12:00:00", "http://example.com", "This is a summary")
    val summary = DatabaseService.readSummary("00:1B:44:11:3A:B7")
    println(s"Read summary: $summary")

    DatabaseService.updateSummary("00:1B:44:11:3A:B7", "This is an updated summary")
    val updatedSummary = DatabaseService.readSummary("00:1B:44:11:3A:B7")
    println(s"Updated summary: $updatedSummary")

//    DatabaseService.deleteSummary("00:1B:44:11:3A:B7")
//    val deletedSummary = DatabaseService.readSummary("00:1B:44:11:3A:B7")
//    println(s"Deleted summary: $deletedSummary")
  }
}
