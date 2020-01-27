package com.primordia.astronomy.base

import com.jogamp.opengl.fixedfunc.GLLightingFunc
import com.jogamp.opengl.{GL, GLAutoDrawable, GLEventListener}
import com.primordia.astronomy.base.caps.CapsProvider

trait BasicAppGlEventListener extends GLEventListener {
  this: CapsProvider =>
  val DEFAULT_WINDOW_WIDTH = 1920
  val DEFAULT_WINDOW_HEIGHT = 1080

  var width: Int = DEFAULT_WINDOW_WIDTH
  var height: Int = DEFAULT_WINDOW_HEIGHT
  var ratio: Float = DEFAULT_WINDOW_WIDTH.toFloat/DEFAULT_WINDOW_HEIGHT.toFloat
  var step: Float = 0f
  var step_rate: Float = 1f

  override def init(drawable: GLAutoDrawable): Unit = {
    val gl = drawable.getGL.getGL2

    enableFeatures(gl)
  }

  override def dispose(drawable: GLAutoDrawable): Unit = {}

  override def display(drawable: GLAutoDrawable): Unit = {
    step = step + step_rate
  }

  override def reshape(drawable: GLAutoDrawable, x: Int, y: Int, _width: Int, _height: Int): Unit = {
    width = _width
    height = _height
    val gl = drawable.getGL.getGL2
    gl.glViewport(0, 0, width, height)
    ratio = width.toFloat / height.toFloat
  }
}
