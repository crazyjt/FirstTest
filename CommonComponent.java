import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Choice;
import java.awt.Frame;
import java.awt.List;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.Box;

public class CommonComponent {
	Frame frame = new Frame("����");
	
	//����һ��5��20�е��ı���
	TextArea textArea = new TextArea(5,20);
	
	//����һ������ѡ���
	Choice colorChoice = new Choice();
	
	//����һ�鵥ѡ��
	CheckboxGroup sexChoice = new CheckboxGroup();
	//������ѡ���ѡ����
	Checkbox male = new Checkbox("��", sexChoice, true);
	Checkbox female = new Checkbox("Ů", sexChoice, false);
	
	//����һ����ѡ�򣬳�ʼ״̬Ϊδѡ��
	Checkbox married = new Checkbox("�Ƿ��ѻ飿", false);
	
	//����һ���б�ѡ���
	List colorList = new List();
	
	//����һ������50�е��ı���
	TextField textField = new TextField(50);
	
	//����һ��ȷ����ť
	 Button btnOk = new Button("ȷ��");
	
	//�����ʼ������
	public void init()
	{
		//����ѡ����������
		colorChoice.add("��ɫ");
		colorChoice.add("��ɫ");
		colorChoice.add("��ɫ");
		
		//�б�ѡ����������
		colorList.add("��ɫ");
		colorList.add("��ɫ");
		colorList.add("��ɫ");
		
		//����Panelװ�ص����ı����ȷ�ϰ�ť
		Panel buttom = new Panel();
		buttom.add(textField);
		buttom.add(btnOk);
		
		//����Panelװ������ѡ��򡢵�ѡ����͸�ѡ��
		Panel checkPanel = new Panel();
		checkPanel.add(colorChoice);
		//ע�ⲻ��ֱ���������CheckBoxGroup����Ҫ������е�ѡ��
		checkPanel.add(male);
		checkPanel.add(female);
		checkPanel.add(married);
		
		//���ô�ֱ���е�Boxװ�ض����ı���checkPanel
		Box topLeft = Box.createVerticalBox();
		topLeft.add(textArea);
		topLeft.add(checkPanel);
		
		//����ˮƽ���е�Boxװ��topLeft��List�б�ѡ���
		Box top = Box.createHorizontalBox();
		top.add(topLeft);
		top.add(colorList);
		
		//��top��buttomװ��frame��
		frame.add(buttom, BorderLayout.SOUTH);
		frame.add(top);
		
		frame.addWindowListener(new closeWindow());
		frame.pack();
		frame.setVisible(true);
		btnOk.addActionListener(new btnListener());
	}
	
	class closeWindow extends WindowAdapter
	{
		public void windowClosing(WindowEvent e)
		{
			System.exit(0);
		}
	}
	
	 class btnListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			String textFieldStr = new String();
			textFieldStr = textArea.getText() + " " + colorList.getSelectedItem().toString() + " " + colorChoice.getSelectedItem().toString() + " " + sexChoice.getSelectedCheckbox().getLabel().toString();
			textField.setText(textFieldStr);
		}	
	}
	
	public static void main(String[] args) {
		new CommonComponent().init();
	}

}
