package nju.edu.lc3.simluator.instruction;

import nju.edu.lc3.util.BitUtil;

public class Bit_Add extends BitInstruction{
	char[] opcode = {'0','0','0','1'};
	int dr;
	int sr1;
	int sr2;
	int imm5;
	int type;
	

	public String getSource()
	{
		String result="ADD ";
		if(type ==0)
		{
			result=result+"R"+dr+","+"R"+sr1+","+"R"+sr2;
		}
		else
		{
			result=result+"R"+dr+","+"R"+sr1+",#"+imm5;
		}
		return result;
		
	}
	
	
	public Bit_Add(char[] bit)
	{
		this.bit = bit;
		dr = BitUtil.bitarrayToInt(bit, 4, 3,false);
		sr1 = BitUtil.bitarrayToInt(bit, 7, 3,false);
		if(bit[10]=='0')
		{
			sr2 = BitUtil.bitarrayToInt(bit, 13, 3,false);
			type =0;
		}
		else
		{
			imm5 = BitUtil.bitarrayToInt(bit, 11, 5,true);
			type =1;
		}
	}
	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean validate() {
		
		if(bit[BIT_LENGTH-6]=='0')
		{
			if(bit[BIT_LENGTH-5]!='0'||bit[BIT_LENGTH-5]!='0')
				return false;
		}
		return true;
	}
	
}
