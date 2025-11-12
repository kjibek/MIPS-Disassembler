import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Disassembler {
	private static int address = 0x9A03C; // initializes variable address as 9a03c
	
	public static void disassemble(int hex) {
		int op = (hex >>> 26) & 0x3F; // obtains op through bitmask
		boolean isRType = false; // default boolean R type false
		if (op == 0) { // checks if R type or I type by op code
			isRType = true;
			RType(hex); // calls R type method w/ parameter of hex instruction
		}
		
		else {
			IType(hex, op); // calls I type method w/ parameter of hex instruction
		}
	}
	
	public static void RType(int hex) { // disassembles for R type
		int mask5 = 0x1F; // sets mask for 5 bit
		int mask6 = 0x3F; // sets mask for 6 bit
		
		int rs = (hex >>> 21) & mask5; // obtains decimal values for rs, rt, rd, and funct by shifting & bitmasking
		int rt = (hex >>> 16) & mask5; 
		int rd = (hex >>> 11) & mask5; 
		int funct = hex & 0x3F; 
		

		Map<Integer, String> Rfunct = new HashMap<>(); // create map for converting from decimal to string representation
		Rfunct.put(32, "add");
		Rfunct.put(34, "sub");
		Rfunct.put(36, "and");
		Rfunct.put(37, "or");
		Rfunct.put(42, "slt");
		
		addressCounter(); // call address counter method to increment address
		String hexAddress = Integer.toHexString(address); // convert address to hex
		System.out.println(hexAddress + " " + Rfunct.get(funct) + " $" + rd + ", $" + rs + ", $" + rt); // output final disassembled instruction
	}
	
	public static void IType (int hex, int opcode) { // disassembles for I type
		int mask5 = 0x1F; // mask for 5 bit
		int mask6 = 0x3F; // mask for 6 bit
		int mask16 = 0xFFFF; // mask for 16 bit
		
		int op = opcode;
		int rs = (hex >>> 21) & mask5; // obtain decimal values for rs, rt, immediate
		int rt = (hex >>> 16) & mask5;
		short immediate = (short) (hex & mask16);
		
		
		Map<Integer, String> Ifunct = new HashMap<>(); // create a map for converting from decimal to string representation
		Ifunct.put(35, "lw");
		Ifunct.put(43, "sw");
		Ifunct.put(4, "beq");
		Ifunct.put(5, "bne");
		
		addressCounter(); // call address counter method
		String hexAddress = Integer.toHexString(address); // convert address to hex
		
		if (Ifunct.get(op).equals("bne") || Ifunct.get(op).equals("beq")) { // if branch instruction
			int addressOffset = (address + 4) + (immediate << 2); // find address for branch by adding offset to current address
			String hexAddressOffset = Integer.toHexString(addressOffset); // convert to hex
			System.out.println(hexAddress + " " + Ifunct.get(op) + " $" + rt + ", " + immediate + " ($" + rs + ") " + hexAddressOffset); //output final disassembled (branch) instruction
		}
		
		else {
			System.out.println(hexAddress + " " + Ifunct.get(op) + " $" + rt + ", " + immediate + " ($" + rs + ")"); //output final disassembled (non-branch) instruction
		}
	}
	
	public static void addressCounter() { // method for incrementing address by 4
		address += 4;
	}
	
	
	
	// call the following hex instructions to be disassembled
	public static void main(String[] args) {
		Disassembler disassemble = new Disassembler();
		Disassembler.disassemble(0x032BA020);
		Disassembler.disassemble(0x8CE90014);
		Disassembler.disassemble(0x12A90003);
		Disassembler.disassemble(0x022DA822);
		Disassembler.disassemble(0xADB30020);
		Disassembler.disassemble(0x02697824);
		Disassembler.disassemble(0xAE8FFFF4);
		Disassembler.disassemble(0x018C6020);
		Disassembler.disassemble(0x02A4A825);
		Disassembler.disassemble(0x158FFFF7);
		Disassembler.disassemble(0x8ECDFFF0);
	}
}