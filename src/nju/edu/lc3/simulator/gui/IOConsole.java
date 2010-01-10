package nju.edu.lc3.simulator.gui;

import java.awt.Container;
import java.awt.TextArea;

import javax.swing.JFrame;

public class IOConsole extends JFrame {
	public TextArea text;
	



	public IOConsole() {
		this.setSize(600, 400);
		Container cp = this.getContentPane();
		text = new TextArea();
		
		cp.setLayout(null);
		cp.add(text);
		text.setBounds(0, 0, this.getWidth(), this.getHeight());
		//text.setDocument(new MyDocument());
		this.setResizable(false);
		this.setTitle("Simulator Console");
		this.setVisible(true);
		
	}

	public void output(String mess) {

	}


	/*public static void main(String[] args) {
		Console console;
		char[] pwd;
		if ((console = System.console()) != null
				&&(pwd = console
						.readPassword("[%s]", "Please Enter Password:")) != null) {
			System.out.println(String.valueOf(pwd));
		}
	}*/

}
