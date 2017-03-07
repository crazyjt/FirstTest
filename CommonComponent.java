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
	Frame frame = new Frame("测试");
	
	//定义一个5行20列的文本域
	TextArea textArea = new TextArea(5,20);
	
	//定义一个下拉选择框
	Choice colorChoice = new Choice();
	
	//定义一组单选框
	CheckboxGroup sexChoice = new CheckboxGroup();
	//创建单选框的选择项
	Checkbox male = new Checkbox("男", sexChoice, true);
	Checkbox female = new Checkbox("女", sexChoice, false);
	
	//定义一个复选框，初始状态为未选中
	Checkbox married = new Checkbox("是否已婚？", false);
	
	//定义一个列表选择框
	List colorList = new List();
	
	//定义一个单行50列的文本域
	TextField textField = new TextField(50);
	
	//定义一个确定按钮
	 Button btnOk = new Button("确定");
	
	//定义初始化函数
	public void init()
	{
		//下拉选择框加入数据
		colorChoice.add("红色");
		colorChoice.add("绿色");
		colorChoice.add("蓝色");
		
		//列表选择框加入数据
		colorList.add("红色");
		colorList.add("绿色");
		colorList.add("蓝色");
		
		//利用Panel装载单行文本框和确认按钮
		Panel buttom = new Panel();
		buttom.add(textField);
		buttom.add(btnOk);
		
		//利用Panel装载下拉选择框、单选框组和复选框
		Panel checkPanel = new Panel();
		checkPanel.add(colorChoice);
		//注意不能直接添加整个CheckBoxGroup，需要添加其中的选项
		checkPanel.add(male);
		checkPanel.add(female);
		checkPanel.add(married);
		
		//利用垂直排列的Box装载多行文本域、checkPanel
		Box topLeft = Box.createVerticalBox();
		topLeft.add(textArea);
		topLeft.add(checkPanel);
		
		//利用水平排列的Box装载topLeft和List列表选择款
		Box top = Box.createHorizontalBox();
		top.add(topLeft);
		top.add(colorList);
		
		//将top和buttom装入frame中
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
