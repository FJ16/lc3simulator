package nju.edu.lc3.simulator.operation;

import nju.edu.lc3.simulator.gui.MemoryModel;
import nju.edu.lc3.simulator.gui.RegisterModel;
import nju.edu.lc3.util.BitUtil;
import nju.edu.lc3.word.Bits;

public class StepRun {
	public void StepRun()
	{
		
	}
	
	private int fetch()
	{
		int result=0;
		int address = RegisterModel.getRegister("PC").getValue();
		RegisterModel.getRegister("PC").setValue(address++);
		result = MemoryModel.getMemory(address).getValue();
		return result;
	}
	
	private void decode(int value)
	{
		char[] bit = null;
		try {
			bit = Bits.TO_BIT_ARRAY(value, 16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int op = getOperation(bit);
		switch(op)
		{
		case 1:
			
		}
			
		return;
		
		
	}
	
	private int getOperation(char[] bit)
	{
		return BitUtil.bitarrayToInt(bit, 0, 4);
	}
	public static void main(String[] args)
	{
		StepRun test = new StepRun();
		test.decode(7192);
	}
}
