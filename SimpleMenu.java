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
	private Frame frame = new Frame("Menu����");
	private MenuBar menuBar = new MenuBar();
	
	//�����ļ��˵�
	Menu file = new Menu("�ļ�");
	MenuItem newItem = new MenuItem("�½�");
	MenuItem saveItem = new MenuItem("����");
	MenuItem openItem = new MenuItem("��");
	//Ctrl+X����Ϊ��ݼ�
	MenuItem exitItem = new MenuItem("�˳�", new MenuShortcut(KeyEvent.VK_X));
	
	//����༭�˵�
	Menu edit = new Menu("�༭");
	CheckboxMenuItem autowrap = new CheckboxMenuItem("�Զ�����");
	MenuItem copyItem = new MenuItem("����");
	MenuItem pasteItem = new MenuItem("ճ��");
	//����һ��Menu��Ϊ�����˵�
	Menu format = new Menu("��ʽ");
	//Ctrl+Shift+/��Ϊ��ݼ�
	MenuItem commentItem = new MenuItem("ע��", new MenuShortcut(KeyEvent.VK_SLASH, true));
	MenuItem cancelItem = new MenuItem("ȡ��ע��");
	
	private TextArea textArea = new TextArea(6,40);
	
	//�����ʼ������
	public void init()
	{
		//��Lambda����˵��¼�������
		ActionListener menuListener = e ->
		{
			String cmd = e.getActionCommand();
			textArea.append("����" + cmd + "��ť\n");
			if(cmd.equals("�˳�"))
			{
				System.exit(0);
			}
			else if (cmd.equals("����")) 
			{
				FileDialog save = new FileDialog(frame, "ѡ�񱣴��ļ���·��", FileDialog.SAVE);
				save.setVisible(true);
			}
			else if (cmd.equals("��"))
			{
				FileDialog open = new FileDialog(frame, "ѡ����Ҫ���ļ�", FileDialog.LOAD);
				open.setVisible(true);
			}
		};
		//Ϊ�˵������Ӽ�����
		exitItem.addActionListener(menuListener);
		saveItem.addActionListener(menuListener);
		openItem.addActionListener(menuListener);
		//Ϊfile��Ӳ˵���
		file.add(newItem);
		file.add(saveItem);
		file.add(openItem);
		file.add(exitItem);
		//Ϊedit��Ӳ˵���
		edit.add(autowrap);
		//��ӷָ���
		edit.addSeparator();
		edit.add(copyItem);
		edit.add(pasteItem);
		//Ϊformat��Ӳ˵���
		format.add(commentItem);
		format.add(cancelItem);
		//��ӷָ���
		edit.add(new MenuItem("-"));
		edit.add(format);
		//���˵�file��edit����˵���
		menuBar.add(file);
		menuBar.add(edit);
		//Ϊframe�������ò˵���
		frame.setMenuBar(menuBar);
		//�������ڲ�����ʽ�����¼�����������
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
