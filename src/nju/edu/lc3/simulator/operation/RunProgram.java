package nju.edu.lc3.simulator.operation;

import nju.edu.lc3.simulator.gui.Application;
import nju.edu.lc3.simulator.instruction.BitInstruction;
import nju.edu.lc3.simulator.instruction.Bit_Br;
import nju.edu.lc3.simulator.instruction.Bit_JmpRet;
import nju.edu.lc3.simulator.model.MemoryModel;
import nju.edu.lc3.simulator.model.RegisterModel;

public class RunProgram {
	private static RunProgram instance = new RunProgram();
	
	Thread thread ;
	int i=0;
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
		
		while(RegisterModel.getRegister("MCR").getValue()!=0&&RegisterModel.getRegister("PC").getValue()<65535)
		{
			runInto();
			i++;
			Application.getInstance().s2.setText(i+" instructions exectued");
			Application.getInstance().s3.setText("Running");
		}
		MachineRun.getInstance().gotoPCLine();
		Application.getInstance().s3.setText("Idel");
		
	}
	
	public void runInto()
	{
		RegisterModel.getRegister("MCR").setValue(32768);
		int pc = getPC();
		int op = fetch(pc); //获取操作码
		setIRRegister(op);//设置IR寄存器
		BitInstruction ins = MemoryModel.getMemory(pc).getIns(false); //解码
		MachineRun.getInstance().setMachineMode(ins.isSystemMode); //设置机器模式
		ins.execute();         //执行代码
		
	}
	
	public void runOneStep()
	{
		Application.getInstance().s3.setText("Running");
		RegisterModel.getRegister("MCR").setValue(32768);
		int pc = RegisterModel.getRegister("PC").getValue();
		
		BitInstruction ins = MemoryModel.getMemory(pc).getIns(false);
		if(ins instanceof Bit_Br||ins instanceof Bit_JmpRet)
		{
			runInto();
			i++;
			Application.getInstance().s2.setText(i+" instructions exectued");
			Application.getInstance().s3.setText("Idle");
			return;
		}
		
		while(RegisterModel.getRegister("PC").getValue()!=pc+1)
		{
			runInto();
			i++;
			Application.getInstance().s2.setText(i+" instructions exectued");
			
			//System.out.println(i++);
		}
		MachineRun.getInstance().gotoPCLine();
		Application.getInstance().s3.setText("Idle");
		/*int op = fetch(pc); //获取操作码
		setIRRegister(op);//设置IR寄存器
		BitInstruction ins = MemoryModel.getMemory(pc).getIns(false); //解码
		
		MachineRun.getInstance().setMachineMode(ins.isSystemMode); //设置机器模式
		ins.execute();         //执行代码
		if(ins instanceof Bit_JmpRet)
		{
			runOut();
		}*/
		
			
	}
	
	public void runOut()
	{
		RegisterModel.getRegister("MCR").setValue(32768);
		int op;
		do{
			int pc = getPC();
			op = fetch(pc); //获取操作码
			setIRRegister(op);//设置IR寄存器
			BitInstruction ins = MemoryModel.getMemory(pc).getIns(false); //解码
			MachineRun.getInstance().setMachineMode(ins.isSystemMode); //设置机器模式
			ins.execute();         //执行代码
		}
		while(op!=49600&&RegisterModel.getRegister("MCR").getValue()!=0&&RegisterModel.getRegister("PC").getValue()<65535);
		MachineRun.getInstance().gotoPCLine();
	}

	
	private void setIRRegister(int op)
	{
		RegisterModel.getRegister("IR").setValue(op);
	}
	private int fetch(int address)
	{
		int result=0;
		result = MemoryModel.getMemory(address).getValue();
		address++;
		RegisterModel.getRegister("PC").setValue(address);
		return result;
	}
	private int getPC()
	{
		return RegisterModel.getRegister("PC").getValue();
	}
	/*private BitInstruction decode(int value,int address)
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
			instruction = new Bit_Add(bit,address);
			break;
		case 5:
			instruction = new Bit_And(bit,address);
			break;
		case 0:
			instruction = new Bit_Br(bit,address);
			break;
		case 12:
			instruction = new Bit_JmpRet(bit,address);
			break;
		case 4:
			instruction = new Bit_Jsr(bit,address);
			break;
		case 2:
			instruction = new Bit_Ld(bit,address);
			break;
		case 10:
			instruction = new Bit_Ldi(bit,address);
			break;
		case 6:
			instruction = new Bit_Ldr(bit,address);
			break;
		case 14:
			instruction = new Bit_Lea(bit,address);
			break;
		case 9:
			instruction = new Bit_Not(bit,address);
			break;
		case 8:
			instruction = new Bit_Rti(bit,address);
			break;
		case 3:
			instruction = new Bit_St(bit,address);
			break;
		case 11:
			instruction = new Bit_Sti(bit,address);
			break;
		case 7:
			instruction = new Bit_Str(bit,address);
			break;
		case 15:
			instruction = new Bit_Trap(bit,address);
			break;
		default:
			instruction = new Bit_Fill(bit,address);
		}
		if(instruction.validate()==false)
		{
			instruction = new Bit_Fill(bit,address);
		}
			
		return instruction;
	}
	*/
/*	private int getOperation(char[] bit)
	{
		return BitUtil.bitArrayToInt(bit, 0, 4,false);
	}*/
/*	public static void main(String[] args)
	{
		RunProgram test = new RunProgram();
		test.decode(7192);
	}*/
}
