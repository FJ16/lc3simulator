package nju.edu.lc3.simulator.instruction;

import nju.edu.lc3.simulator.model.RegisterModel;
import nju.edu.lc3.util.BitUtil;

public class Bit_JmpRet extends BitInstruction{

	char[] opcode={'1','1','0','0'};
	int type;//0:jmp 1:ret
	int baseR;
	
	
	public Bit_JmpRet(char[] bit,int address)
	{
		this.bit=bit;
		this.address = address;
		baseR = BitUtil.bitArrayToInt(bit, 7, 3,false);
		if(baseR==7)
			type=1;
		else
			type=0;
	}
	@Override
	public boolean execute() {
		/*PC<-BaseR*/
		int temp = RegisterModel.getRegister(baseR).getValue();
		RegisterModel.getRegister("PC").setValue(temp);
		return true;
	}

	@Override
	public String getSource() {
		String result;
		if(type==0)
			result="JMP R"+baseR;
		else
			result="RET";
		return result;
	}

	@Override
	public boolean validate() {
		int temp = BitUtil.bitArrayToInt(bit, 4, 3,false);
		if(temp!=0)
			return false;
		temp = BitUtil.bitArrayToInt(bit, 10, 6,true);
		if(temp!=0)
			return false;
		return true;
	}
	
}
