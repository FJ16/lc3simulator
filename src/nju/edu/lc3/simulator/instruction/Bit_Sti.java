package nju.edu.lc3.simulator.instruction;

import javax.swing.text.BadLocationException;

import nju.edu.lc3.simulator.gui.Application;
import nju.edu.lc3.simulator.model.MemoryModel;
import nju.edu.lc3.simulator.model.RegisterModel;
import nju.edu.lc3.util.BitUtil;

public class Bit_Sti extends BitInstruction{
	char[] opcode ={'1','0','1','1'};
	int sr;
	int PCoffset9;
	
	public Bit_Sti(char[] bit)
	{
		this.bit = bit;
		sr = BitUtil.bitArrayToInt(bit, 4, 3,false);
		PCoffset9 = BitUtil.bitArrayToInt(bit, 7, 9,true);
	}
	@Override
	public boolean execute() {
		/*mem[mem[PC+PCofffset9]]=SR*/
		int value = RegisterModel.getRegister(sr).getValue();
		int index = RegisterModel.getRegister("PC").getValue()+PCoffset9;
		int address = MemoryModel.getMemory(index).getValue();
		MemoryModel.getMemory(address).setValue(value);
		if(address == 65030) //Èç¹ûÊÇDisplay data register Memory map
		{
			char code = (char)(RegisterModel.getRegister(sr).getValue());
			
			Application.getInstance().io.isPrintable=true;
			try {
				Application.getInstance().io.text.getDocument().
				insertString(Application.getInstance().io.text.getCaretPosition(), Character.toString(code), null);
				Application.getInstance().io.text.setCaretPosition(Application.getInstance().io.text.getCaretPosition()+1);
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
		return "STI R"+sr+","+PCoffset9;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		return true;
	}
}
