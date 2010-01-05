package nju.edu.lc3.simluator.instruction;

import nju.edu.lc3.simulator.gui.MemoryModel;
import nju.edu.lc3.simulator.gui.RegisterModel;
import nju.edu.lc3.simulator.operation.MachineRun;
import nju.edu.lc3.util.BitUtil;

public class Bit_Ldi extends BitInstruction{

	char[] opcode ={'1','0','1','0'};
	int dr;
	int PCoffset9;
	
	public Bit_Ldi(char[] bit)
	{
		this.bit = bit;
		dr = BitUtil.bitArrayToInt(bit, 4, 3,false);
		PCoffset9 = BitUtil.bitArrayToInt(bit, 7, 9,true);
	}
	@Override
	public boolean execute() {
		/*DR=mem[mem[PC+PCofffset9]]*/
		int pc =RegisterModel.getRegister("PC").getValue();
		int address = MemoryModel.getMemory(pc+PCoffset9).getValue();
		int value = MemoryModel.getMemory(address).getValue();
		RegisterModel.getRegister(dr).setValue(value);
		MachineRun.getInstance().setcc(RegisterModel.getRegister(dr).getValue());
		return true;
	}

	@Override
	public String getSource() {
		return "LDI R"+dr+","+PCoffset9;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}
}
