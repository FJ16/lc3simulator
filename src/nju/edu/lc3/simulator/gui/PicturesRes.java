package nju.edu.lc3.simulator.gui;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class PicturesRes {
	private static PicturesRes instance;
	private Icon bPoint;//break point
	private Icon init;
	private Icon run;
	
	
	public Icon getbPoint() {
		return bPoint;
	}
	public Icon getInit() {
		return init;
	}
	public Icon getRun() {
		return run;
	}
	private PicturesRes()
	{
		initIcon();
	}
	
	private void initIcon()
	{
		bPoint=new ImageIcon("breakPoint.gif");
		init=new ImageIcon("init.gif");
		run = new ImageIcon("pointto.gif");
	}
	
	public static PicturesRes getInstance()
	{
		if(instance == null)
			instance = new PicturesRes();
		return instance;
	}
	
}
