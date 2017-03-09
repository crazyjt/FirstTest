import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

public class HandDraw {
	//���廭ͼ������
	private final int AREA_WIDTH = 500;
	//���廭ͼ����߶�
	private final int AREA_HEIGHT = 400;
	//������һ������϶��¼���������
	private int preX = -1;
	private int preY = -1;
	
	//����һ���Ҽ��˵�����ѡ�񻭱���ɫ
	PopupMenu pop = new PopupMenu();
	MenuItem red = new MenuItem("��ɫ");
	MenuItem green = new MenuItem("��ɫ");
	MenuItem blue = new MenuItem("��ɫ");
	
	//����һ��BufferedImage��ͼ�������
	BufferedImage image = new BufferedImage(AREA_WIDTH, AREA_HEIGHT, BufferedImage.TYPE_INT_RGB);
	//��ȡimage�еĻ��ʶ���
	Graphics graphics = image.getGraphics();
	
	//���崰��
	private Frame frame = new Frame("����");
	//���廭��,��дһ��DrawCanvas��̳���Canvas�࣬����дpaint����
	private DrawCanvas drawCanvas = new DrawCanvas();
	//foreColor���ڱ��滭����ɫ,Ĭ��Ϊ��ɫ
	private Color foreClolor = new Color(0, 0, 0);
	//�����Ҽ������¼�,����Lambda���ʽ
    ActionListener mouseListener = e -> {
 	//��ȡ�¼�Դ���ı����з���
		if(e.getActionCommand().equals("��ɫ"))
		{
			foreClolor = new Color(255, 0, 0);
			//�ı仭����ɫ
			graphics.setColor(foreClolor);
		}
		if(e.getActionCommand().equals("��ɫ"))
		{
			foreClolor = new Color(0, 255, 0);
			//�ı仭����ɫ
			graphics.setColor(foreClolor);
		}
		if(e.getActionCommand().equals("��ɫ"))
		{
			foreClolor = new Color(0, 0, 255);
			//�ı仭����ɫ
			graphics.setColor(foreClolor);
		}
	   };
		
	//�����ʼ������
		public void init()
		{
			
			
			//Ϊ�Ҽ��Ĳ˵�ѡ������¼�����
			red.addActionListener(mouseListener);
			green.addActionListener(mouseListener);
			blue.addActionListener(mouseListener);
			
			//��red,green,blue�����Ҽ��˵�
			pop.add(red);
			pop.add(green);
			pop.add(blue);
			
			//���Ҽ��˵����뻭��
			drawCanvas.add(pop);
			
			//ͨ������graphics���󣬽�image���󱳾����Ϊ��ɫ
			graphics.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);
			drawCanvas.setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));
			
			//��������϶��¼�
			drawCanvas.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e)
				{
					//Ϊ�ı���ɫʱ��ʼΪ��ɫ
					graphics.setColor(foreClolor);
					if(preX > 0 && preY > 0)
					{
						//����������֮�������
						graphics.drawLine(preX, preY, e.getX(), e.getY());
					}
						//����ǰ�����¼����
						preX = e.getX();
						preY = e.getY();
						//����
						drawCanvas.repaint();
				}
			});
			
			//������굯���¼�
			drawCanvas.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e)
				{
					//�Ҽ�����
					if(e.isPopupTrigger())
					{
						pop.show(drawCanvas, e.getX(), e.getY());
					}
					
					//��굯����������
					preX = -1;
					preY = -1;
				}
			});
			
			//���ڹرռ����¼�
			frame.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e)
				{
					System.exit(0);
				}
			});
			
			frame.add(drawCanvas);
			frame.pack();
			frame.setVisible(true);
		}
		
		
	public static void main(String[] args) {
		new HandDraw().init();
	}
	
	class DrawCanvas extends Canvas
	{
		//��дpaint����
		public void paint(Graphics g)
		{
			//��image���Ƶ��û�����
			g.drawImage(image, 0, 0, null);
		}
	}

}
