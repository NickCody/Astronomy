package com.primordia.astronomy.base.caps

import com.jogamp.opengl.fixedfunc.GLLightingFunc
import com.jogamp.opengl.{GL, GL2, GLCapabilities}

trait HighQualityCapsProvider extends CapsProvider {

  override def caps: GLCapabilities = {
    val c = new GLCapabilities(null)
    c.setNumSamples(16)
    c.setAlphaBits(8)
    c.setSampleBuffers(true)
    c
  }

  override def enableFeatures(gl: GL2): Unit = {
    gl.glShadeModel(GLLightingFunc.GL_SMOOTH)
    gl.glEnable(GL.GL_BLEND)
    gl.glEnable(GL.GL_MULTISAMPLE)
    gl.glEnable(GL.GL_LINE_SMOOTH)
//    gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA)
    gl.glHint(GL.GL_LINE_SMOOTH_HINT, GL.GL_NICEST)
  }

}
