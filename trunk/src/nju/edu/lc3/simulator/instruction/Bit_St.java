package nju.edu.lc3.simulator.instruction;

import nju.edu.lc3.simulator.gui.Application;
import nju.edu.lc3.simulator.model.MemoryModel;
import nju.edu.lc3.simulator.model.RegisterModel;
import nju.edu.lc3.util.BitUtil;

public class Bit_St extends BitInstruction{
	char[] opcode = {'0','0','1','1'};
	int sr;
	int PCoffset9;
	
	public Bit_St(char[] bit)
	{
		this.bit = bit;
		sr = BitUtil.bitArrayToInt(bit, 4, 3,false);
		PCoffset9 = BitUtil.bitArrayToInt(bit, 7, 9,true);
	}
	@Override
	public boolean execute() {
		/*mem[PC+PCofffset9]=sr*/
		int value = RegisterModel.getRegister(sr).getValue();
		int address = RegisterModel.getRegister("PC").getValue()+PCoffset9;
		MemoryModel.getMemory(address).setValue(value);
		if(address == 65030) //�����Display data register Memory map
		{
			Application.getInstance().io.text.append(value+"");
		}
		return true;
	}

	@Override
	public String getSource() {
		return "ST R"+sr+","+PCoffset9;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

}
