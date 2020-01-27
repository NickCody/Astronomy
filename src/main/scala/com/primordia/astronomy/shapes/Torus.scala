package com.primordia.astronomy.shapes

import com.jogamp.opengl.util.gl2.GLUT
import com.jogamp.opengl.{GL, GL2, GL2GL3}

class Torus(innerRadius: Float = 1f, outerRadius: Float = 2f, nSides: Int = 16, rings: Int = 32) {
  val glut = new GLUT

  def draw(gl: GL2): Unit = {

    gl.glPushAttrib(GL2.GL_ALL_ATTRIB_BITS)
    gl.glPolygonOffset(1, 1)
    gl.glColor3f(0.3f,0.3f,0.3f)
    gl.glEnable(GL.GL_POLYGON_OFFSET_FILL)
    glut.glutSolidTorus(innerRadius, outerRadius, nSides, rings)

    gl.getGL2GL3.glPolygonMode(GL.GL_FRONT_AND_BACK, GL2GL3.GL_LINE)
    gl.glDisable(GL.GL_POLYGON_OFFSET_FILL)
    gl.glColor3f(1f,1f,1f)
    glut.glutWireTorus(innerRadius, outerRadius, nSides, rings)
    gl.glPopAttrib()
  }
}
