package nju.edu.lc3.simulator.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.CoolBar;
import org.eclipse.swt.widgets.CoolItem;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import com.swtdesigner.SWTResourceManager;

public class LC3Simulator {

	private Combo combo;
	protected Shell shell;

	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			LC3Simulator window = new LC3Simulator();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window
	 */
	public void open() {
		final Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
	}

	/**
	 * Create contents of the window
	 */
	protected void createContents() {
		shell = new Shell();
		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		shell.setLayout(gridLayout);
		shell.setSize(380, 600);
		shell.setText("SWT Application");
		
		final Composite toolComposite = new Composite(shell, SWT.NONE);
		toolComposite.setLayoutData(new GridData(349, 25));

		final Button button = new Button(toolComposite, SWT.NONE);
		button.setText("button");
		button.setBounds(0, 0, 50, 25);

		final Button button_1 = new Button(toolComposite, SWT.NONE);
		button_1.setText("button");
		button_1.setBounds(57, 0, 50, 25);

		final Button button_2 = new Button(toolComposite, SWT.NONE);
		button_2.setText("button");
		button_2.setBounds(113, 0, 50, 25);

		combo = new Combo(toolComposite, SWT.NONE);
		combo.setBounds(282, 1, 67, 25);
		
		final Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayoutData(new GridData(349, SWT.DEFAULT));
		composite.setBounds(0, 0, 360, 401);
		
		RegisterComposite rc = new RegisterComposite(composite, 0);
		rc.setBounds( 0, 0,120, 401);
		
		
		MemoryComposite mc = new MemoryComposite(composite,0);
		mc.setBounds(120,0,220,401);

		final Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		final MenuItem newSubmenuMenuItem = new MenuItem(menu, SWT.CASCADE);
		newSubmenuMenuItem.setText("文件");

		final Menu menu_1 = new Menu(newSubmenuMenuItem);
		newSubmenuMenuItem.setMenu(menu_1);

		final MenuItem newItemMenuItem = new MenuItem(menu_1, SWT.NONE);
		newItemMenuItem.setText("导入");
		//
	}

}
