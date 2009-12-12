package nju.edu.lc3.simulator.gui;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JScrollBar;


public class MyScrollBar extends JScrollBar{
	Scrollable control;
	public MyScrollBar(Scrollable control,int maxScroll){
		this.control=control;
		this.setMaximum(maxScroll);
		this.setValueIsAdjusting(true);
		this.addAdjustmentListener(new Adjust());
		this.setVisible(true);
	}
	public void increMax(){
		int max=super.getMaximum();
		super.setMaximum(max+1);
	}
	class Adjust implements AdjustmentListener{
		public void adjustmentValueChanged(AdjustmentEvent event){
			if(MyScrollBar.this.getMaximum()==0){
				MyScrollBar.this.setVisible(false);
			}
			else {
				MyScrollBar.this.setVisible(true);
			}
			control.scroll();
		}
	}
}
