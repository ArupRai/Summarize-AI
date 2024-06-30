package org.example

class HistoryEntry {
  private var date: String = null
  private var time: String = null
  private var url: String = null
  private var summary: String = null

  def getDate: String = date
  def setDate(date: String): Unit = { this.date = date }

  def getTime: String = time
  def setTime(time: String): Unit = { this.time = time }

  def getUrl: String = url
  def setUrl(url: String): Unit = { this.url = url }

  def getSummary: String = summary
  def setSummary(summary: String): Unit = { this.summary = summary }
}
