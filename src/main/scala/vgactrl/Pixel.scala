package vgactrl

import chisel3._
import chisel3.util.unsignedBitLength

class Pixel(resolution: Resolution) extends Bundle {
    val verticalIndex = UInt(unsignedBitLength(resolution.vertical.visibleArea).W)
    val horizontalIndex =  UInt(unsignedBitLength(resolution.horizontal.visibleArea).W)
    val red = UInt((resolution.depth / 3).W)
    val green = UInt((resolution.depth / 3).W)
    val blue = UInt((resolution.depth / 3).W)
}
