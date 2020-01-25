package com.primordia.astronomy.simple

import scala.annotation.tailrec

object SquareRoot {

  val ABOUT_ZERO = 10E-10

  def main(args: Array[String]): Unit = {

    var n = 1.0
    while(n < 1E10) {
      val rs = Math.sqrt(n)
      var it: Int = 1
      var fs = sqrt(n.toDouble, it)
      while( Math.abs(rs-fs) > ABOUT_ZERO) {
        it = it + 1
        fs = sqrt(n.toDouble, it)
      }
      println(f"For $n, required $it iterations")
      n = n * 1.1
    }

  }

  def sqrt(N: Double, iterations: Int): Double = {
    var n: Double = N/2.0

    for (i <- 1 to iterations) {
      n = (n + N/n)/2
    }

    n
  }

  def fsqrt(N: Double, iterations: Int): Double = {

    @tailrec
    def _sqrt(N: Double, n: Double, i: Int): Double = {
      if (i == 0)
        n
      else {
        val _n = (n + N / n) / 2
        _sqrt(N, _n, i - 1)
      }
    }

    _sqrt(N, N/2.0, iterations)
  }

}

