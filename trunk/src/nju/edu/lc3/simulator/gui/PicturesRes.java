package nju.edu.lc3.simulator.gui;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class PicturesRes {
	private static PicturesRes instance;
	Icon bPoint;//break point
	Icon init;
	Icon pointto;
	Icon open;
	Icon run;
	Icon stepOver;
	Icon stepInto;
	Icon stepOut;
	
	private PicturesRes()
	{
		initIcon();
	}
	
	
	private void initIcon()
	{
		bPoint=new ImageIcon("breakPoint.gif");
		init=new ImageIcon("init.gif");
		pointto = new ImageIcon("pointto.gif");
		open = new ImageIcon("open.png");
		run = new ImageIcon("run.png");
		stepOver = new ImageIcon("stepOver.png");
		stepInto = new ImageIcon("stepIn.png");
		stepOut = new ImageIcon("stepOut.png");
	}
	
	public static PicturesRes getInstance()
	{
		if(instance == null)
			instance = new PicturesRes();
		return instance;
	}
	
}
