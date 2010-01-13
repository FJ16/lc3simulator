package nju.edu.lc3.simulator.instruction;

import nju.edu.lc3.util.BitUtil;

public class Bit_Rti extends BitInstruction{
	
	public Bit_Rti(char[] bit,int address)
	{
		this.bit = bit;
		this.address = address;
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getSource() {
		// TODO Auto-generated method stub
		return "RTI";
	}

	@Override
	public boolean validate() {
		if(BitUtil.bitArrayToInt(bit, 4, 12, false)!=0)
			return false;
		return true;
	}

}
