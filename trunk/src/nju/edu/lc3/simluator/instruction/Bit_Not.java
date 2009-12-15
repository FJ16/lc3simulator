package nju.edu.lc3.simluator.instruction;

import nju.edu.lc3.util.BitUtil;

public class Bit_Not extends BitInstruction{
	
	int dr;
	int sr;
	public Bit_Not(char[] bit)
	{
		this.bit = bit;
		dr = BitUtil.bitarrayToInt(bit, 4, 3, false);
		sr = BitUtil.bitarrayToInt(bit, 7, 3, false);
	}

	@Override
	public boolean execute() {
		/*DR = NOT(SR);setcc()*/
		return false;
	}

	@Override
	public String getSource() {
		
		return "NOT R"+dr+",R"+sr;
	}

	@Override
	public boolean validate() {
		for(int i=10;i<16;i++)
		{
			if(bit[i]=='0')
				return false;
		}
		return true;
	}
	
}
