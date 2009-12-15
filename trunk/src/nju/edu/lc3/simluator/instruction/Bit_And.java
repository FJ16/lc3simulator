package nju.edu.lc3.simluator.instruction;

import nju.edu.lc3.util.BitUtil;

public class Bit_And extends BitInstruction {
	char[] opcode = {'0','1','0','1'};
	int dr;
	int sr1;
	int sr2;
	int imm5;
	char[] bit;
	int type;
	
	

	public Bit_And(char[] bit)
	{
		this.bit = bit;
		dr = BitUtil.bitarrayToInt(bit, 4, 3);
		sr1 = BitUtil.bitarrayToInt(bit, 7, 3);
		if(bit[10]=='0')
		{
			sr2 = BitUtil.bitarrayToInt(bit, 13, 3);
			type =0;
		}
		else
		{
			imm5 = BitUtil.bitarrayToInt(bit, 11, 5);
			type =1;
		}
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String getSource()
	{
		String result="AND ";
		if(type ==0)
		{
			result=result+"R"+dr+" "+"R"+sr1+" "+"R"+sr2;
		}
		else
		{
			result=result+"R"+dr+" "+"R"+sr1+" "+"#"+imm5;
		}
		return result;
		
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
