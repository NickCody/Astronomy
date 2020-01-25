package com.primordia.astronomy.base

import java.awt.event.{KeyEvent, KeyListener}
import java.awt.{BorderLayout, Dimension}

import com.jogamp.opengl._
import com.jogamp.opengl.awt.GLCanvas
import com.jogamp.opengl.fixedfunc.GLLightingFunc
import com.jogamp.opengl.glu.GLU
import com.jogamp.opengl.util.FPSAnimator
import javax.swing.{JFrame, JPanel, WindowConstants}

class OglApp(title: String) extends BasicAppGlEventListener  {

  protected val frame = new JFrame(title)
  protected val panel = new JPanel(new BorderLayout)
  protected val fps = 144

  protected val caps = new GLCapabilities(null)
  caps.setDoubleBuffered(true)
  caps.setHardwareAccelerated(true)
  caps.setNumSamples(8)
  caps.setSampleBuffers(true)

  protected val canvas: GLCanvas = new GLCanvas(caps)

  canvas.setPreferredSize(new Dimension(
    Integer.getInteger("width", DEFAULT_WINDOW_WIDTH),
    Integer.getInteger("height", DEFAULT_WINDOW_HEIGHT)))

  panel.add(canvas, BorderLayout.CENTER)
  frame.getContentPane.add(panel, BorderLayout.CENTER)

  canvas.addGLEventListener(this)

  protected val animator = new FPSAnimator(canvas, fps)

  frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE)
  frame.pack()
  frame.setVisible(true)

  animator.start()
}
