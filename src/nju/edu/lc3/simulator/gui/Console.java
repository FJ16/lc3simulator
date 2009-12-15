package nju.edu.lc3.simulator.gui;
import javax.swing.UIManager;
public class Console {
	public static void main(String[] args){

		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}

		new Simulator();

	}
}
