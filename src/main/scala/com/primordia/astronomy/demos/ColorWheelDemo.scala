package com.primordia.astronomy.demos

import java.awt.event.{KeyEvent, KeyListener}

import com.jogamp.opengl._
import com.jogamp.opengl.fixedfunc.GLLightingFunc
import com.jogamp.opengl.glu.GLU
import com.primordia.astronomy.base.OglApp

object ColorWheelDemo {
  def main(args: Array[String]): Unit = {
    val colorWheelDemo = new ColorWheelDemo()
  }
}
class ColorWheelDemo extends OglApp("Color Wheel Demo") {
  private var step: Double = 0
  private var step_delta = 0.1
  private var divisors = List(1, 2, 3, 4, 5, 6, 8, 9, 10, 12, 15, 18, 20, 24, 30, 36, 40, 45, 60, 72, 90, 120, 180, 360)
  private var petals = (divisors.length) / 2;

  canvas.addKeyListener(new KeyListener() {

    def keyPressed(e: KeyEvent): Unit = {
      e.getKeyCode match {
        case KeyEvent.VK_UP => petals = Math.min(divisors.length - 1, petals + 1)
        case KeyEvent.VK_DOWN => petals = Math.max(1, petals - 2)
        case KeyEvent.VK_LEFT => step_delta = step_delta / 1.25
        case KeyEvent.VK_RIGHT => step_delta = step_delta * 1.25
      }
    }

    def keyTyped(e: KeyEvent): Unit = {}

    def keyReleased(e: KeyEvent): Unit = {}
  })

  canvas.addGLEventListener(new GLEventListener() {

    override def display(drawable: GLAutoDrawable): Unit = {

      val gl = drawable.getGL.getGL2

      gl.glLoadIdentity()
      gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT)

      val glu = new GLU
      glu.gluPerspective(65f, ratio, 1f, 10000f)
      glu.gluLookAt(0, 0, 1000, 0, 0, 0, 0f, 1f, 0f)

      gl.glBegin(GL.GL_LINES)

      for (i <- 0 to 360 by divisors(petals)) {

        val a1 = Math.toRadians(step + i)
        val a2 = Math.toRadians(step + i + 180.0)
        val r = height.toDouble / 2.0
        val hue = i / 360f
        val color = java.awt.Color.getHSBColor(hue, 1.0f, 1.0f)
        gl.glColor3f(color.getRed.toFloat / 255, color.getGreen.toFloat / 255, color.getBlue.toFloat / 255)
        gl.glVertex3d(0, 0, 0)
        gl.glVertex3d(r * Math.cos(a2), r * Math.sin(a2), 0)
      }

      gl.glEnd()

      step = step + step_delta
    }

    override def init(drawable: GLAutoDrawable): Unit = {}
    override def reshape(drawable: GLAutoDrawable, x: Int, y: Int, _width: Int, _height: Int): Unit = {}
    override def dispose(drawable: GLAutoDrawable): Unit = {}
  })
}
