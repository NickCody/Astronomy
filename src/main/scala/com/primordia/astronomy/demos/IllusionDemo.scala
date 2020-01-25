package com.primordia.astronomy.demos

import com.jogamp.opengl._
import com.jogamp.opengl.fixedfunc.{GLLightingFunc, GLMatrixFunc}
import com.jogamp.opengl.glu.GLU
import com.primordia.astronomy.base.OglApp

import scala.collection.mutable


object IllusionDemo {
  def main(args: Array[String]): Unit = {
    val illusion = new IllusionDemo()
  }
}

class IllusionDemo extends OglApp("Illusion") {
  step = 0.0f

  canvas.addGLEventListener(new GLEventListener() {

    def drawIllusion(gl: GL2, w: Float, h: Float, s: Float): Unit = {

      gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW)
      gl.glPushMatrix();

      gl.glTranslatef(w/2, h/2, 0)
      gl.glScalef(0.5f,0.5f,0.5f)
      gl.glRotatef(s, 0, 1, 0)
      gl.glTranslatef(-w/2, -h/2, 0)

      gl.glBegin(GL.GL_LINES)
      for (i <- 0 to h.toInt by 20) {
        val p = i.toFloat/h.toFloat
        val x = w - (w * p).toInt
        val hue = p
        val color = java.awt.Color.getHSBColor(hue, 1.0f, 1.0f)
        gl.glColor3f(color.getRed.toFloat/255, color.getGreen.toFloat/255, color.getBlue.toFloat/255)
        gl.glVertex3d(0, i, 0)
        gl.glVertex3d(x, 0, 0);
      }
      gl.glEnd()

      gl.glPopMatrix()
    }

    override def display(drawable: GLAutoDrawable): Unit = {

      val gl = drawable.getGL.getGL2

      gl.glLoadIdentity()
      gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT)

      val glu = new GLU
      glu.gluPerspective(45f, ratio, 1f, 10000f)

      val w = width/2f
      val h = height/2f

      glu.gluLookAt(w/2, h/2 , 1000, w/2, h/2, 0, 0f, 1f, 0f)

      drawIllusion(gl, w, h, step)

      step = step + 1f
    }

    override def init(drawable: GLAutoDrawable): Unit = {}
    override def reshape(drawable: GLAutoDrawable, x: Int, y: Int, _width: Int, _height: Int): Unit = {}
    override def dispose(drawable: GLAutoDrawable): Unit = {}

  })

}
