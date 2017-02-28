import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Event;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;



public class WindowTest {
	
	public static void main(String[] args) {
		Frame frame = new Frame("²âÊÔ´°¿Ú");
		
		Panel p1 = new Panel();
		p1.add(new TextField(30));
		frame.add(p1,BorderLayout.NORTH);
		
		Panel p2 = new Panel();
		p2.setLayout(new GridLayout(3, 5));
		String[] str={"0","1","2","3","4","5","6","7","8","9","+","-","*","/","="};
		for(int i = 0; i < 15; i++)
		{
			p2.add(new Button(str[i]));
		}
		frame.add(p2);
		
		frame.pack();
		frame.setVisible(true);
		
	}
	

}
