package easypath

import chisel3._
import chisel3.testers._
import chiseltest._
import chiseltest.formal._
import org.scalatest.flatspec.AnyFlatSpec

class SimpleTest extends AnyFlatSpec with ChiselScalatestTester with Formal {
    "DUT" should "pass" in {
		test(new DeviceUnderTest) {dut =>
			dut.io.a.poke(0.U)
			dut.io.b.poke(1.U)
			dut.clock.step()
			println("Result is: " + dut.io.out.peek().toString)
			dut.io.a.poke(3.U)
			dut.io.b.poke(2.U)
			dut.clock.step()
			println("Result is:" + dut.io.out.peek().toString)
		}
	}
}

