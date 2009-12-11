package nju.edu.lc3.simulator.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

public class MemoryComposite extends Composite {

	private Table table;
	/**
	 * Create the composite
	 * @param parent
	 * @param style
	 */
	public MemoryComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout());

		table = new Table(this, SWT.BORDER);
		table.setHeaderVisible(true);

		final TableColumn newColumnTableColumn = new TableColumn(table, SWT.NONE);
		newColumnTableColumn.setWidth(16);

		final TableColumn newColumnTableColumn_1 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_1.setWidth(60);
		newColumnTableColumn_1.setText("µØÖ·");

		final TableColumn newColumnTableColumn_2 = new TableColumn(table, SWT.NONE);
		newColumnTableColumn_2.setWidth(120);
		newColumnTableColumn_2.setText("Êý¾Ý");
		
		//
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
