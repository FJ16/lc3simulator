package nju.edu.lc3.simulator.gui;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JLabel;


public class RegisterShowValue extends ShowValue{
	
	public RegisterShowValue(String name){
		super(name);
	}
	
	public void addDescription(){
		des=new JLabel("   "+name+":0x0000");
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setSize(80, 20);
		//this.setBorder(BorderFactory.createLineBorder(Color.blue));
		this.add(des);
		des.setBounds(0, 0, this.getWidth(), this.getHeight());
		des.setBackground(Color.white);
	}
}
