package nju.edu.lc3.simulator.gui;
import java.awt.Color;

import javax.swing.JLabel;

import nju.edu.lc3.simulator.model.RegisterModel;
import nju.edu.lc3.util.BitUtil;


public class RegisterShowValue extends ShowValue{
	RegisterModel register;
	
	public RegisterShowValue(String name){
		super(name,0);
		
		super.setValue(register.getValue());
	}
	
	public void setValue(int value)
	{
		this.register.setValue(value);
		super.setValue(value);
	}
	public void addDescription(){
		register = RegisterModel.getRegister(name);
		String hexString = BitUtil.toHexString(register.getValue());
		
		
		des=new JLabel(name+"  "+hexString+" "+register.getValue());
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setSize(100, 20);
		//this.setBorder(BorderFactory.createLineBorder(Color.blue));
		this.add(des);
		des.setBounds(0, 0, this.getWidth(), this.getHeight());
		des.setBackground(Color.white);
	}
}
