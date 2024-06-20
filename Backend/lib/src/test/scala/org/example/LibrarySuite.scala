package org.example

import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.junit.JUnitRunner
import org.junit.runner.RunWith

@RunWith(classOf[JUnitRunner])
class LibrarySuite extends AnyFunSuite {

  test("Example test") {
    assert(1 + 1 == 2)
  }
}
