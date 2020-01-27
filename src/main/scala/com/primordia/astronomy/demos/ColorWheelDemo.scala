package com.primordia.astronomy.demos

import java.awt.event.{KeyEvent, KeyListener}

import com.jogamp.opengl._
import com.jogamp.opengl.glu.GLU
import com.primordia.astronomy.base.OglApp
import com.primordia.astronomy.base.caps.HighQualityCapsProvider
import com.primordia.astronomy.base.view.StandardView
import com.primordia.astronomy.shapes.ColorWheel

object ColorWheelDemo {
  def main(args: Array[String]): Unit = {
    val colorWheelDemo = new ColorWheelDemo()
  }
}
class ColorWheelDemo extends OglApp("Color Wheel Demo") with HighQualityCapsProvider {
  protected val view = new StandardView(canvas)

  step_rate = 0.1f
  private val colorWheel = new ColorWheel

  canvas.addKeyListener(new KeyListener() {

    def keyPressed(e: KeyEvent): Unit = {
      e.getKeyCode match {
        case KeyEvent.VK_EQUALS => colorWheel.setPetals( colorWheel.getPetals + 1)
        case KeyEvent.VK_MINUS => colorWheel.setPetals( colorWheel.getPetals - 1)
        case KeyEvent.VK_OPEN_BRACKET => step_rate = step_rate / 1.25f
        case KeyEvent.VK_CLOSE_BRACKET => step_rate = step_rate * 1.25f
        case _ =>
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
      glu.gluPerspective(view.fov, ratio, view.zNear, view.zFar)
      glu.gluLookAt(0, 0, view.eyeZ, 0, 0, 0, 0f, 1f, 0f)

      colorWheel.draw(gl, step, width, height)

      view.draw(gl)

      //
      // Draw keyboard shortcuts
      //
      gl.glColor3f(.5f, .5f, .5f)
      gl.glWindowPos2i(width-300, 100)
      glut.glutBitmapString(8, "MINUS - Less petals")
      gl.glWindowPos2i(width-300, 120)
      glut.glutBitmapString(8, "PLUS - more petals")
      gl.glWindowPos2i(width-300, 140)
      glut.glutBitmapString(8, "OPEN_BRACKET - Slower")
      gl.glWindowPos2i(width-300, 160)
      glut.glutBitmapString(8, "CLOSE_BRACKET - Faster")
      gl.glFlush()
    }

    override def init(drawable: GLAutoDrawable): Unit = {}
    override def reshape(drawable: GLAutoDrawable, x: Int, y: Int, _width: Int, _height: Int): Unit = {}
    override def dispose(drawable: GLAutoDrawable): Unit = {}
  })
}
