package nju.edu.lc3.simluator.instruction;

import nju.edu.lc3.util.BitUtil;

public class Bit_Str extends BitInstruction{
	char[] opcode ={'0','1','1','1'};
	int sr;
	int baseR;
	int offset6;
	
	public Bit_Str(char[] bit)
	{
		this.bit = bit;
		sr = BitUtil.bitarrayToInt(bit, 4, 3,false);
		baseR = BitUtil.bitarrayToInt(bit, 7, 3,false);
		offset6 = BitUtil.bitarrayToInt(bit, 10, 6,true);
	}
	@Override
	public boolean execute() {
		/*mem[BaseR+offset6]=sr*/
		return false;
	}

	@Override
	public String getSource() {
		return "STR R"+sr+",R"+baseR+",#"+offset6;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}
}
