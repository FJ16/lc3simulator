package nju.edu.lc3.simulator.gui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import nju.edu.lc3.util.BitUtil;


public class MemoryShowValue extends ShowValue{
	
	PicturesRes picRes = PicturesRes.getInstance();
	private JLabel  state;
	int address;
	MemoryModel memoryModel;
	static int CURRENT_POINT=3000;

	private static MemoryModel unique;//the ip can only point to one address at one time.
	
	
	public static MemoryShowValue getMemoryShowValue(MemoryModel memory)
	{
		MemoryShowValue memoryShowValue = new MemoryShowValue(memory);
		return memoryShowValue;
	}
	private MemoryShowValue(MemoryModel memory){
		
		super(memory.getName(),memory.getValue());
		this.address = memory.getAddress();
		this.setLayout(null);
		this.setBackground(Color.white);
		this.setSize(370,20);
		this.addDescription();

		state=new JLabel();
		setStatePic();
	}
	public void setValue(int value)
	{
		MemoryModel.getMemory(address).setValue(value);
		super.setValue(value);
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
		String temp=Integer.toHexString(address);
		for(;temp.length()<4;temp="0"+temp);
		String op="NOP";//×ª»¯³É»ã±à
		int value = MemoryModel.getMemory(address).getValue();
		String binValue = BitUtil.toBinString(value);
		String hexValue = BitUtil.toHexString(value);
		des=new JLabel(temp+"   "+binValue+"   "+hexValue+"   "+op);	
		this.add(des);
		des.setBounds(30, 0, this.getWidth()-20, this.getHeight());
	}
	
	public void setStatePic()
	{
		if(MemoryModel.getMemory(address).isCurrent())
			state.setIcon(picRes.getRun());
		else if(MemoryModel.getMemory(address).isBreakPoint())
			state.setIcon(picRes.getbPoint());
		else
			state.setIcon(picRes.getInit());
		
		this.add(state);
		state.setBounds(0, 0, 20, 20);
	}
	public void pointTo(){//the current ip point to this address
		CURRENT_POINT = this.address;
		setStatePic();
		((MemoryView)this.getParent()).scroll();
	}
	

	public void addBreakPoint(){
		MemoryModel.getMemory(address).setBreakPoint(true);
		setStatePic();
	}
	
	public void removeBreakPoint(){
		MemoryModel.getMemory(address).setBreakPoint(false);
		setStatePic();
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
