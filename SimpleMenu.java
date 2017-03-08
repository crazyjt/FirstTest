import java.awt.CheckboxMenuItem;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MenuShortcut;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SimpleMenu {
	private Frame frame = new Frame("Menu测试");
	private MenuBar menuBar = new MenuBar();
	
	//定义文件菜单
	Menu file = new Menu("文件");
	MenuItem newItem = new MenuItem("新建");
	MenuItem saveItem = new MenuItem("保存");
	MenuItem openItem = new MenuItem("打开");
	//Ctrl+X设置为快捷键
	MenuItem exitItem = new MenuItem("退出", new MenuShortcut(KeyEvent.VK_X));
	
	//定义编辑菜单
	Menu edit = new Menu("编辑");
	CheckboxMenuItem autowrap = new CheckboxMenuItem("自动换行");
	MenuItem copyItem = new MenuItem("复制");
	MenuItem pasteItem = new MenuItem("粘贴");
	//定义一个Menu作为二级菜单
	Menu format = new Menu("格式");
	//Ctrl+Shift+/作为快捷键
	MenuItem commentItem = new MenuItem("注释", new MenuShortcut(KeyEvent.VK_SLASH, true));
	MenuItem cancelItem = new MenuItem("取消注释");
	
	private TextArea textArea = new TextArea(6,40);
	
	//定义初始化函数
	public void init()
	{
		//用Lambda定义菜单事件监听器
		ActionListener menuListener = e ->
		{
			String cmd = e.getActionCommand();
			textArea.append("单击" + cmd + "按钮\n");
			if(cmd.equals("退出"))
			{
				System.exit(0);
			}
			else if (cmd.equals("保存")) 
			{
				FileDialog save = new FileDialog(frame, "选择保存文件的路径", FileDialog.SAVE);
				save.setVisible(true);
			}
			else if (cmd.equals("打开"))
			{
				FileDialog open = new FileDialog(frame, "选择需要打开文件", FileDialog.LOAD);
				open.setVisible(true);
			}
		};
		//为菜单项增加监听器
		exitItem.addActionListener(menuListener);
		saveItem.addActionListener(menuListener);
		openItem.addActionListener(menuListener);
		//为file添加菜单项
		file.add(newItem);
		file.add(saveItem);
		file.add(openItem);
		file.add(exitItem);
		//为edit添加菜单项
		edit.add(autowrap);
		//添加分隔线
		edit.addSeparator();
		edit.add(copyItem);
		edit.add(pasteItem);
		//为format添加菜单项
		format.add(commentItem);
		format.add(cancelItem);
		//添加分隔线
		edit.add(new MenuItem("-"));
		edit.add(format);
		//将菜单file和edit加入菜单条
		menuBar.add(file);
		menuBar.add(edit);
		//为frame窗口设置菜单条
		frame.setMenuBar(menuBar);
		//以匿名内部类形式创建事件监听器对象
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		frame.add(textArea);
		frame.pack();
		frame.setVisible(true);
	}
	public static void main(String[] args) {
		new SimpleMenu().init();
	}

}
