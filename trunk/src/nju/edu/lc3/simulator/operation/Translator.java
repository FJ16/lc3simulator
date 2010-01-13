package nju.edu.lc3.simulator.operation;

import nju.edu.lc3.simulator.instruction.BitInstruction;
import nju.edu.lc3.simulator.instruction.Bit_Add;
import nju.edu.lc3.simulator.instruction.Bit_And;
import nju.edu.lc3.simulator.instruction.Bit_Br;
import nju.edu.lc3.simulator.instruction.Bit_Fill;
import nju.edu.lc3.simulator.instruction.Bit_JmpRet;
import nju.edu.lc3.simulator.instruction.Bit_Jsr;
import nju.edu.lc3.simulator.instruction.Bit_Ld;
import nju.edu.lc3.simulator.instruction.Bit_Ldi;
import nju.edu.lc3.simulator.instruction.Bit_Ldr;
import nju.edu.lc3.simulator.instruction.Bit_Lea;
import nju.edu.lc3.simulator.instruction.Bit_Not;
import nju.edu.lc3.simulator.instruction.Bit_Rti;
import nju.edu.lc3.simulator.instruction.Bit_St;
import nju.edu.lc3.simulator.instruction.Bit_Sti;
import nju.edu.lc3.simulator.instruction.Bit_Str;
import nju.edu.lc3.simulator.instruction.Bit_Trap;
import nju.edu.lc3.util.BitUtil;
import nju.edu.lc3.word.Bits;

public class Translator {
	public static BitInstruction decode(int value,int address)
	{
		char[] bit = null;
		try {
			bit = Bits.TO_BIT_ARRAY(value, 16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int op = getOperation(bit);
		BitInstruction instruction;
		switch(op)
		{
		case 1:
			instruction = new Bit_Add(bit,address);
			break;
		case 5:
			instruction = new Bit_And(bit,address);
			break;
		case 0:
			instruction = new Bit_Br(bit,address);
			break;
		case 12:
			instruction = new Bit_JmpRet(bit,address);
			break;
		case 4:
			instruction = new Bit_Jsr(bit,address);
			break;
		case 2:
			instruction = new Bit_Ld(bit,address);
			break;
		case 10:
			instruction = new Bit_Ldi(bit,address);
			break;
		case 6:
			instruction = new Bit_Ldr(bit,address);
			break;
		case 14:
			instruction = new Bit_Lea(bit,address);
			break;
		case 9:
			instruction = new Bit_Not(bit,address);
			break;
		case 8:
			instruction = new Bit_Rti(bit,address);
			break;
		case 3:
			instruction = new Bit_St(bit,address);
			break;
		case 11:
			instruction = new Bit_Sti(bit,address);
			break;
		case 7:
			instruction = new Bit_Str(bit,address);
			break;
		case 15:
			instruction = new Bit_Trap(bit,address);
			break;
		default:
			instruction = new Bit_Fill(bit,address);
		}
		if(instruction.validate()==false)
		{
			instruction = new Bit_Fill(bit,address);
		}
			
		return instruction;
	}
	private static int getOperation(char[] bit)
	{
		return BitUtil.bitArrayToInt(bit, 0, 4,false);
	}
}
