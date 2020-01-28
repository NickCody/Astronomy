package com.primordia.astronomy.base.mouse

import java.awt.Point
import java.awt.event.{MouseEvent, MouseListener, MouseMotionListener}

class MouseViewListener(rotationSource: ViewRotationSource) extends MouseListener with MouseMotionListener{
  var pointClicked: Point = _
  var startX: Float = _
  var startY: Float = _

  override def mouseClicked(e: MouseEvent): Unit = {}

  override def mousePressed(e: MouseEvent): Unit = {
    pointClicked = e.getPoint
    startX = rotationSource.viewRotationX
    startY = rotationSource.viewRotationY
  }

  override def mouseReleased(e: MouseEvent): Unit = {}

  override def mouseEntered(e: MouseEvent): Unit = {}

  override def mouseExited(e: MouseEvent): Unit = {}

  override def mouseDragged(e: MouseEvent): Unit = {
    rotationSource.viewRotationX = startX + (e.getY - pointClicked.y)/2
    rotationSource.viewRotationY = startY + (e.getX - pointClicked.x)/2
  }

  override def mouseMoved(e: MouseEvent): Unit = {}
}
