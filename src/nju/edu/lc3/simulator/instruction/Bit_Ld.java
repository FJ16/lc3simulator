package nju.edu.lc3.simulator.instruction;

import nju.edu.lc3.simulator.model.MemoryModel;
import nju.edu.lc3.simulator.model.RegisterModel;
import nju.edu.lc3.simulator.operation.MachineRun;
import nju.edu.lc3.util.BitUtil;

public class Bit_Ld extends BitInstruction{

	char[] opcode ={'0','0','1','0'};
	int dr;
	int PCoffset9;
	
	public Bit_Ld(char[] bit)
	{
		this.bit = bit;
		dr = BitUtil.bitArrayToInt(bit, 4, 3,false);
		PCoffset9 = BitUtil.bitArrayToInt(bit, 7, 9,true);
	}
	@Override
	public boolean execute() {
		/*DR=mem[PC+PCofffset9]*/
		int pc =RegisterModel.getRegister("PC").getValue();
		int value = MemoryModel.getMemory(pc+PCoffset9).getValue();
		RegisterModel.getRegister(dr).setValue(value);
		MachineRun.getInstance().setcc(RegisterModel.getRegister(dr).getValue());
		
		
		if(pc+PCoffset9==65026) //如果加载的地址是KBDR的内存映射
		{
			MemoryModel.getMemory(65024).setValue(0);
		}
		
		if(pc+PCoffset9==65534) //如果加载的地址是MCR的内存映射
		{
			RegisterModel.getRegister("MCR").setValue(0); //halt the mechine
		}
		
		return true;
	}

	@Override
	public String getSource() {
		return "LD R"+dr+","+PCoffset9+"+PC";
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
