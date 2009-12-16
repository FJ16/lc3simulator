package nju.edu.lc3.simluator.instruction;

import nju.edu.lc3.util.BitUtil;

public class Bit_Jsr extends BitInstruction{
	
	int type;//1:JSR 0:JSRR
	int PCoffset11;
	int baseR;
	public Bit_Jsr(char[] bit)
	{
		this.bit =bit;
		if(bit[4]=='1')
		{
			type=1;
			PCoffset11 = BitUtil.bitarrayToInt(bit, 5, 11,true);
		}
		else
		{
			type=0;
			baseR=BitUtil.bitarrayToInt(bit, 7, 3,false);
		}
	}

	@Override
	public boolean execute() {
		/*�Ȱ�PC��ֵ����R7��Ȼ����ת*/
		return true;
	}

	@Override
	public String getSource() {
		String result;
		if(type==1)
		{
			result="JSR "+PCoffset11;
		}
		else
		{
			result="JSRR "+baseR;
		}
		return result;
	}

	@Override
	public boolean validate() {
		if(bit[5]=='0'){
			int temp=BitUtil.bitarrayToInt(bit, 6, 2,false);
			if(temp!=0)
				return false;
			temp = BitUtil.bitarrayToInt(bit, 10, 6,false);
			return false;
		}
		return true;
	}
	
}