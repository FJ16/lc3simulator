package nju.edu.lc3.simulator.gui;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;


public class MemoryView extends JPanel implements Scrollable{
	MemoryShowValue [] memory;
	MyScrollBar scroll;
	public MemoryView(){
		initialize();
	}
	
	public void initialize(){
		this.setLayout(null);
		
		scroll=new MyScrollBar(this,65518);
		addMemory(3000);
		scroll.setValue(3000);
	}
	public void addMemory(int address){
		memory=new MemoryShowValue[28];
		for(int i=0;i<memory.length;i++){
			String name=Integer.toHexString(i+address);
			for(;name.length()<4;name="0"+name);
			memory[i]=new MemoryShowValue("0000:"+name);			
			this.add(memory[i]);
			memory[i].setBounds(0, memory[i].getHeight()*i, memory[i].getWidth(), memory[i].getHeight());
		}
		
		this.add(scroll);
		scroll.setBounds(memory[0].getWidth(), 0, 20, memory[0].getHeight()*memory.length);
		
		this.setSize(memory[0].getWidth()+scroll.getWidth(),memory[0].getHeight()*memory.length);
		this.setBackground(Color.white);
	}
	
	public void scroll(){
		int value=scroll.getValue();
		
		this.removeAll();
		addMemory(value);
	}
}
