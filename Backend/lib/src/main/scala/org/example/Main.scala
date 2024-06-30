package org.example

object Main {
  def main(args: Array[String]): Unit = {
    println("Starting the Database Service")
    // Initialize the database setup on application start
    DatabaseService.setupDatabase()

  }
}
