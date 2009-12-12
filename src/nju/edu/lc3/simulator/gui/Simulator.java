package nju.edu.lc3.simulator.gui;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;


public class Simulator extends JFrame{
	RegisterView regView;
	MemoryView memView;
	
	
	
	JPanel buttons;
	JButton open,run,setpIn,setpOut,breakPoints,insBreak,jumpTo;
	JTextField jumpDes;
	IOConsole io;
	
	public Simulator(){
		
		io=new IOConsole();
		initialize();

		final JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		final JMenu fileMenu = new JMenu();
		fileMenu.setText("File");
		menuBar.add(fileMenu);

		final JMenuItem newItemMenuItem = new JMenuItem();
		newItemMenuItem.setText("New file");
		fileMenu.add(newItemMenuItem);

		final JMenuItem newItemMenuItem_1 = new JMenuItem();
		newItemMenuItem_1.setText("Open file");
		fileMenu.add(newItemMenuItem_1);

		final JMenuItem newItemMenuItem_2 = new JMenuItem();
		newItemMenuItem_2.setText("Exit");
		fileMenu.add(newItemMenuItem_2);

		final JMenu operationMenu = new JMenu();
		operationMenu.setText("Operation");
		menuBar.add(operationMenu);

		final JMenuItem newItemMenuItem_3 = new JMenuItem();
		newItemMenuItem_3.setText("Run");
		operationMenu.add(newItemMenuItem_3);

		final JMenuItem newItemMenuItem_4 = new JMenuItem();
		newItemMenuItem_4.setText("Next Step");
		operationMenu.add(newItemMenuItem_4);

		final JMenuItem newItemMenuItem_5 = new JMenuItem();
		newItemMenuItem_5.setText("Set BP");
		operationMenu.add(newItemMenuItem_5);

		final JMenu helpMenu = new JMenu();
		helpMenu.setText("Help");
		menuBar.add(helpMenu);

		final JMenuItem newItemMenuItem_6 = new JMenuItem();
		newItemMenuItem_6.setText("About");
		helpMenu.add(newItemMenuItem_6);
	}
	
	public void initialize(){
		this.setResizable(false);
		this.setSize(400,780);
		this.setTitle("8086 Simulator");
		
		Container cp=this.getContentPane();
		cp.setLayout(null);
		cp.setBackground(Color.white);
		
		int ypos=0;
		initizlizeMenu();
		
		buttons=new JPanel();	
		cp.add(buttons);		
		buttons.setBounds(0, ypos, this.getWidth(), 30);
		buttons.setBorder(BorderFactory.createRaisedBevelBorder());
		initializeButtons();
		
		ypos+=buttons.getHeight()+5;
		
		
		regView=new RegisterView();
		cp.add(regView);
		regView.setBounds(10, 33, regView.getWidth(), 87);
		
		ypos+=regView.getHeight();

		
		
		memView=new MemoryView();
		cp.add(memView);
		memView.setBounds(0, ypos, memView.getWidth(), memView.getHeight());
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setVisible(true);
	}
	public void initializeButtons(){
		int xpos=0;
		int width=55;
		int height=25;
		int ypos=3;
		
		buttons.setLayout(null);
		
		open=new JButton("OP");
		open.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				
				//System.out.println(io.text.getText());
			}
		});
		buttons.add(open);
		open.setBounds(xpos, ypos, width, height);
		
		xpos+=width;
		run=new JButton("RUN");
		buttons.add(run);
		run.setBounds(xpos, ypos, width, height);
		
		xpos+=width;
		setpIn=new JButton("SI");
		buttons.add(setpIn);
		setpIn.setBounds(xpos, ypos, width, height);
		
		xpos+=width;
		setpOut=new JButton("SO");
		buttons.add(setpOut);
		setpOut.setBounds(xpos, ypos, width, height);
		
		xpos+=width;
		breakPoints=new JButton("BP");
		buttons.add(breakPoints);
		breakPoints.setBounds(xpos, ypos, width, height);
		
		xpos+=width;
		jumpTo=new JButton("JT");
		buttons.add(jumpTo);
		jumpTo.setBounds(xpos, ypos, width, height);
		
		xpos+=width;
		jumpDes=new JTextField();
		jumpDes.setDocument(new MyDocument(8));
		buttons.add(jumpDes);
		jumpDes.setBounds(xpos, ypos, 60, height);
		
		
	}
	
	public void initizlizeMenu(){
		int xpos=0;
		int width=80,height=25;
		
		xpos+=width;
		
		xpos+=width;
		
		xpos+=width;
	}
	
	class   MyDocument     extends   PlainDocument   {   
		int   maxLen   =   20;   
		public   MyDocument(int   maxLen){   
			this.maxLen   =   maxLen;   
		}   
		public   void   insertString(int   offset
				,   String   s,   javax.swing.text.AttributeSet   attributeSet)
		throws   javax.swing.text.BadLocationException   {   
		        //   �жϳ���   
			String   strLastText   =   super.getText(0,super.getLength());			
		   	if(strLastText.length() >= maxLen)return   ;
		   	if(s.length()+   strLastText.length()   >maxLen)return;
		   	super.insertString(offset, s, attributeSet);
		}
	}
}