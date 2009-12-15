package nju.edu.lc3.simluator.instruction;

import nju.edu.lc3.util.BitUtil;

public class Bit_Br extends BitInstruction{
	char[] opcode = {'0','0','0','0'};
	boolean n;
	boolean z;
	boolean p;
	int PCoffset9;
	
	
	public Bit_Br(char[] bit)
	{
		this.bit = bit;
		if(bit[4]=='1')
			n=true;
		if(bit[5]=='1')
			z=true;
		if(bit[6]=='1')
			p=true;
		PCoffset9 = BitUtil.bitarrayToInt(bit, 7, 9,true);
	}

	@Override
	public boolean execute() {
		/*if((n && N)||(z && Z)||(p && P))
			PC = PC+ SEXT(PCoffset9)*/
		return false;
	}

	@Override
	public String getSource() {
		// TODO Auto-generated method stub
		String result="BR";
		if(n)
			result+="n";
		if(z)
			result+="z";
		if(p)
			result+="p";
		result=result+" "+PCoffset9;
		return result;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}
}
