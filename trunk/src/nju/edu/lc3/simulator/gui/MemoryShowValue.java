package nju.edu.lc3.simulator.gui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JLabel;
import javax.swing.JMenuItem;

import nju.edu.lc3.simulator.instruction.BitInstruction;
import nju.edu.lc3.simulator.model.MemoryModel;
import nju.edu.lc3.simulator.model.RegisterModel;
import nju.edu.lc3.simulator.operation.Translator;
import nju.edu.lc3.util.BitUtil;


public class MemoryShowValue extends ShowValue{
	
	PicturesRes picRes = PicturesRes.getInstance();
	private JLabel  state;
	int address;
	
	BitInstruction ins;
	public BitInstruction getIns()
	{
		if(ins==null)
			ins = Translator.decode(value);
		return ins;
	}
	//static int CURRENT_POINT=3000;

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
		this.setSize(370,18);
		this.addDescription();

		state=new JLabel();
		state.addMouseListener(new MouseAdapter() {
			public void mouseClicked(final MouseEvent e) {
				
				if(e.getClickCount()==2&&e.getButton()==MouseEvent.BUTTON1){
					if(MemoryModel.getMemory(address).isBreakPoint())
					{
						MemoryShowValue.this.removeBreakPoint();
					}
					else{
					MemoryShowValue.this.addBreakPoint();
					}
				}
			}
		});
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
		
		ins = Translator.decode(value);
		String op=ins.getSource();//×ª»¯³É»ã±à
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
			state.setIcon(picRes.pointto);
		else if(MemoryModel.getMemory(address).isBreakPoint())
			state.setIcon(picRes.bPoint);
		else
			state.setIcon(picRes.init);
		
		this.add(state);
		state.setBounds(0, 0, 20, 20);
	}
	public void pointTo(){//the current ip point to this address
		RegisterModel.getRegister("PC").setValue(this.address);
		((Simulator)(this.getTopLevelAncestor())).rePaintAll();
		setStatePic();
		
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
