package nju.edu.lc3.simulator.instruction;

import nju.edu.lc3.simulator.model.RegisterModel;
import nju.edu.lc3.simulator.operation.MachineRun;
import nju.edu.lc3.util.BitUtil;

public class Bit_Lea extends BitInstruction{
	char[] opode={'1','1','1','0'};
	int dr;
	int PCoffset9;
	
	public Bit_Lea(char[] bit,int address)
	{
		this.bit= bit;
		this.address = address;
		dr = BitUtil.bitArrayToInt(bit, 4, 3, false);
		PCoffset9 = BitUtil.bitArrayToInt(bit, 7, 9, true);
	}

	@Override
	public boolean execute() {
		/*DR = PC+ PCoffset9;setcc()*/
		int value = RegisterModel.getRegister("PC").getValue()+PCoffset9;
		RegisterModel.getRegister(dr).setValue(value);
		MachineRun.getInstance().setcc(RegisterModel.getRegister(dr).getValue());
		return true;
	}

	@Override
	public String getSource() {
		String des = Integer.toHexString(PCoffset9+address+1);
		return "LEA R"+dr+",x"+des.toUpperCase();
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

}
