package nju.edu.lc3.simulator.model;

public class TimeCounter {

	private static final int CYCLE_TIME = 100;
	private int counter;
	private final TimeCounter instance = new TimeCounter();
	private TimeCounter()
	{
		
	}
	private TimeCounter(int i)
	{
		
		counter = i;
	}

	public TimeCounter getInstance()
	{
		return instance;
	}
	
	
	public void setCounter(int i)
	{
		this.counter =i;
	}
	public int getCounter()
	{
		return counter;
	}
	public void reset()
	{
		counter = CYCLE_TIME;
	}
	
	public void timeDes()
	{
		if(counter>=1)
			counter--;
		else
		{
			reset();
			Interrupt.sendInt(Interrupt.INT_TIME_OUT);
		}
		
	}
}