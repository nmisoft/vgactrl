package vgactrl

import chisel3._
import chisel3.util.{switch, is}
import chisel3.util.Counter
import chisel3.util.Valid

class Frame(resolution: Resolution, clockDivisor: Int) extends Module {

    def getMemoryAddress(verticalIndex: UInt, horizontalIndex: UInt): UInt = {
        verticalIndex * resolution.horizontal.visibleArea.U + horizontalIndex
    }

    val pixelClock = Counter(clockDivisor).inc().asClock()

    val frameBuffer = SyncReadMem(resolution.vertical.visibleArea * resolution.horizontal.visibleArea, UInt(resolution.depth.W))

    val verticalCounter = withClock(pixelClock) {
        Counter(resolution.vertical.whole())
    }
    val verticalSync = RegInit(1.U(1.W))
    switch(verticalCounter.value) {
        is(resolution.vertical.syncStart().U) {
            verticalSync := 0.U
        }
        is(resolution.vertical.syncEnd().U) {
            verticalSync := 1.U
        }
    }

    val horizontalCounter = withClock(pixelClock) {
        Counter(resolution.horizontal.whole())
    }
    horizontalCounter.inc()
    val horizontalSync = RegInit(1.U(1.W))
    switch(horizontalCounter.value) {
        is(resolution.horizontal.syncStart().U) {
            horizontalSync := 0.U
        }
        is(resolution.horizontal.syncEnd().U) {
            horizontalSync := 1.U
        }
        is((resolution.horizontal.whole() - 1).U) {
            verticalCounter.inc()
        }
    }

    val io = IO(new Bundle {
        val vga = Output(new VGA(resolution.depth))
        val pixel = Input(Valid(new Pixel(resolution)))
    })

    /* VGA Output */
    io.vga.horizontalSync := horizontalSync
    io.vga.verticalSync := verticalSync
    io.vga.red := 0.U
    io.vga.green := 0.U
    io.vga.blue := 0.U
    when (horizontalCounter.value >= 0.U &&
            horizontalCounter.value < resolution.horizontal.visibleArea.U &&
            verticalCounter.value >= 0.U &&
            verticalCounter.value < resolution.vertical.visibleArea.U) {
        val color = Color(frameBuffer.read(getMemoryAddress(verticalCounter.value, horizontalCounter.value), true.B))
        io.vga.red := color.red
        io.vga.green := color.green
        io.vga.blue := color.blue
    }

    /* Image Input */
    when (io.pixel.valid) {
        val color = Color(io.pixel.bits.red, io.pixel.bits.green, io.pixel.bits.blue)
        frameBuffer.write(getMemoryAddress(io.pixel.bits.verticalIndex, io.pixel.bits.horizontalIndex), color.bits)
    }
}