package com.primordia.astronomy.demos

import java.awt.event.{KeyEvent, KeyListener}

import com.jogamp.opengl._
import com.jogamp.opengl.fixedfunc.GLMatrixFunc
import com.jogamp.opengl.glu.GLU
import com.primordia.astronomy.base.OglApp
import com.primordia.astronomy.base.caps.HighQualityCapsProvider
import com.primordia.astronomy.base.view.StandardView
import com.primordia.astronomy.shapes.{ColorWheel, Torus}
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D

import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer

object TorusDemo {
  def main(args: Array[String]): Unit = {
    val sphereDemo = new TorusDemo()
  }
}

class TorusDemo extends OglApp("Torus Demo") with HighQualityCapsProvider {
  protected val view = new StandardView(canvas)

  step_rate = 0.33f

  val turbulence = 0.01f
  val scale = 8f
  val dim = 40
  val spacing = 10
  val translations = ArrayBuffer.fill[Vector3D](dim * dim)(new Vector3D(0, 0, 0))
  for (i <- 0 until dim) {
    for (j <- 0 until dim) {
      translations.update(i * dim + j, new Vector3D(0, 0, 0));
    }
  }

  private val torus = new Torus(1f, 2f, 12, 24)

  canvas.addGLEventListener(new GLEventListener() {

    override def display(drawable: GLAutoDrawable): Unit = {
      val gl = drawable.getGL.getGL2
      val glu = new GLU

      gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW)
      gl.glLoadIdentity()

      gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT)

      glu.gluPerspective(view.fov, ratio, view.zNear, view.zFar)
      glu.gluLookAt(0, 0, view.eyeZ, 0, 0, 0, 0f, 1f, 0f)

      for (i <- 0 until dim) {
        for (j <- 0 until dim) {
          gl.glPushMatrix()

          val offset = translations(i * dim + j).add(
            new Vector3D(
              turbulence/2f - Math.random() * turbulence,
              turbulence/2f - Math.random() * turbulence,
              turbulence/2f - Math.random() * turbulence,
            )
          )

          translations(i * dim + j) = offset
          val v1 = (new Vector3D(scale * (dim / 2 - i), scale * (dim / 2 - j), 0)).add(offset)
          gl.glTranslated(v1.getX, v1.getY, v1.getZ)

          gl.glRotatef(step, 0, 1, 0)
          gl.glRotatef(step * 6, 0, 0, 1)
          torus.draw(gl)

          gl.glPopMatrix()
        }

      }

      view.draw(gl)

      gl.glFlush()
    }

    override def init(drawable: GLAutoDrawable): Unit = {
      val gl = drawable.getGL.getGL2
      gl.glEnable(GL.GL_CULL_FACE)
      gl.glCullFace(GL.GL_BACK)
      gl.glEnable(GL.GL_DEPTH_TEST)
      gl.glDepthFunc(GL.GL_LEQUAL)
    }

    override def reshape(drawable: GLAutoDrawable, x: Int, y: Int, _width: Int, _height: Int): Unit = {}

    override def dispose(drawable: GLAutoDrawable): Unit = {}
  })
}
