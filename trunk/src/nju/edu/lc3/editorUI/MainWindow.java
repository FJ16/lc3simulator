package nju.edu.lc3.editorUI;

import java.io.File;
import java.io.FileInputStream;

import nju.edu.lc3.code.CodeBase;
import nju.edu.lc3.parser.LC3Parser;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

public class MainWindow {
	
	private List list;
	private Text text;
	protected Shell shell;

	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainWindow window = new MainWindow();
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
		shell.setLayout(new FormLayout());
		shell.setSize(800, 600);
		shell.setText("SWT Application");

		final ToolBar toolBar = new ToolBar(shell, SWT.NONE);
		final FormData fd_toolBar = new FormData();
		fd_toolBar.bottom = new FormAttachment(0, 25);
		fd_toolBar.top = new FormAttachment(0, 0);
		fd_toolBar.right = new FormAttachment(100, -5);
		fd_toolBar.left = new FormAttachment(0, 4);
		toolBar.setLayoutData(fd_toolBar);

		final ToolItem newItemToolItem = new ToolItem(toolBar, SWT.PUSH);
		newItemToolItem.setText("新建");

		final ToolItem newItemToolItem_1 = new ToolItem(toolBar, SWT.PUSH);
		newItemToolItem_1.setText("打开");

		final ToolItem newItemToolItem_2 = new ToolItem(toolBar, SWT.PUSH);
		newItemToolItem_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				list.removeAll();
				
				
				//item.setText("第一遍扫描：");
				
				LC3Parser parser = new LC3Parser();
			    File file = new File("test2.asm");
			    CodeBase cb;
			    try {
			                cb = parser.parse(new FileInputStream(file));
			                cb.showBinaryInstructions();
			        } catch (Exception e1) {
			        	list.add(e1.getMessage());
			                e1.printStackTrace();
			                return;
			        }
			        list.add("编译成功"); 
				
			}
		});
		newItemToolItem_2.setText("编译");

		final SashForm sashForm = new SashForm(shell, SWT.VERTICAL);
		final FormData fd_sashForm = new FormData();
		fd_sashForm.top = new FormAttachment(0, 25);
		fd_sashForm.bottom = new FormAttachment(100, -5);
		fd_sashForm.right = new FormAttachment(100, -5);
		fd_sashForm.left = new FormAttachment(0, 5);
		sashForm.setLayoutData(fd_sashForm);

		text = new Text(sashForm, SWT.V_SCROLL | SWT.MULTI | SWT.H_SCROLL | SWT.BORDER);

		list = new List(sashForm, SWT.BORDER);
		sashForm.setWeights(new int[] {1, 1 });

		final Menu menu = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menu);

		final MenuItem newSubmenuMenuItem = new MenuItem(menu, SWT.CASCADE);
		newSubmenuMenuItem.setText("文件");

		final Menu menu_1 = new Menu(newSubmenuMenuItem);
		newSubmenuMenuItem.setMenu(menu_1);

		final MenuItem newItemMenuItem = new MenuItem(menu_1, SWT.NONE);
		newItemMenuItem.setText("新建");

		final MenuItem newItemMenuItem_1 = new MenuItem(menu_1, SWT.NONE);
		newItemMenuItem_1.setText("打开");

		final MenuItem newItemMenuItem_2 = new MenuItem(menu_1, SWT.NONE);
		newItemMenuItem_2.setText("退出");

		final MenuItem newSubmenuMenuItem_1 = new MenuItem(menu, SWT.CASCADE);
		newSubmenuMenuItem_1.setText("操作");

		final Menu menu_2 = new Menu(newSubmenuMenuItem_1);
		newSubmenuMenuItem_1.setMenu(menu_2);

		final MenuItem newItemMenuItem_3 = new MenuItem(menu_2, SWT.NONE);
		newItemMenuItem_3.setText("编译");

		final MenuItem newSubmenuMenuItem_2 = new MenuItem(menu, SWT.CASCADE);
		newSubmenuMenuItem_2.setText("帮助");

		final Menu menu_3 = new Menu(newSubmenuMenuItem_2);
		newSubmenuMenuItem_2.setMenu(menu_3);

		final MenuItem newItemMenuItem_4 = new MenuItem(menu_3, SWT.NONE);
		newItemMenuItem_4.setText("关于");
		//
	}

}
