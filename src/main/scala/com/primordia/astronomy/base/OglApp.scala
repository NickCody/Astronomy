package com.primordia.astronomy.base

import java.awt.event.{KeyEvent, KeyListener}
import java.awt.{BorderLayout, Dimension}

import com.jogamp.opengl._
import com.jogamp.opengl.awt.GLCanvas
import com.jogamp.opengl.fixedfunc.GLLightingFunc
import com.jogamp.opengl.glu.GLU
import com.jogamp.opengl.util.FPSAnimator
import com.jogamp.opengl.util.gl2.GLUT
import com.primordia.astronomy.base.caps.CapsProvider
import com.primordia.astronomy.base.view.StandardView
import javax.swing.{JFrame, JPanel, WindowConstants}

class OglApp(title: String) extends BasicAppGlEventListener  {
  this: CapsProvider =>

  protected val frame = new JFrame(title)
  protected val panel = new JPanel(new BorderLayout)
  protected final val DEFAULT_FPS = 60
  protected val canvas: GLCanvas = new GLCanvas(caps)

  val glut = new GLUT

  canvas.setPreferredSize(new Dimension(
    Integer.getInteger("width", DEFAULT_WINDOW_WIDTH),
    Integer.getInteger("height", DEFAULT_WINDOW_HEIGHT)))


  panel.add(canvas, BorderLayout.CENTER)
  frame.getContentPane.add(panel, BorderLayout.CENTER)

  canvas.addGLEventListener(this)

  protected val animator = new FPSAnimator(canvas, DEFAULT_FPS)

  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  frame.pack()
  frame.setVisible(true)

  animator.start()
}
