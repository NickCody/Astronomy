package com.primordia.astronomy.shapes

import com.jogamp.opengl.{GL, GL2, GL2GL3, GL3}

class ColorWheel {
  private val divisors = (1 to 360).toList.filter( n => 360 % n == 0 ).reverse
  private var petals: Int = (divisors.length) / 2;
  private val petal_angle = 0.01f

  def draw(gl: GL2, step: Float, width: Int, height: Int): Unit = {

    for (i <- 0 to 360 by divisors(petals)) {

      val angle = Math.toRadians(step + i)
      val r = height.toDouble / 4.0
      val hue = i / 360f
      val color = java.awt.Color.getHSBColor(hue, 1.0f, 1.0f)
      gl.glBegin(GL.GL_TRIANGLE_STRIP)
      gl.glColor3f(color.getRed.toFloat / 255, color.getGreen.toFloat / 255, color.getBlue.toFloat / 255)
      gl.glVertex3d(0, 0, 0)
      gl.glVertex3d(r * Math.cos(angle-petal_angle/2f), r * Math.sin(angle-petal_angle/2f), 0)
      gl.glVertex3d(r * Math.cos(angle+petal_angle/2f), r * Math.sin(angle+petal_angle/2f), 0)
      gl.glEnd()
    }


  }

  def setPetals(petals: Int): Unit = {
    this.petals = Math.max(0, Math.min(divisors.length-1, petals))
  }

  def getPetals: Int = {
    petals
  }

}
