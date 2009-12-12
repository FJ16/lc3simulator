package nju.edu.lc3.simulator.gui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;


public class MemoryShowValue extends ShowValue{
	
	private Icon bPoint;//break point
	private Icon init;
	private Icon run;
	private JLabel  state;
	
	private static Icon pointTo;
	private static MemoryShowValue unique;//the ip can only point to one address at one time.
	public MemoryShowValue(String name){
		super(name);
		
		this.setLayout(null);
		this.setBackground(Color.white);
		//this.setBorder(BorderFactory.createLineBorder(Color.blue));
		this.setSize(370,20);
		this.addDescription();
		bPoint=new ImageIcon("breakPoint.gif");
		init=new ImageIcon("init.gif");
		
		state=new JLabel();
		state.setIcon(init);
		
		this.add(state);
		state.setBounds(0, 0, 20, 20);
	}
	public void getRightMenu(){
		super.getRightMenu();
		
		JMenuItem item;
		
		item=new JMenuItem("Set PC to the select position");
		rightMenu.add(item);
		item.addActionListener(new JumpTo());
		
		item=new JMenuItem("Add BreakPoint");
		rightMenu.add(item);
		item.addActionListener(new AddPoint());
		
		item=new JMenuItem("Remove BreakPoint");
		rightMenu.add(item);
		item.addActionListener(new RemovePoint());
		
	}
	public void addDescription(){
		des=new JLabel(""+name+"            0x0000                NOP");	
		this.add(des);
		des.setBounds(30, 0, this.getWidth()-20, this.getHeight());
	}
	public void pointTo(){//the current ip point to this address
		if(pointTo==null){
			pointTo=new ImageIcon("pointto.gif");
			unique=this;
		}
		unique.state.setIcon(init);
		
		unique=this;
		
		state.setIcon(pointTo);
	}
	
	public void addBreakPoint(){
		if(unique!=null&& unique==this)
			state.setIcon(pointTo);
		else
			state.setIcon(bPoint);
	}
	
	public void removeBreakPoint(){
		if(unique!=null&& unique==this)
			state.setIcon(pointTo);
		else
			state.setIcon(init);
	}
	public void setBackGround(Color col){
		des.setBackground(col);
	}
	
	
	
	class AddPoint implements ActionListener{
		public void actionPerformed(ActionEvent e){
			MemoryShowValue.this.addBreakPoint();
		}
	}
	class RemovePoint implements ActionListener{
		public void actionPerformed(ActionEvent e){
			MemoryShowValue.this.removeBreakPoint();
		}
	}
	
	class JumpTo implements ActionListener{
		public void actionPerformed(ActionEvent e){
			MemoryShowValue.this.pointTo();
		}
	}
}
