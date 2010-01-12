package nju.edu.lc3.simulator.gui;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.PlainDocument;

import nju.edu.lc3.simulator.model.MemoryModel;

public class IOConsole extends JFrame {
	
	public JTextArea text;
	public boolean isPrintable;
	class MyDocument extends PlainDocument {
		 
        public void insertString(int offs, String str, AttributeSet a) 
            throws BadLocationException {

/*            if (str == null) {
                return;
            }
            char[] upper = str.toCharArray();
            for (int i = 0; i < upper.length; i++) {
                upper[i] = Character.toUpperCase(upper[i]);
            }
            super.insertString(offs, new String(upper), a);*/
        	
        	if(isPrintable)
        	{
        		super.insertString(offs, str, a);
        	}
        	else
        	{
        		
        	}
        }
    }
	
	public IOConsole() {
		super();
		this.setSize(600,400);
		this.setResizable(false);
		this.setTitle("LC3 Console");
		final JScrollPane scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		text = new JTextArea();
		text.addKeyListener(new KeyAdapter() {
			public void keyTyped(final KeyEvent arg0) {
				int value = arg0.getKeyChar();
				MemoryModel.getMemory(65026).setValue(value);
				MemoryModel.getMemory(65024).setValue(32768);
				
			}
		});
		scrollPane.setViewportView(text);
		text.setDocument(new MyDocument());
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		  
		
	}

	public void output(String mess) {

	}
	
	

}
