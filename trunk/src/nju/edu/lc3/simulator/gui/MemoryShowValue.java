package nju.edu.lc3.simulator.gui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JMenuItem;


public class MemoryShowValue extends ShowValue{
	
	PicturesRes picRes = PicturesRes.getInstance();
	private JLabel  state;
	

	private static MemoryShowValue unique;//the ip can only point to one address at one time.
	public MemoryShowValue(String name){
		super(name);
		
		this.setLayout(null);
		this.setBackground(Color.white);
		//this.setBorder(BorderFactory.createLineBorder(Color.blue));
		this.setSize(370,20);
		this.addDescription();

		state=new JLabel();
		state.setIcon(picRes.getInit());
		
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
		if(unique==null){
			unique=this;
		}
		unique.state.setIcon(picRes.getInit());
		
		unique=this;
		
		state.setIcon(picRes.getRun());
	}
	
	public void addBreakPoint(){
		if(unique!=null&& unique==this)
			state.setIcon(picRes.getRun());
		else
			state.setIcon(picRes.getbPoint());
	}
	
	public void removeBreakPoint(){
		if(unique!=null&& unique==this)
			state.setIcon(picRes.getRun());
		else
			state.setIcon(picRes.getInit());
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
