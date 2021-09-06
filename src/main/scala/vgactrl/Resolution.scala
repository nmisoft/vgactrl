package vgactrl

import chisel3._

class Axis(var visibleArea: Int, var frontPorch: Int, var syncPulse: Int, var backPorch: Int) {
    def syncStart(): Int = {
        visibleArea + frontPorch
    }
    def syncEnd(): Int = {
        syncStart + syncPulse
    }
    def whole(): Int = {
        syncEnd + backPorch
    }
}

case class Resolution(
    val vertical: Axis,
    val horizontal: Axis,
    val depth: Int
)
