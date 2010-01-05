package nju.edu.lc3.simluator.instruction;

import nju.edu.lc3.simulator.gui.RegisterModel;
import nju.edu.lc3.simulator.operation.MachineRun;
import nju.edu.lc3.util.BitUtil;

public class Bit_Not extends BitInstruction{
	char[] opcode = {'1','0','0','1'};
	int dr;
	int sr;
	public Bit_Not(char[] bit)
	{
		this.bit = bit;
		dr = BitUtil.bitArrayToInt(bit, 4, 3, false);
		sr = BitUtil.bitArrayToInt(bit, 7, 3, false);
	}

	@Override
	public boolean execute() {
		/*DR = NOT(SR) ;setcc()*/
		int value= RegisterModel.getRegister(sr).getValue();
		RegisterModel.getRegister(dr).setValue(~value);
		MachineRun.getInstance().setcc(RegisterModel.getRegister(dr).getValue());
		return true;
	}

	@Override
	public String getSource() {
		
		return "NOT R"+dr+",R"+sr;
	}

	@Override
	public boolean validate() {
		for(int i=10;i<16;i++)
		{
			if(bit[i]=='0')
				return false;
		}
		return true;
	}
	
}
