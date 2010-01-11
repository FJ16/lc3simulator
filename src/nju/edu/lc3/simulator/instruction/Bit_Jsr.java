package nju.edu.lc3.simulator.instruction;

import nju.edu.lc3.simulator.model.RegisterModel;
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
			PCoffset11 = BitUtil.bitArrayToInt(bit, 5, 11,true);
		}
		else
		{
			type=0;
			baseR=BitUtil.bitArrayToInt(bit, 7, 3,false);
		}
	}

	@Override
	public boolean execute() {
		/*先把PC的值放入R7，然后跳转*/
		int pc =RegisterModel.getRegister("PC").getValue();
		RegisterModel.getRegister(7).setValue(pc);
		if(type == 1)
		{
			RegisterModel.getRegister("PC").setValue(pc+PCoffset11);
		}
		if(type == 0)
		{
			int address = RegisterModel.getRegister(baseR).getValue();
			RegisterModel.getRegister("PC").setValue(address);
		}
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
			result="JSRR R"+baseR;
		}
		return result;
	}

	@Override
	public boolean validate() {
		if(bit[5]=='0'){
			int temp=BitUtil.bitArrayToInt(bit, 6, 2,false);
			if(temp!=0)
				return false;
			temp = BitUtil.bitArrayToInt(bit, 10, 6,false);
			return false;
		}
		return true;
	}
	
}
