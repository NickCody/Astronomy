package com.primordia.astronomy.demos

import com.jogamp.opengl._
import com.jogamp.opengl.fixedfunc.{GLLightingFunc, GLMatrixFunc}
import com.jogamp.opengl.glu.GLU
import com.primordia.astronomy.base.OglApp

import scala.collection.mutable

object SphereDemo {
  def main(args: Array[String]): Unit = {
    val sphereDemo = new SphereDemo()
  }
}
class SphereDemo extends OglApp("Illusion") {
  var step = 0.0f

  canvas.addGLEventListener(new GLEventListener() {

    def drawSphere(gl: GL2, w: Float, h: Float, s: Float): Unit = {
      gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW)

      gl.glPushMatrix()

      gl.glTranslatef(w/2, h/2, 0)
      gl.glRotatef(s, 0, 1f, 0)
      gl.glScalef(10f,10f,10f)

      gl.glColor3f(1f, 1f, 1f)
      gl.glBegin(GL.GL_TRIANGLES)

      val sectorCount = 20
      val stackCount = 20
      val stackStep = Math.PI / stackCount
      val sectorStep = 2f * Math.PI / sectorCount
      val radius = 10f

      val vertices = new mutable.ArrayBuffer[Float]()
      val delta = 0.5f
      for(stack <- 0 until stackCount) {
        val stackAngle = Math.PI / 2f + stack * stackStep
        val xy = radius * Math.cos(stackAngle).toFloat
        val z = radius * Math.sin(stackAngle).toFloat
        for (sector <- 0 until sectorCount) {
          val sectorAngle = sector * sectorStep
          val x = xy * Math.cos(sectorAngle).toFloat
          val y = xy * Math.sin(sectorAngle).toFloat
          gl.glVertex3f(x, y+delta, z)
          gl.glVertex3f(x+delta, y-delta, z)
          gl.glVertex3f(x-delta, y-delta, z)
        }
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

      drawSphere(gl, w, h, step)

      step = step + 1f
    }

    override def init(drawable: GLAutoDrawable): Unit = {}
    override def reshape(drawable: GLAutoDrawable, x: Int, y: Int, _width: Int, _height: Int): Unit = {}
    override def dispose(drawable: GLAutoDrawable): Unit = {}

  })

}
