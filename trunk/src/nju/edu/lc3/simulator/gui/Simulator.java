package nju.edu.lc3.simulator.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import nju.edu.lc3.simulator.operation.RunProgram;
import nju.edu.lc3.util.BitUtil;

public class Simulator extends JFrame {
	RegisterView regView;
	MemoryView memView;

	JPanel buttons;
	JButton open, run, step,setpIn, setpOut, stop, insBreak;
	JLabel jumpTo;
	JTextField jumpDes;
	IOConsole io;

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
	}

	public void initialize() {
		this.setResizable(true);
		this.setSize(405, 780);
		this.setResizable(false);
		this.setTitle("LC3 Simulator");
		Container cp = this.getContentPane();
		cp.setLayout(null);
		cp.setBackground(Color.white);
		int ypos = 0;
		initizlizeMenu();
		buttons = new JPanel();
		cp.add(buttons);
		buttons.setBounds(0, ypos, this.getWidth(), 30);
		buttons.setBorder(BorderFactory.createRaisedBevelBorder());
		initializeButtons();

		ypos += buttons.getHeight() + 5;

		regView = new RegisterView();
		cp.add(regView);
		regView.setBounds(10, 33, regView.getWidth(), 87);

		ypos += regView.getHeight();

		memView = new MemoryView();
		cp.add(memView);
		memView.setBounds(0, ypos, memView.getWidth(), memView.getHeight());

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

				// System.out.println(io.text.getText());
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
									
			}
		});
		buttons.add(run);
		run.setToolTipText("Run Program");
		run.setBounds(xpos, ypos, width, height);
		
		xpos+=width;
		step = new JButton();
		step.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				RunProgram run = new RunProgram();
				run.runOneStep();
				rePaintAll();
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

		xpos += width;
		setpOut = new JButton();
		buttons.add(setpOut);
		setpOut.setIcon(PicturesRes.getInstance().stepOut);
		setpOut.setToolTipText("Step Out");
		setpOut.setBounds(xpos, ypos, width, height);

		xpos += width;
		stop = new JButton();
		stop.addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent arg0) {
				
			}
		});
		stop.setIcon(PicturesRes.getInstance().stop);
		buttons.add(stop);
		stop.setBounds(xpos, ypos, width, height);

		xpos += width;
		jumpTo = new JLabel("JumpTo:");
		buttons.add(jumpTo);
		jumpTo.setBounds(xpos, ypos, 45, height);

		xpos += 45;
		jumpDes = new JTextField();
		jumpDes.addKeyListener(new KeyAdapter() {
			public void keyPressed(final KeyEvent e) {
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					int address;
					if (jumpDes.getText().indexOf('x') >= 0) {
						address = BitUtil.toInt(jumpDes.getText());
					} else {
						address = Integer.parseInt(jumpDes.getText());
					}
					memView.scroll.setValue(address);
				}
			}
		});
		jumpDes.setDocument(new MyDocument(8));
		buttons.add(jumpDes);
		jumpDes.setBounds(xpos, ypos, 60, height);
		

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
			// ÅÐ¶Ï³¤¶È
			String strLastText = super.getText(0, super.getLength());
			if (strLastText.length() >= maxLen)
				return;
			if (s.length() + strLastText.length() > maxLen)
				return;
			super.insertString(offset, s, attributeSet);
		}
	}
}
