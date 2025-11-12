# 🖥️ MIPS Partial Disassembler


The goal of this program is to **mimick the computer architecture of a partial MIPS disassembler**. It **takes in 32-bit instructions (in hexadecimal) and outputs the original source instructions** (for example: 9A040 lw $10, 12 ($20)).

The instructions will be of two types-- I-type and R-type where the possible **source instructions are: add, sub, and, or, slt, lw, sw, beq, bne**.

The **first instruction begins at hex 9A040**. The **output of registers will be in numerical format (e.g. $7, $0)** as opposed to the symbolic descriptions (e.g. $s3, $t1).


**This program will be disassembling the following examples of instructions:**
* 0x032BA020
* 0x8CE90014
* 0x12A90003
* 0x022DA822
* 0xADB30020
* 0x02697824
* 0xAE8FFFF4
* 0x018C6020
* 0x02A4A825
* 0x158FFFF7
* 0x8ECDFFF0
