package nju.edu.lc3.simulator.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.PlainDocument;

import nju.edu.lc3.simulator.operation.FileManager;
import nju.edu.lc3.simulator.operation.MachineRun;
import nju.edu.lc3.simulator.operation.RunProgram;
import nju.edu.lc3.util.BitUtil;

public class Simulator extends JFrame {
	public IOConsole io;
	public RegisterView regView;
	public MemoryView memView;
	public JLabel s2;
	public JLabel s3;
	
	Thread thread;

	JToolBar buttons;
	JButton open, run, step,setpIn, setpOut, stop, insBreak;
	JLabel jumpTo;
	JComboBox jumpDes;
	

	public Simulator() {

		io = new IOConsole();
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
		
		try {
			FileManager.getInstance().loadOS();
			this.repaint();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(this,e.getMessage());
		}
	}

	public void initialize() {
		Point l = new Point();
		l.setLocation(620,0);
			
		this.setLocation(l);
		this.setResizable(true);
		this.setSize(396, 693);
		this.setResizable(false);
		this.setTitle("LC3 Simulator");
		Container cp = this.getContentPane();
		cp.setLayout(null);
		cp.setBackground(Color.white);
		int ypos = 0;
		initizlizeMenu();
		buttons = new JToolBar();
		buttons.setFloatable(false);
		cp.add(buttons);
		buttons.setBounds(0, 0, this.getWidth(), 30);
		buttons.setBorder(BorderFactory.createRaisedBevelBorder());
		initializeButtons();
		ypos += buttons.getHeight() + 5;

		regView = new RegisterView();
		cp.add(regView);
		regView.setBounds(0, 36, regView.getWidth(), 82);

		ypos += regView.getHeight();

		memView = new MemoryView();
		cp.add(memView);
		memView.setBounds(0, ypos, memView.getWidth(), memView.getHeight());
		
		ypos +=memView.getHeight();
		JToolBar statusBar = new JToolBar();
		statusBar.setLayout(null);
		JLabel s1 = new JLabel("Ready");
		s1.setBounds(2, 2, 40, 15);
		statusBar.add(s1);
		JSeparator sp = new JSeparator(SwingConstants.VERTICAL);
		sp.setBounds(45, 0, 3, 30);
		statusBar.add(sp);
		
		s2 = new JLabel("0 instructions exectued");
		s2.setHorizontalAlignment(SwingConstants.RIGHT);
		s2.setBounds(50, 2, 200, 15);
		statusBar.add(s2);
		
		
		s3 = new JLabel("Idle");
		s3.setHorizontalAlignment(SwingConstants.RIGHT);
		s3.setBounds(260, 2, 60, 15);
		statusBar.add(s3);
		JSeparator sp2 = new JSeparator(SwingConstants.VERTICAL);
		sp2.setBounds(253, 0, 3, 30);
		statusBar.add(sp2);
		
		statusBar.setBounds(0,ypos,396,30);
		cp.add(statusBar);
		statusBar.setFloatable(false);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void rePaintAll()
	{
		for(ShowValue reg :regView.registers)
		{
			reg.rePaint();
		}
		memView.scroll();
		memView.scroll.repaint();
		
	}

	public void initializeButtons() {
		int xpos = 0;
		int width = 25;
		int height = 25;
		int ypos = 3;

		buttons.setLayout(null);

		open = new JButton();
		open.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				final JFileChooser fc = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("bin file","bin");
				fc.setFileFilter(filter);
				fc.setCurrentDirectory(new File("."));
				int returnVal = fc.showOpenDialog(Simulator.this);
				
				if(returnVal==JFileChooser.CANCEL_OPTION)
				{
					return;
				}
				else if(returnVal==JFileChooser.APPROVE_OPTION)
				{
					
					String file= fc.getSelectedFile().getPath();
					try {
						FileManager.getInstance().loadFile(file);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				else 
				{
					JOptionPane.showMessageDialog(null, "发生错误！","ERROR",JOptionPane.ERROR_MESSAGE);
				}

			

			}
		});
		
		buttons.add(open);
		open.setIcon(PicturesRes.getInstance().open);
		open.setToolTipText("Open File");
		open.setBounds(xpos, ypos, width, height);

		xpos += width;
		run = new JButton();
		run.setIcon(PicturesRes.getInstance().run);
		run.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				thread = new Thread(){
					public void run(){
						RunProgram run = RunProgram.getInstance();
						run.runAll();
						MachineRun.getInstance().gotoPCLine();
						rePaintAll();
					}
					
				};
				thread.start();
				
			}
		});
		buttons.add(run);
		run.setToolTipText("Run Program");
		run.setBounds(xpos, ypos, width, height);
		
		xpos+=width;
		step = new JButton();
		step.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				thread = new Thread(){
					public void run(){
						RunProgram run = RunProgram.getInstance();
						run.runOneStep();
						MachineRun.getInstance().gotoPCLine();
						rePaintAll();
					}
					
				};
				thread.start();
				
			}
		});
		step.setIcon(PicturesRes.getInstance().stepOver);
		step.setToolTipText("Step Over");
		step.setBounds(xpos, ypos, width, height);
		buttons.add(step);

		xpos += width;
		setpIn = new JButton();
		setpIn.setIcon(PicturesRes.getInstance().stepInto);
		setpIn.setToolTipText("Step Into");
		buttons.add(setpIn);
		setpIn.setBounds(xpos, ypos, width, height);
		setpIn.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				RunProgram run = RunProgram.getInstance();
				run.runInto();
				MachineRun.getInstance().gotoPCLine();
				rePaintAll();
			}
		});

		xpos += width;
		setpOut = new JButton();
		buttons.add(setpOut);
		setpOut.setIcon(PicturesRes.getInstance().stepOut);
		setpOut.setToolTipText("Step Out");
		setpOut.setBounds(xpos, ypos, width, height);
		setpOut.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				RunProgram run = RunProgram.getInstance();
				run.runOut();
				MachineRun.getInstance().gotoPCLine();
				rePaintAll();
			}
		});

		xpos += width;
		stop = new JButton();
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				if(thread!=null)
					thread.stop();
				MachineRun.getInstance().gotoPCLine();
				Application.getInstance().s3.setText("Idle");
				rePaintAll();
			}
		});
		stop.setIcon(PicturesRes.getInstance().stop);
		buttons.add(stop);
		stop.setToolTipText("Stop");
		stop.setBounds(xpos, ypos, width, height);

		xpos += width;
		jumpTo = new JLabel("JumpTo:");
		buttons.add(jumpTo);
		jumpTo.setBounds(xpos, ypos, 45, height);

		xpos += 45;
		jumpDes = new JComboBox();
		jumpDes.addItem("x3000");
		jumpDes.setEditable(true);
		jumpDes.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					int address;
					String gotoLine = (String)jumpDes.getEditor().getItem();
					if (gotoLine.indexOf('x') >= 0) {
						address = BitUtil.toInt(gotoLine);
					} else {
						address = Integer.parseInt(gotoLine);
					}
					memView.scroll.setValue(address);
					jumpDes.insertItemAt(jumpDes.getEditor().getItem(),0);
				}
			}
		});
		
		//jumpDes.setDocument(new MyDocument(8));
		buttons.add(jumpDes);
		jumpDes.setBounds(xpos, ypos+2, 60, height-5);
		

	}
	

	public void initizlizeMenu() {
		int xpos = 0;
		int width = 80, height = 25;
		xpos += width;
		xpos += width;
		xpos += width;
	}

	class MyDocument extends PlainDocument {
		int maxLen = 20;

		public MyDocument(int maxLen) {
			this.maxLen = maxLen;
		}

		public void insertString(int offset, String s,
				javax.swing.text.AttributeSet attributeSet)
				throws javax.swing.text.BadLocationException {
			// 判断长度
			String strLastText = super.getText(0, super.getLength());
			if (strLastText.length() >= maxLen)
				return;
			if (s.length() + strLastText.length() > maxLen)
				return;
			super.insertString(offset, s, attributeSet);
		}
	}
}
