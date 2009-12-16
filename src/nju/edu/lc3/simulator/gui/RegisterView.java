package nju.edu.lc3.simulator.gui;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;


public class RegisterView extends JPanel{
	RegisterShowValue[] registers;
	public RegisterView(){
		
		
		initialize();
	}
	
	public void initialize(){
		int row=5;
		int col=3;
		this.setLayout(new GridLayout(row,col));
		this.setBackground(Color.white);
		registers=new RegisterShowValue[12];
		int i=0;
		registers[i++]=new RegisterShowValue("R0");
		registers[i++]=new RegisterShowValue("R4");
		registers[i++]=new RegisterShowValue("PC");
		registers[i++]=new RegisterShowValue("R1");
		registers[i++]=new RegisterShowValue("R5");
		registers[i++]=new RegisterShowValue("IR");
		registers[i++]=new RegisterShowValue("R2");
		registers[i++]=new RegisterShowValue("R6");
		registers[i++]=new RegisterShowValue("PSR");
		registers[i++]=new RegisterShowValue("R3");
		registers[i++]=new RegisterShowValue("R7");
		registers[i++]=new RegisterShowValue("CC");
		
		
		for(i=0;i<registers.length;i++){
			this.add(registers[i]);
		}
		
		
		this.setSize(380,registers[0].getHeight()*row);
	}
}
