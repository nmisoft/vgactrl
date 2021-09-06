package vgactrl

import chisel3._
import chisel3.stage.ChiselStage
import chisel3.util.Counter
import chisel3.util.Valid

class VGACtrl(resolution: Resolution, clockDivisor: Int) extends Module {
    val frame = Module(new Frame(resolution, clockDivisor))
    val spiSlave = Module(new SPISlave(16 + 16 + 16))

    val io = IO(new Bundle {
        val vga = Output(new VGA(resolution.depth))
        val spi = new SPI
    })

    io.vga := frame.io.vga

    spiSlave.io.spi.ss := RegNext(RegNext(io.spi.ss))
    spiSlave.io.spi.mosi := RegNext(RegNext(io.spi.mosi))
    spiSlave.io.spi.sck := RegNext(RegNext(io.spi.sck))

    frame.io.pixel.valid := spiSlave.io.output.valid

    frame.io.pixel.bits.verticalIndex := spiSlave.io.output.bits(47, 32)
    frame.io.pixel.bits.horizontalIndex := spiSlave.io.output.bits(31, 16)

    val redRange = Color.redRange(resolution.depth)
    val greenRange = Color.greenRange(resolution.depth)
    val blueRange = Color.blueRange(resolution.depth)

    frame.io.pixel.bits.red := spiSlave.io.output.bits(redRange._1, redRange._2)
    frame.io.pixel.bits.green := spiSlave.io.output.bits(greenRange._1, greenRange._2)
    frame.io.pixel.bits.blue := spiSlave.io.output.bits(blueRange._1, blueRange._2)
}

object VGACtrl extends App{
    (new ChiselStage).emitVerilog(
        new VGACtrl(
            resolution = new Resolution (
                vertical = new Axis (
                    visibleArea = 400,
                    frontPorch = 12,
                    syncPulse = 2,
                    backPorch = 35,
                ),
                horizontal = new Axis (
                    visibleArea = 640,
                    frontPorch = 16,
                    syncPulse = 96,
                    backPorch = 48,
                ),
                depth = 12
            ),
            clockDivisor = 4
        ),
        Array("--target-dir", "target")
    )
}