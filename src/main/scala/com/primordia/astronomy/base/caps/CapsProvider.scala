package com.primordia.astronomy.base.caps

import com.jogamp.opengl.{GL2, GLCapabilities}

trait CapsProvider {
  def caps: GLCapabilities = ???
  def enableFeatures(gl: GL2): Unit = ???
}
