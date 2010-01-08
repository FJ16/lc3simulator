package nju.edu.lc3.simulator.operation;

import nju.edu.lc3.simulator.gui.Application;
import nju.edu.lc3.simulator.model.RegisterModel;
import nju.edu.lc3.util.BitUtil;

public class MachineRun {

	private static final MachineRun instance = new MachineRun();

	public static MachineRun getInstance() {
		return instance;
	}

	public void setcc(int value) {
		if (value == 0) {
			int temp = RegisterModel.getRegister("PSR").getValue();
			RegisterModel.getRegister("PSR").setValue(temp | 2);
		} else if (value > 0) {
			int temp = RegisterModel.getRegister("PSR").getValue();
			RegisterModel.getRegister("PSR").setValue(temp | 1);
		} else if (value < 0) {
			int temp = RegisterModel.getRegister("PSR").getValue();
			RegisterModel.getRegister("PSR").setValue(temp | 4);
		}

		int temp = RegisterModel.getRegister("PSR").getValue();
		temp &= 7;
		RegisterModel.getRegister("CC").setValue(temp);

	}

	public void setMachineMode(boolean mode) {
		if (mode) {
			int temp = RegisterModel.getRegister("PSR").getValue();
			int value = BitUtil.setBits(temp, 0, 1, 0);
			RegisterModel.getRegister("PSR").setValue(value);
		} else {
			int temp = RegisterModel.getRegister("PSR").getValue();
			int value = BitUtil.setBits(temp, 0, 1, 1);
			RegisterModel.getRegister("PSR").setValue(value);

		}
	}

	public void gotoPCLine() {
		int pc = RegisterModel.getRegister("PC").getValue();
		int low = Application.getInstance().memView.scroll.getValue();
		int high = Application.getInstance().memView.scroll.getValue()
				+ Application.getInstance().memView.MEMORY_SHOW_SIZE;
		if (pc < low || pc >= high)
			Application.getInstance().memView.scroll.setValue(pc);

	}

	public void setPriority(int level) {
		int temp = RegisterModel.getRegister("PSR").getValue();
		int value = BitUtil.setBits(temp, 5, 3, level);
		RegisterModel.getRegister("PSR").setValue(value);
	}

	public static void main(String[] args) {
		MachineRun.getInstance().setMachineMode(true);
		MachineRun.getInstance().setPriority(0);
	}
}
