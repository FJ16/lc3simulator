package nju.edu.lc3.simulator.operation;

import nju.edu.lc3.simulator.instruction.*;
import nju.edu.lc3.simulator.model.MemoryModel;
import nju.edu.lc3.simulator.model.RegisterModel;
import nju.edu.lc3.util.BitUtil;
import nju.edu.lc3.word.Bits;

public class RunProgram {
	private static RunProgram instance = new RunProgram();
	
	Thread thread ;
	public static RunProgram getInstance()
	{
		return instance;
	}
	private RunProgram()
	{
		
	}
	
	public void runAll()
	{
		
		RegisterModel.getRegister("MCR").setValue(32768);
		int i=0;
		while(RegisterModel.getRegister("MCR").getValue()!=0&&RegisterModel.getRegister("PC").getValue()<65535)
		{
			runInto();
			//System.out.println(i++);
			
		}
		MachineRun.getInstance().gotoPCLine();
		
	}
	

	

	
	private void setIRRegister(int op)
	{
		RegisterModel.getRegister("IR").setValue(op);
		
	}

	public void runInto()
	{
		RegisterModel.getRegister("MCR").setValue(32768);
		int op = fetch(); //获取操作码
		setIRRegister(op);//设置IR寄存器
		BitInstruction ins = decode(op); //解码
		MachineRun.getInstance().setMachineMode(ins.isSystemMode); //设置机器模式
		ins.execute();         //执行代码
		
	}
	
	public void runOneStep()
	{
		RegisterModel.getRegister("MCR").setValue(32768);
		int op = fetch(); //获取操作码
		setIRRegister(op);//设置IR寄存器
		BitInstruction ins = decode(op); //解码
		
		MachineRun.getInstance().setMachineMode(ins.isSystemMode); //设置机器模式
		ins.execute();         //执行代码
		if(ins instanceof Bit_JmpRet)
		{
			runOut();
		}
		
			
	}
	
	public void runOut()
	{
		RegisterModel.getRegister("MCR").setValue(32768);
		int op;
		do{
			op = fetch(); //获取操作码
			setIRRegister(op);//设置IR寄存器
			BitInstruction ins = decode(op); //解码
			MachineRun.getInstance().setMachineMode(ins.isSystemMode); //设置机器模式
			ins.execute();         //执行代码
		}
		while(op!=49600&&RegisterModel.getRegister("MCR").getValue()!=0&&RegisterModel.getRegister("PC").getValue()<65535);
		MachineRun.getInstance().gotoPCLine();
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
/*	public static void main(String[] args)
	{
		RunProgram test = new RunProgram();
		test.decode(7192);
	}*/
}
