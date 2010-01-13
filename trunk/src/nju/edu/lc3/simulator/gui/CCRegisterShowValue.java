package nju.edu.lc3.simulator.gui;

import java.awt.Color;

import javax.swing.JLabel;

import nju.edu.lc3.simulator.model.RegisterModel;

public class CCRegisterShowValue extends RegisterShowValue {

	public CCRegisterShowValue(String name) {
		super(name);
		
	}
	
	public void addDescription(){
		register = RegisterModel.getRegister(name);
		int temp = register.getValue();
		String cc  = "";
		if((temp&4)>0)
			cc+="N";
		if((temp&2)>0)
			cc+="Z";
		if((temp&1)>0)
			cc+="P";
		des=new JLabel(name+"  "+cc);
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setSize(100, 20);
		//this.setBorder(BorderFactory.createLineBorder(Color.blue));
		this.add(des);
		des.setBounds(0, 0, this.getWidth(), this.getHeight());
		des.setBackground(Color.white);
	}

}
