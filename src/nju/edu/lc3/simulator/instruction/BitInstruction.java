package nju.edu.lc3.simulator.instruction;

public abstract class BitInstruction {
	char[] bit;
	public boolean isSystemMode = false;
	public static final int BIT_LENGTH=16;
	public abstract boolean validate();
	public abstract boolean execute();
	public abstract String getSource();
	
	
}
