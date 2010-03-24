package nju.edu.lc3.simulator.model;

public class Interrupt {

	private int type;

	public Interrupt(int type) {
		this.type = type;
	}

	public static void sendInt(Interrupt interrupt)
	{
		//发送中断
		
	}
	
	/**
	 * 时钟中断
	 */
	public static final Interrupt INT_TIME_OUT = new Interrupt(0);
	
	/**
	 * 键盘中断
	 */
	public static final Interrupt INT_KEYBOARD = new Interrupt(1);
	
	/**
	 * 可编程中断
	 */
	public static final Interrupt INT_PROGRAMMAR = new Interrupt(2);
	

}
