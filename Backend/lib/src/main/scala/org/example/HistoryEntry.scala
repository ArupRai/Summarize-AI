package org.example


class HistoryEntry {

  // Default constructor
  private var date: Nothing = null
  private var time: Nothing = null
  private var url: Nothing = null
  private var summary: Nothing = null

  def this(date: Nothing, time: Nothing, url: Nothing, summary: Nothing) {
    this()
    this.date = date
    this.time = time
    this.url = url
    this.summary = summary
  }

  // Getters and setters
  def getDate: Nothing = date

  def setDate(date: Nothing): Unit = {
    this.date = date
  }

  def getTime: Nothing = time

  def setTime(time: Nothing): Unit = {
    this.time = time
  }

  def getUrl: Nothing = url

  def setUrl(url: Nothing): Unit = {
    this.url = url
  }

  def getSummary: Nothing = summary

  def setSummary(summary: Nothing): Unit = {
    this.summary = summary
  }
}
