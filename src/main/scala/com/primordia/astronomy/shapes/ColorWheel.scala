package com.primordia.astronomy.shapes

import com.jogamp.opengl.{GL, GL2}

class ColorWheel {
  private val divisors = (1 to 360).toList.filter( n => 360 % n == 0 ).reverse
  private var petals: Int = (divisors.length) / 2;

  def draw(gl: GL2, step: Float, width: Int, height: Int): Unit = {
    gl.glBegin(GL.GL_LINES)

    for (i <- 0 to 360 by divisors(petals)) {

      val angle = Math.toRadians(step + i)
      val r = height.toDouble / 2.0
      val hue = i / 360f
      val color = java.awt.Color.getHSBColor(hue, 1.0f, 1.0f)
      gl.glColor3f(color.getRed.toFloat / 255, color.getGreen.toFloat / 255, color.getBlue.toFloat / 255)
      gl.glVertex3d(0, 0, 0)
      gl.glVertex3d(r * Math.cos(angle), r * Math.sin(angle), 0)
    }

    gl.glEnd()

  }

  def setPetals(petals: Int): Unit = {
    this.petals = Math.max(0, Math.min(divisors.length, petals))
  }

  def getPetals: Int = {
    petals
  }

}
