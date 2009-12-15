package nju.edu.lc3.simluator.instruction;

import nju.edu.lc3.util.BitUtil;

public class Bit_Lea extends BitInstruction{
	
	int dr;
	int PCoffset9;
	
	public Bit_Lea(char[] bit)
	{
		this.bit= bit;
		dr = BitUtil.bitarrayToInt(bit, 4, 3, false);
		PCoffset9 = BitUtil.bitarrayToInt(bit, 7, 9, true);
	}

	@Override
	public boolean execute() {
		/*DR = PC+ PCoffset9;setcc()*/
		return false;
	}

	@Override
	public String getSource() {
		
		return "LEA R"+dr+","+PCoffset9;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

}
