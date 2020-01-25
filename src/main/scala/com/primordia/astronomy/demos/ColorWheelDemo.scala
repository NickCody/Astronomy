package com.primordia.astronomy.demos

import java.awt.event.{KeyEvent, KeyListener}

import com.jogamp.opengl._
import com.jogamp.opengl.glu.GLU
import com.primordia.astronomy.base.OglApp
import com.primordia.astronomy.shapes.ColorWheel

object ColorWheelDemo {
  def main(args: Array[String]): Unit = {
    val colorWheelDemo = new ColorWheelDemo()
  }
}
class ColorWheelDemo extends OglApp("Color Wheel Demo") {
  step_rate = 0.1f
  private val colorWheel = new ColorWheel

  canvas.addKeyListener(new KeyListener() {

    def keyPressed(e: KeyEvent): Unit = {
      e.getKeyCode match {
        case KeyEvent.VK_UP => colorWheel.setPetals( colorWheel.getPetals + 1)
        case KeyEvent.VK_DOWN => colorWheel.setPetals( colorWheel.getPetals - 1)
        case KeyEvent.VK_LEFT => step_rate = step_rate / 1.25f
        case KeyEvent.VK_RIGHT => step_rate = step_rate * 1.25f
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

      colorWheel.draw(gl, step, width, height)

      //
      // Draw keyboard shortcuts
      //
      gl.glColor3f(.5f, .5f, .5f)
      gl.glWindowPos2i(100, 100)
      glut.glutBitmapString(8, "DOWN - Less petals")
      gl.glWindowPos2i(100, 120)
      glut.glutBitmapString(8, "UP - more petals")
      gl.glWindowPos2i(100, 140)
      glut.glutBitmapString(8, "LEFT - Slower")
      gl.glWindowPos2i(100, 160)
      glut.glutBitmapString(8, "RIGHT - Faster")
      gl.glFlush()
    }

    override def init(drawable: GLAutoDrawable): Unit = {}
    override def reshape(drawable: GLAutoDrawable, x: Int, y: Int, _width: Int, _height: Int): Unit = {}
    override def dispose(drawable: GLAutoDrawable): Unit = {}
  })
}
