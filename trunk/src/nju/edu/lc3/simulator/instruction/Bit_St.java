package nju.edu.lc3.simulator.instruction;

import javax.swing.text.BadLocationException;

import nju.edu.lc3.simulator.gui.Application;
import nju.edu.lc3.simulator.model.MemoryModel;
import nju.edu.lc3.simulator.model.RegisterModel;
import nju.edu.lc3.util.BitUtil;

public class Bit_St extends BitInstruction{
	char[] opcode = {'0','0','1','1'};
	int sr;
	int PCoffset9;
	
	public Bit_St(char[] bit,int address)
	{
		this.bit = bit;
		this.address = address;
		sr = BitUtil.bitArrayToInt(bit, 4, 3,false);
		PCoffset9 = BitUtil.bitArrayToInt(bit, 7, 9,true);
	}
	@Override
	public boolean execute() {
		/*mem[PC+PCofffset9]=sr*/
		int value = RegisterModel.getRegister(sr).getValue();
		int address = RegisterModel.getRegister("PC").getValue()+PCoffset9;
		MemoryModel.getMemory(address).setValue(value);
		if(address == 65030) //Èç¹ûÊÇDisplay data register Memory map
		{
			char code = (char)(RegisterModel.getRegister(sr).getValue());
			
			Application.getInstance().io.isPrintable=true;
			try {
				Application.getInstance().io.text.getDocument().
				insertString(Application.getInstance().io.text.getDocument().
						getEndPosition().getOffset(), code+"", null);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Application.getInstance().io.isPrintable=false;
		}
		return true;
	}

	@Override
	public String getSource() {
		String des = Integer.toHexString(PCoffset9+address+1);
		return "ST R"+sr+",x"+des.toUpperCase();
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}

}
