package nju.edu.lc3.simulator.instruction;

import nju.edu.lc3.simulator.model.MemoryModel;
import nju.edu.lc3.simulator.model.RegisterModel;
import nju.edu.lc3.util.BitUtil;

public class Bit_Trap extends BitInstruction{
	char[] opcode ={'1','1','1','1'};
	
	int trapvect8;
	
	public Bit_Trap(char[] bit,int address)
	{
		this.bit = bit;
		this.address = address;
		trapvect8 = BitUtil.bitArrayToInt(bit, 8, 8,true);
	}
	@Override
	public boolean execute() {
		/*R7 = PC; PC = mem[trapvect8]*/
		int pc = RegisterModel.getRegister("PC").getValue();
		RegisterModel.getRegister(7).setValue(pc);
		int value = MemoryModel.getMemory(trapvect8).getValue();
		RegisterModel.getRegister("PC").setValue(value);
		return false;
	}

	@Override
	public String getSource() {
		return "TRAP x"+Integer.toHexString(trapvect8);
	}

	@Override
	public boolean validate() {
		int temp = BitUtil.bitArrayToInt(bit, 4, 4, false);
		if(temp!=0)
			return false;
		return true;
	}

}
