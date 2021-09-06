package vgactrl

import chisel3._

class VGA(depth: Int) extends Bundle {
    val red = UInt((depth / 3).W)
    val green = UInt((depth / 3).W)
    val blue = UInt((depth / 3).W)
    val verticalSync = Bool()
    val horizontalSync = Bool()
}
