package nju.edu.lc3.simluator.instruction;

import nju.edu.lc3.util.BitUtil;

public class Bit_Trap extends BitInstruction{
	char[] opcode ={'1','1','1','1'};
	
	int trapvect8;
	
	public Bit_Trap(char[] bit)
	{
		this.bit = bit;
		
		trapvect8 = BitUtil.bitarrayToInt(bit, 8, 8,true);
	}
	@Override
	public boolean execute() {
		/*R7 = PC; PC = mem[trapvect8]*/
		
		return false;
	}

	@Override
	public String getSource() {
		return "TRAP "+trapvect8;
	}

	@Override
	public boolean validate() {
		int temp = BitUtil.bitarrayToInt(bit, 4, 4, false);
		if(temp!=0)
			return false;
		return true;
	}

}
