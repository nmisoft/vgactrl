package vgactrl

import chisel3._
import chisel3.util.Counter
import chisel3.util.{switch, is}
import chisel3.stage.ChiselStage

class TimingDescriptor(visibleArea: Int, frontPorch: Int, syncPulse: Int, backPorch: Int) {
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

class VGACtrl(
    verticalDescriptor: TimingDescriptor = new TimingDescriptor(
        visibleArea = 600,
        frontPorch = 37,
        syncPulse = 6,
        backPorch = 23,
    ),
    horizontalDescriptor: TimingDescriptor = new TimingDescriptor(
        visibleArea = 800,
        frontPorch = 56,
        syncPulse = 120,
        backPorch = 64,
    ),
    colorDepth: Int = 12,
    pixelClockDivisor: Int = 2,
    ) extends Module {

    val PerColorDepth = colorDepth / 3

    val io = IO(new Bundle {
        val red = Output(UInt(PerColorDepth.W))
        val green = Output(UInt(PerColorDepth.W))
        val blue = Output(UInt(PerColorDepth.W))
        val verticalSync = Output(Bool())
        val horizontalSync = Output(UInt(1.W))
    })

    val pixelClock = Counter(pixelClockDivisor).inc().asClock()
    val lineCounter = withClock(pixelClock) {
        Counter(verticalDescriptor.whole())
    }
    val verticalSync = RegInit(1.U(1.W))
    switch(lineCounter.value) {
        is(verticalDescriptor.syncStart().U) {
            verticalSync := 0.U
        }
        is(verticalDescriptor.syncEnd().U) {
            verticalSync := 1.U
        }
    }

    val pixelCounter = withClock(pixelClock) {
        Counter(horizontalDescriptor.whole())
    }
    pixelCounter.inc()
    val horizontalSync = RegInit(1.U(1.W))
    switch(pixelCounter.value) {
        is(horizontalDescriptor.syncStart().U) {
            horizontalSync := 0.U
        }
        is(horizontalDescriptor.syncEnd().U) {
            horizontalSync := 1.U
        }
        is((horizontalDescriptor.whole() - 1).U) {
            lineCounter.inc()
        }
    }
    io.horizontalSync := horizontalSync

    io.verticalSync := verticalSync

    when (pixelCounter.value >= 100.U && pixelCounter.value <= 200.U) {
        io.red := 0.U
        io.green := 4.U
        io.blue := 4.U
    } .otherwise {
        io.red := 0.U
        io.green := 0.U
        io.blue := 0.U
    }
}

object VGACtrl extends App{
    (new ChiselStage).emitVerilog(new VGACtrl, Array("--target-dir", "target"))
}