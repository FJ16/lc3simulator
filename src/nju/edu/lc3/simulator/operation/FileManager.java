package nju.edu.lc3.simulator.operation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import nju.edu.lc3.simulator.model.MemoryModel;
import nju.edu.lc3.simulator.model.RegisterModel;

public class FileManager {
	private static final FileManager instance = new FileManager();
	private static final String OS_KERNEL_PATH = "os.txt";
	public int startAddress;

	public static FileManager getInstance() {
		return instance;
	}

	public void loadOS() throws IOException {
		
		BufferedReader stream;
		stream = new BufferedReader(new FileReader(OS_KERNEL_PATH));
		String line = stream.readLine();
		int state = 0;
		while(line!=null)
		{
			if(line.trim().equals("")||line==null)
			{
				line = stream.readLine();
				continue;
			}
			else if(line.indexOf("Registers:")>=0)
			{
				state = 0;
				line = stream.readLine();
				continue;
			}
			else if(line.indexOf("Memory:")>=0)
			{
				state =1;
				line = stream.readLine();
				continue;
			}
			
			if(state==0)//读入寄存器的值
			{
				String reg = line.substring(0,2);
				if(!reg.equals("CC"))
				{
					String value = line.substring(6, 10);
					int intValue = Integer.parseInt(value, 16);
					RegisterModel.getRegister(reg).setValue(intValue);
				}
				
			}
			
			else //读入内存地址
			{
				String mem = line.substring(1,5);
				int memAddress = Integer.parseInt(mem,16);
				String value = line.substring(7,23);
				int intValue = Integer.parseInt(value,2);
				MemoryModel.getMemory(memAddress).setValue(intValue);
			}
			
			line = stream.readLine();
		}
		stream.close();
	}

	public void loadFile(String filePath) throws IOException {
				
		BufferedReader stream;
		stream = new BufferedReader(new FileReader(filePath));
		String line = stream.readLine();
		int address = Integer.parseInt(line,2);
		this.startAddress = address;
		line = stream.readLine();
		while(line!=null)
		{
			int value = Integer.parseInt(line,2);
			MemoryModel.getMemory(address).setValue(value);
			address++;
			line = stream.readLine();
		}
		stream.close();
		

	}
}
