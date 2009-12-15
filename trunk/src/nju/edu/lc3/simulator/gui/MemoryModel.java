package nju.edu.lc3.simulator.gui;

import java.util.ArrayList;
import java.util.List;

public class MemoryModel {
	
	private static List<MemoryModel> memoryList = new ArrayList<MemoryModel>(65536);
	static 
	{
		for(int i =0;i<65536;i++)
		{
			memoryList.add(new MemoryModel(i));
		}
	}
	private boolean isBreakPoint;
	private int address;
	private int value;
	private String operation;
	private String name;
	
	public static MemoryModel getMemory(int address)
	{
		return memoryList.get(address);
	}
	public boolean isBreakPoint() {
		return isBreakPoint;
	}
	public void setBreakPoint(boolean isBreakPoint) {
		this.isBreakPoint = isBreakPoint;
	}

	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
		setName();
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	public String getName()
	{
		return name;
	}
	public boolean isCurrent()
	{
		if(this.address==MemoryShowValue.CURRENT_POINT)
			return true;
		return false;
	}
	
	public void value2operation()
	{
		//����ֵ�õ������
	}
	public MemoryModel(boolean isBreakPoint, int address,
			int value) {
		super();
		this.isBreakPoint = isBreakPoint;
		this.address = address;
		this.value = value;
		setName();
	}
	private void setName()
	{
		String temp=Integer.toHexString(address);
		for(;temp.length()<4;temp="0"+temp);
		this.name = temp;
	}
	public MemoryModel(int address)
	{
		this(false,address,0);
	}
}