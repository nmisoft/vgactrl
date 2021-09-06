package vgactrl

import chisel3._
import chisel3.util.Valid

class SPI extends Bundle {
    val ss = Input(Bool())
    val mosi = Input(Bool())
    val sck = Input(Bool())
}

class SPISlave(width: Int) extends Module {

    def risingEdge(x: Bool) = x && !RegNext(x)

    val io = IO(new Bundle {
        val spi = new SPI
        val output = Output(Valid(UInt(width.W)))
    })

    val data = RegInit(0.U(width.W))

    when (!io.spi.ss && risingEdge(io.spi.sck)) {
        data := data(width - 2, 0) ## io.spi.mosi
    }

    io.output.valid := false.B
    io.output.bits := 0.U
    when (risingEdge(io.spi.ss)) {
        io.output.valid := true.B
        io.output.bits := data
    }
}