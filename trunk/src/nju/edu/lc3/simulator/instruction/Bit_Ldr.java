package nju.edu.lc3.simulator.instruction;

import nju.edu.lc3.simulator.model.MemoryModel;
import nju.edu.lc3.simulator.model.RegisterModel;
import nju.edu.lc3.simulator.operation.MachineRun;
import nju.edu.lc3.util.BitUtil;

public class Bit_Ldr extends BitInstruction{
	char[] opcode ={'0','1','1','0'};
	int dr;
	int baseR;
	int offset6;
	
	public Bit_Ldr(char[] bit,int address)
	{
		this.bit = bit;
		this.address = address;
		dr = BitUtil.bitArrayToInt(bit, 4, 3,false);
		baseR = BitUtil.bitArrayToInt(bit, 7, 3,false);
		offset6 = BitUtil.bitArrayToInt(bit, 10, 6,true);
	}
	@Override
	public boolean execute() {
		/*DR=mem[BaseR+offset6]*/
		int address = RegisterModel.getRegister(baseR).getValue()+offset6;
		int value = MemoryModel.getMemory(address).getValue();
		RegisterModel.getRegister(dr).setValue(value);
		MachineRun.getInstance().setcc(RegisterModel.getRegister(dr).getValue());
		return true;
	}

	@Override
	public String getSource() {
		return "LDR R"+dr+",R"+baseR+",#"+offset6;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

}
