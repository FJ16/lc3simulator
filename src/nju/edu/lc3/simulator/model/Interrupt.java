package nju.edu.lc3.simulator.model;

public class Interrupt {

	private int type;

	public Interrupt(int type) {
		this.type = type;
	}

	public static void sendInt(Interrupt interrupt)
	{
		//�����ж�
		
	}
	
	/**
	 * ʱ���ж�
	 */
	public static final Interrupt INT_TIME_OUT = new Interrupt(0);
	
	/**
	 * �����ж�
	 */
	public static final Interrupt INT_KEYBOARD = new Interrupt(1);
	
	/**
	 * �ɱ���ж�
	 */
	public static final Interrupt INT_PROGRAMMAR = new Interrupt(2);
	

}
