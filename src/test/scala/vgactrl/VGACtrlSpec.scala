package vgactrl

import org.scalatest._
import chiseltest._
import chisel3._

import chiseltest.experimental.TestOptionBuilder._
import chiseltest.internal.WriteVcdAnnotation

class VGACtrlSpec extends FlatSpec with ChiselScalatestTester with Matchers {
  behavior of "VGACtrl"

  it should "do something" in {
    test(new VGACtrl).withAnnotations(Seq(WriteVcdAnnotation)) { c =>
      c.clock.setTimeout(0)
      c.clock.step(1000000)
    }
  }
}