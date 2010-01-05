package nju.edu.lc3.simulator.operation;

import nju.edu.lc3.simluator.instruction.*;
import nju.edu.lc3.simulator.gui.MemoryModel;
import nju.edu.lc3.simulator.gui.RegisterModel;
import nju.edu.lc3.util.BitUtil;
import nju.edu.lc3.word.Bits;

public class RunProgram {
	public void runAll()
	{
		
	}
	

	
	private void setIRRegister(int op)
	{
		RegisterModel.getRegister("IR").setValue(op);
	}

	public void runInto()
	{
		int op = fetch(); //获取操作码
		setIRRegister(op);//设置IR寄存器
		BitInstruction ins = decode(op); //解码
		MachineRun.getInstance().setMachineMode(ins.isSystemMode); //设置机器模式
		ins.execute();         //执行代码
	}
	
	public void runOneStep()
	{
		int pcValue = RegisterModel.getRegister("PC").getValue();
		do
		{
			runInto();
		}
		while(RegisterModel.getRegister("PC").getValue()==pcValue);
			
	}
	
	public void runOut()
	{
		int op;
		do{
			op = fetch(); //获取操作码
			setIRRegister(op);//设置IR寄存器
			BitInstruction ins = decode(op); //解码
			MachineRun.getInstance().setMachineMode(ins.isSystemMode); //设置机器模式
			ins.execute();         //执行代码
		}
		while(op!=49600);
	}
	
	private int fetch()
	{
		int result=0;
		int address = RegisterModel.getRegister("PC").getValue();
		
		result = MemoryModel.getMemory(address).getValue();
		address++;
		RegisterModel.getRegister("PC").setValue(address);
		return result;
	}
	
	private BitInstruction decode(int value)
	{
		char[] bit = null;
		try {
			bit = Bits.TO_BIT_ARRAY(value, 16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		int op = getOperation(bit);
		BitInstruction instruction;
		switch(op)
		{
		case 1:
			instruction = new Bit_Add(bit);
			break;
		case 5:
			instruction = new Bit_And(bit);
			break;
		case 0:
			instruction = new Bit_Br(bit);
			break;
		case 12:
			instruction = new Bit_JmpRet(bit);
			break;
		case 4:
			instruction = new Bit_Jsr(bit);
			break;
		case 2:
			instruction = new Bit_Ld(bit);
			break;
		case 10:
			instruction = new Bit_Ldi(bit);
			break;
		case 6:
			instruction = new Bit_Ldr(bit);
			break;
		case 14:
			instruction = new Bit_Lea(bit);
			break;
		case 9:
			instruction = new Bit_Not(bit);
			break;
		case 8:
			instruction = new Bit_Rti(bit);
			break;
		case 3:
			instruction = new Bit_St(bit);
			break;
		case 11:
			instruction = new Bit_Sti(bit);
			break;
		case 7:
			instruction = new Bit_Str(bit);
			break;
		case 15:
			instruction = new Bit_Trap(bit);
			break;
		default:
			instruction = new Bit_Fill(bit);
		}
		if(instruction.validate()==false)
		{
			instruction = new Bit_Fill(bit);
		}
			
		return instruction;
	}
	
	private int getOperation(char[] bit)
	{
		return BitUtil.bitArrayToInt(bit, 0, 4,false);
	}
	public static void main(String[] args)
	{
		RunProgram test = new RunProgram();
		test.decode(7192);
	}
}
