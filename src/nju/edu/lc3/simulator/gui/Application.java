package nju.edu.lc3.simulator.gui;
import javax.swing.UIManager;
public class Application {
	
	private static Simulator instance;
	public static Simulator getInstance()
	{
		return instance;
	}
	public static void main(String[] args){

		try{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception ee)
		{
			ee.printStackTrace();
		}

		instance = new Simulator();

	}
}
