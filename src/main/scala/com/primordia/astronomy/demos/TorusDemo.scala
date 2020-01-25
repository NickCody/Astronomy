package com.primordia.astronomy.demos

import java.awt.event.{KeyEvent, KeyListener}

import com.jogamp.opengl._
import com.jogamp.opengl.fixedfunc.GLMatrixFunc
import com.jogamp.opengl.glu.GLU
import com.primordia.astronomy.base.OglApp
import com.primordia.astronomy.shapes.ColorWheel

object TorusDemo {
  def main(args: Array[String]): Unit = {
    val sphereDemo = new TorusDemo()
  }
}

class TorusDemo extends OglApp("Torus Demo") {
  step_rate = 0.33f

  val zFar = 100f
  val zNear = 1f
  var fov = 45f
  var eyeZ = 10f

  private val colorWheel = new ColorWheel

  canvas.addKeyListener(new KeyListener() {

    def keyPressed(e: KeyEvent): Unit = {
      e.getKeyCode match {
        case KeyEvent.VK_UP     => fov = fov * 1.1f
        case KeyEvent.VK_DOWN   => fov = Math.max(1f, fov / 1.1f)
        case KeyEvent.VK_RIGHT  => eyeZ = eyeZ * 1.1f
        case KeyEvent.VK_LEFT   => eyeZ = Math.max(1f, eyeZ / 1.1f)
      }

      println(s"fov=$fov, eyeZ = $eyeZ")
    }

    def keyTyped(e: KeyEvent): Unit = {}

    def keyReleased(e: KeyEvent): Unit = {}
  })

  canvas.addGLEventListener(new GLEventListener() {

    override def display(drawable: GLAutoDrawable): Unit = {
      val gl = drawable.getGL.getGL2
      val glu = new GLU

      gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW)
      gl.glLoadIdentity()
      gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT)

      glu.gluPerspective(fov, ratio, zNear, zFar)
      glu.gluLookAt(0, 0, eyeZ, 0, 0, 0, 0f, 1f, 0f)

      gl.glPushMatrix();
        gl.glRotatef(step, 0, 1, 0)

        gl.glPushAttrib(GL2.GL_ALL_ATTRIB_BITS)
          gl.glPolygonOffset(1, 1)
          gl.glColor3f(0.1f,0.1f,0.1f)
          gl.glEnable(GL.GL_POLYGON_OFFSET_FILL)
          glut.glutSolidTorus(1f, 2f, 16, 32)

          gl.getGL2GL3.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2GL3.GL_LINE)
          gl.glDisable(GL.GL_POLYGON_OFFSET_FILL)
          gl.glColor3f(1,1,1)
          glut.glutWireTorus(1f, 2f, 16, 32)
        gl.glPopAttrib()
      gl.glPopMatrix()


      //
      // Draw keyboard shortcuts
      //
      gl.glColor3f(.5f, .5f, .5f)
      gl.glWindowPos2i(100, 100)
      glut.glutBitmapString(8, "DOWN - Decrease FOV")
      gl.glWindowPos2i(100, 120)
      glut.glutBitmapString(8, "UP - Increase FOV")
      gl.glWindowPos2i(100, 140)
      glut.glutBitmapString(8, "LEFT - Draw in")
      gl.glWindowPos2i(100, 160)
      glut.glutBitmapString(8, "RIGHT - Pull out")

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
