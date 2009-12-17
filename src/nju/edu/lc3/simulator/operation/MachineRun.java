package nju.edu.lc3.simulator.operation;

import nju.edu.lc3.simulator.gui.RegisterModel;

public class MachineRun {
	
	private static final MachineRun instance = new MachineRun();
	public static MachineRun getInstance()
	{
		return instance;
	}
	public void setcc(int value)
	{
		if(value == 0)
		{
			int temp = RegisterModel.getRegister("PSR").getValue();
			RegisterModel.getRegister("PSR").setValue(temp|2);
		}
		else if(value>0)
		{
			int temp = RegisterModel.getRegister("PSR").getValue();
			RegisterModel.getRegister("PSR").setValue(temp|1);
		}
		else if(value<0)
		{
			int temp = RegisterModel.getRegister("PSR").getValue();
			RegisterModel.getRegister("PSR").setValue(temp|4);
		}
	}
}
