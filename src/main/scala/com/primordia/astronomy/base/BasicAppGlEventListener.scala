package com.primordia.astronomy.base

import com.jogamp.opengl.fixedfunc.GLLightingFunc
import com.jogamp.opengl.{GL, GLAutoDrawable, GLEventListener}

trait BasicAppGlEventListener extends GLEventListener {
  val DEFAULT_WINDOW_WIDTH = 1920
  val DEFAULT_WINDOW_HEIGHT = 1080

  var width: Int = DEFAULT_WINDOW_WIDTH
  var height: Int = DEFAULT_WINDOW_HEIGHT
  var ratio: Float = DEFAULT_WINDOW_WIDTH.toFloat/DEFAULT_WINDOW_HEIGHT.toFloat

  override def init(drawable: GLAutoDrawable): Unit = {
    val gl = drawable.getGL.getGL2

    gl.glShadeModel(GLLightingFunc.GL_SMOOTH)
    gl.glEnable(GL.GL_BLEND)
    gl.glEnable(GL.GL_MULTISAMPLE)
    gl.glEnable(GL.GL_LINE_SMOOTH)
    gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA)
  }

  override def dispose(drawable: GLAutoDrawable): Unit = {}

  override def display(drawable: GLAutoDrawable): Unit = {}

  override def reshape(drawable: GLAutoDrawable, x: Int, y: Int, _width: Int, _height: Int): Unit = {
    width = _width
    height = _height
    val gl = drawable.getGL.getGL2
    gl.glViewport(0, 0, width, height)
    ratio = width.toFloat / height.toFloat
  }
}
