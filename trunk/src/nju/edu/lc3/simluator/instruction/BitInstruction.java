package nju.edu.lc3.simluator.instruction;

public abstract class BitInstruction {
	
	public static final int BIT_LENGTH=16;
	public abstract boolean validate();
	public abstract boolean execute();
	public abstract String getSource();
	
}
