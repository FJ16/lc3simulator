package nju.edu.lc3.simulator.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

public class RegisterComposite extends Composite {

	private List list;

	/**
	 * Create the composite
	 * 
	 * @param parent
	 * @param style
	 */
	public RegisterComposite(Composite parent, int style) {
		super(parent, style);
		final swing2swt.layout.GridLayout gridLayout = new swing2swt.layout.GridLayout();
		gridLayout.setColumns(1);
		gridLayout.setRows(1);
		setLayout(gridLayout);

		list = new List(this, SWT.NONE);

		
		list.add("R0");
		list.add("R1");
		list.add("R2");
		list.add("R3");
		list.add("R4");
		list.add("R5");
		list.add("R6");
		list.add("R7");
		list.add("PC");
		list.add("IR");
		list.add("PSR");
		list.add("CC");
		
		//
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
