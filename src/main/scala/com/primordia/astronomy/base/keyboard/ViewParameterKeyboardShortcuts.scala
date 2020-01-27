package com.primordia.astronomy.base.keyboard

import java.awt.event.{KeyEvent, KeyListener}

import com.primordia.astronomy.base.view.ViewParameters

trait ViewParameterKeyboardShortcuts extends KeyListener {
  this:ViewParameters =>

  def keyPressed(e: KeyEvent): Unit = {
    e.getKeyCode match {
      case KeyEvent.VK_UP => fov = fov * 1.1f
      case KeyEvent.VK_DOWN => fov = Math.max(1f, fov / 1.1f)
      case KeyEvent.VK_RIGHT => eyeZ = eyeZ * 1.1f
      case KeyEvent.VK_LEFT => eyeZ = Math.max(1f, eyeZ / 1.1f)
      case _ =>
    }

    println(s"fov=$fov, eyeZ = $eyeZ")
  }

  def keyTyped(e: KeyEvent): Unit = {}

  def keyReleased(e: KeyEvent): Unit = {}

}
