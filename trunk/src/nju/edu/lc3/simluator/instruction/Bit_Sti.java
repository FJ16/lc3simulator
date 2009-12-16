package nju.edu.lc3.simluator.instruction;

import nju.edu.lc3.simulator.gui.MemoryModel;
import nju.edu.lc3.simulator.gui.RegisterModel;
import nju.edu.lc3.util.BitUtil;

public class Bit_Sti extends BitInstruction{
	char[] opcode ={'1','0','1','1'};
	int sr;
	int PCoffset9;
	
	public Bit_Sti(char[] bit)
	{
		this.bit = bit;
		sr = BitUtil.bitarrayToInt(bit, 4, 3,false);
		PCoffset9 = BitUtil.bitarrayToInt(bit, 7, 9,true);
	}
	@Override
	public boolean execute() {
		/*mem[mem[PC+PCofffset9]]=SR*/
		int value = RegisterModel.getRegister(sr).getValue();
		int index = RegisterModel.getRegister("PC").getValue()+PCoffset9;
		int address = MemoryModel.getMemory(index).getValue();
		MemoryModel.getMemory(address).setValue(value);
		return true;
	}

	@Override
	public String getSource() {
		return "STI R"+sr+","+PCoffset9;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}
}
