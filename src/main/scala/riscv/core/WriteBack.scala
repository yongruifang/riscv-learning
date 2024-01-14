package  riscv.core

import chisel3._
import chisel3.util._
import riscv.Parameters

class WriteBack extends Module {
  val io = IO(new Bundle() {
    val instruction_address = Input(UInt(Parameters.AddrWidth)) // 指令地址, 当指令为跳转指令时, 该地址为跳转后的地址
    val alu_result = Input(UInt(Parameters.DataWidth)) // ALU计算结果
    val memory_read_data = Input(UInt(Parameters.DataWidth)) // 从内存中读取的数据
    val regs_write_source = Input(UInt(2.W)) // 将要写入寄存器的数据来源， 00: ALU计算结果, 01: 从内存中读取的数据, 11: 下一条指令的地址
    val regs_write_data = Output(UInt(Parameters.DataWidth)) // 将要写入寄存器的数据
  })
  io.regs_write_data := MuxLookup(
    io.regs_write_source, // 当regs_write_source为00时, 将alu_result写入寄存器
    io.alu_result,
    IndexedSeq( 
      RegWriteSource.Memory -> io.memory_read_data, // 当regs_write_source为01时, 将memory_read_data写入寄存器
      RegWriteSource.NextInstructionAddress -> (io.instruction_address + 4.U) // 当regs_write_source为11时, 将下一条指令的地址写入寄存器
    )
  )
}