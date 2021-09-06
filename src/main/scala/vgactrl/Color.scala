package vgactrl

import chisel3._

case class Color(red: UInt, green: UInt, blue: UInt)
{
    def bits(): UInt = {
        red ## green ## blue
    }
}

object Color {
    def apply(rgbColor: UInt): Color = {
        val depth = rgbColor.getWidth

        val red   = rgbColor(redRange(depth)._1, redRange(depth)._2)
        val green = rgbColor(greenRange(depth)._1, greenRange(depth)._2)
        val blue  = rgbColor(blueRange(depth)._1, blueRange(depth)._2)

        new Color(red, green, blue)
    }

    def apply(red: UInt, green: UInt, blue: UInt): Color = {
        new Color(red, green, blue)
    }

    def blueRange(depth: Int): (Int, Int) = {
        val width = depth / 3

        (
            1 * width - 1,
            0 * width
        )
    }

    def greenRange(depth: Int): (Int, Int) = {
        val width = depth / 3

        (
            2 * width - 1,
            1 * width
        )
    }

    def redRange(depth: Int): (Int, Int) = {
        val width = depth / 3

        (
            3 * width - 1,
            2 * width
        )
    }
}
