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
	//定义画图区域宽度
	private final int AREA_WIDTH = 500;
	//定义画图区域高度
	private final int AREA_HEIGHT = 400;
	//定义上一次鼠标拖动事件鼠标的坐标
	private int preX = -1;
	private int preY = -1;
	
	//定义一个右键菜单用于选择画笔颜色
	PopupMenu pop = new PopupMenu();
	MenuItem red = new MenuItem("红色");
	MenuItem green = new MenuItem("绿色");
	MenuItem blue = new MenuItem("蓝色");
	
	//定义一个BufferedImage画图缓冲对象
	BufferedImage image = new BufferedImage(AREA_WIDTH, AREA_HEIGHT, BufferedImage.TYPE_INT_RGB);
	//获取image中的画笔对象
	Graphics graphics = image.getGraphics();
	
	//定义窗口
	private Frame frame = new Frame("画板");
	//定义画布,编写一个DrawCanvas类继承于Canvas类，以重写paint函数
	private DrawCanvas drawCanvas = new DrawCanvas();
	//foreColor用于保存画笔颜色,默认为黑色
	private Color foreClolor = new Color(0, 0, 0);
	//定义右键监听事件,利用Lambda表达式
    ActionListener mouseListener = e -> {
 	//获取事件源的文本进行分类
		if(e.getActionCommand().equals("红色"))
		{
			foreClolor = new Color(255, 0, 0);
			//改变画笔颜色
			graphics.setColor(foreClolor);
		}
		if(e.getActionCommand().equals("绿色"))
		{
			foreClolor = new Color(0, 255, 0);
			//改变画笔颜色
			graphics.setColor(foreClolor);
		}
		if(e.getActionCommand().equals("蓝色"))
		{
			foreClolor = new Color(0, 0, 255);
			//改变画笔颜色
			graphics.setColor(foreClolor);
		}
	   };
		
	//定义初始化函数
		public void init()
		{
			
			
			//为右键的菜单选项添加事件监听
			red.addActionListener(mouseListener);
			green.addActionListener(mouseListener);
			blue.addActionListener(mouseListener);
			
			//将red,green,blue加入右键菜单
			pop.add(red);
			pop.add(green);
			pop.add(blue);
			
			//将右键菜单加入画布
			drawCanvas.add(pop);
			
			//通过操作graphics对象，将image对象背景填充为白色
			graphics.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);
			drawCanvas.setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));
			
			//监听鼠标拖动事件
			drawCanvas.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e)
				{
					//为改变颜色时初始为黑色
					graphics.setColor(foreClolor);
					if(preX > 0 && preY > 0)
					{
						//绘制两个点之间的连线
						graphics.drawLine(preX, preY, e.getX(), e.getY());
					}
						//将当前坐标记录下来
						preX = e.getX();
						preY = e.getY();
						//画线
						drawCanvas.repaint();
				}
			});
			
			//监听鼠标弹起事件
			drawCanvas.addMouseListener(new MouseAdapter() {
				public void mouseReleased(MouseEvent e)
				{
					//右键弹起
					if(e.isPopupTrigger())
					{
						pop.show(drawCanvas, e.getX(), e.getY());
					}
					
					//鼠标弹起，坐标重置
					preX = -1;
					preY = -1;
				}
			});
			
			//窗口关闭监听事件
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
		//重写paint方法
		public void paint(Graphics g)
		{
			//将image绘制到该画布上
			g.drawImage(image, 0, 0, null);
		}
	}

}
