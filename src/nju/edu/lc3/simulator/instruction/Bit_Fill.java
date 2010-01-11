package nju.edu.lc3.simulator.instruction;

import nju.edu.lc3.util.BitUtil;

public class Bit_Fill extends BitInstruction{

	public Bit_Fill(char[] bit)
	{
		this.bit = bit;
	}
	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getSource() {
		// TODO Auto-generated method stub
		return ".FILL "+BitUtil.bitArrayToInt(bit, 0, 16, true);
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

}
