package nju.edu.lc3.simulator.instruction;

import nju.edu.lc3.simulator.model.RegisterModel;
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
		PCoffset9 = BitUtil.bitArrayToInt(bit, 7, 9,true);
	}

	@Override
	public boolean execute() {
		/*if((n && N)||(z && Z)||(p && P))
			PC = PC+ SEXT(PCoffset9)*/
		
		boolean n_cur = (RegisterModel.getRegister("CC").getValue()&4)==4;
		boolean z_cur = (RegisterModel.getRegister("CC").getValue()&2)==2;
		boolean p_cur = (RegisterModel.getRegister("CC").getValue()&1)==1;
		if((n && n_cur)||(z && z_cur)||(p && p_cur))
		{
			int temp = RegisterModel.getRegister("PC").getValue();
			temp+=PCoffset9;
			RegisterModel.getRegister("PC").setValue(temp);
		}
		return true;
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
