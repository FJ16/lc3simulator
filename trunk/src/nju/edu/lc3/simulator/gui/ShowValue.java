package nju.edu.lc3.simulator.gui;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;



public abstract class ShowValue extends JPanel{
	String name;
	/**register's name is register. such as ax 's name is ax
	 * memory's name is the address of the memory.
	 * */
	JLabel des;
	JPopupMenu   rightMenu;
	
	static ShowValue unique=null;
	public ShowValue(String name){
		this.name=name;
		initialize();
	}
	public void initialize(){
		addDescription();
		this.addMouseListener(new ChangeValue());
		getRightMenu();
	}
	public void getRightMenu(){
		JMenuItem item;
		rightMenu=new JPopupMenu();
		
		rightMenu.addMouseListener(new ChangeValue());
		
		item=new JMenuItem("Set Value");
		rightMenu.add(item);
		item.addActionListener(new SetValue());
		
		
	}
	public abstract void addDescription();
	public int getValue(){
		return 0;
	}
	public void setValue(){
		ShowValue.this.setBackground(Color.white);
		SetValueView temp=SetValueView.getInstance();
		temp.setVisible(true);
		temp.setDestination(name);
	}
	private void highLight(){
		if(unique!=null){
			unique.setBackground(Color.white);
		}
		unique=ShowValue.this;
		unique.setBackground(Color.LIGHT_GRAY);
	}
	public void action(){
		
	}
	
	class SetValue implements ActionListener{
		public void actionPerformed(ActionEvent e){
			setValue();
		}
	}
	
	class ChangeValue implements MouseListener{
		public void mousePressed(MouseEvent e){		
			if(e.getButton()==MouseEvent.BUTTON3){
				highLight();
				rightMenu.show(ShowValue.this,e.getX()+1,e.getY()+1);
			}
		}
		public void mouseEntered(MouseEvent e){
			
		}
		public void mouseExited(MouseEvent e){
			
		}
		public void mouseClicked(MouseEvent e){
			if(e.getClickCount()==1&&e.getButton()==MouseEvent.BUTTON1){
				highLight();
			}
			else if(e.getClickCount()==2&&e.getButton()==MouseEvent.BUTTON1){
				setValue();
			}
			
		}
		public void mouseReleased(MouseEvent e){
			
		}
	}
}
