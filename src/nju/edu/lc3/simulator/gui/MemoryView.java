package nju.edu.lc3.simulator.gui;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

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
		addMemory(12288);
		scroll.setValue(12288);
	}
	public void addMemory(int address){
		
		memory=new MemoryShowValue[28];
		for(int i=0;i<memory.length;i++){
			memory[i]=MemoryShowValue.getMemoryShowValue(MemoryModel.getMemory(address+i));			
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
