package nju.edu.lc3.simulator.gui;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.text.PlainDocument;


public class IOConsole extends JFrame{
	public JTextArea text;
	boolean isReadable;
	
	class  MyDocument extends PlainDocument{
		public MyDocument(){
			
		}

		public void insertString(int offset, String  mess, javax.swing.text.AttributeSet attributeSet)
		throws  javax.swing.text.BadLocationException{
			if(read()){
				super.insertString(offset, mess, attributeSet);
			}
		}
	}
	public IOConsole(){
		this.setSize(600,400);
		Container cp=this.getContentPane();
		text=new JTextArea();
		cp.setLayout(null);
		cp.add(text);
		text.setBounds(0, 0, this.getWidth(), this.getHeight());
		text.setDocument(new MyDocument());
		
		this.setResizable(false);
		this.setTitle("Simulator Console");
		this.setVisible(true);
		isReadable = false;
	}
	
	public void setReadable(boolean isReadable)
	{
		this.isReadable = isReadable;
	}
	
	public boolean read(){
		
		return isReadable;
	}
	public void output(String mess){
		
	}
	
	protected void processWindowEvent(WindowEvent e){
	}
}
