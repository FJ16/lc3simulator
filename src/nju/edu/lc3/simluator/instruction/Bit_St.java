package nju.edu.lc3.simluator.instruction;

import nju.edu.lc3.util.BitUtil;

public class Bit_St extends BitInstruction{
	char[] opcode = {'0','0','1','1'};
	int sr;
	int PCoffset9;
	
	public Bit_St(char[] bit)
	{
		this.bit = bit;
		sr = BitUtil.bitarrayToInt(bit, 4, 3,false);
		PCoffset9 = BitUtil.bitarrayToInt(bit, 7, 9,true);
	}
	@Override
	public boolean execute() {
		/*mem[PC+PCofffset9]=sr*/
		return false;
	}

	@Override
	public String getSource() {
		return "ST R"+sr+","+PCoffset9;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

}
