package com.primordia.astronomy.base.view

import com.jogamp.opengl.{GL, GL2, GLAutoDrawable, GLEventListener}
import com.jogamp.opengl.awt.GLCanvas
import com.jogamp.opengl.fixedfunc.GLMatrixFunc
import com.jogamp.opengl.glu.GLU
import com.jogamp.opengl.util.gl2.GLUT
import com.primordia.astronomy.base.keyboard.ViewParameterKeyboardShortcuts
import org.apache.commons.math3.geometry.euclidean.threed.Vector3D

class StandardView(canvas: GLCanvas) extends ViewParameters  with ViewParameterKeyboardShortcuts{
  private val glut = new GLUT

  canvas.addKeyListener(this)

  def draw(gl: GL2): Unit = {

    gl.glMatrixMode(GLMatrixFunc.GL_MODELVIEW)
    gl.glLoadIdentity()

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
  }

}
