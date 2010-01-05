package nju.edu.lc3.simulator.gui;

import java.util.ArrayList;
import java.util.List;

public class RegisterModel {
	private String name;
	private int value;
	
	private static List<RegisterModel> registerModel = new ArrayList<RegisterModel>(12);
	static 
	{
		for(int i =0;i<8;i++)
		{
			registerModel.add(new RegisterModel("R"+i,0));
		}
		registerModel.add(new RegisterModel("PC",12288));
		registerModel.add(new RegisterModel("IR",0));
		registerModel.add(new RegisterModel("PSR",0));
		registerModel.add(new RegisterModel("CC",2));
	}
	
	public static RegisterModel getRegister(String name)
	{
		RegisterModel result = registerModel.get(0);
		for(RegisterModel temp:registerModel)
		{
			if(temp.getName().equals(name))
				result = temp;
		}
		return result;
	}
	
	public static RegisterModel getRegister(int index)
	{
		return(getRegister("R"+index));
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public RegisterModel(String name,int value)
	{
		this.name = name;
		this.value = value;
	}
}
