package nju.edu.lc3.simluator.instruction;

import nju.edu.lc3.util.BitUtil;

public class Bit_Ldr extends BitInstruction{
	char[] opcode ={'0','1','1','0'};
	int dr;
	int baseR;
	int offset6;
	
	public Bit_Ldr(char[] bit)
	{
		this.bit = bit;
		dr = BitUtil.bitarrayToInt(bit, 4, 3,false);
		baseR = BitUtil.bitarrayToInt(bit, 7, 3,false);
		offset6 = BitUtil.bitarrayToInt(bit, 10, 6,true);
	}
	@Override
	public boolean execute() {
		/*DR=mem[BaseR+offset6]*/
		return false;
	}

	@Override
	public String getSource() {
		return "LDR R"+dr+",R"+baseR+",#"+offset6;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

}
