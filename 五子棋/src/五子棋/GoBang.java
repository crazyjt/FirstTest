package 五子棋;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GoBang {
	//定义窗口
	JFrame frame = new JFrame("五子棋");
	//定义画布
	private ChessBoard chessBoard = new ChessBoard();
	//定义棋盘、棋子、选中框的实例对象
	BufferedImage table;
	BufferedImage black;
	BufferedImage white;
	BufferedImage selected;
	//定义棋盘大小和像素大小
	private static int BOARD_SIZE = 15;
	private final int TABLE_WIDTH = 530;
	private final int TABLE_HEIGTH = 557;
	//定义棋盘坐标的像素值与数组之间的比率
	private final int Rate =  512 / BOARD_SIZE;
	//定义棋盘坐标的像素值和棋盘边界之间的偏移距离
	private final int X_OFFSET = 4;
	private final int Y_OFFSET = 4;
	//定义二维数组充当棋盘
	private String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
	//定义当前选中坐标,初始化为-1
	private int selectedX = -1;
	private int selectedY = -1;
	//定义一个变量用于判断下棋位置是否有棋子
	private boolean hadChess = false;
	//定义一个随机变量用于电脑下棋
	ThreadLocalRandom random = ThreadLocalRandom.current();
	
	
	public void init() throws Exception
	{
		//将图片导入，赋给变量
		table = ImageIO.read(new File("image/board.jpg"));
		black = ImageIO.read(new File("image/black.png"));
		white = ImageIO.read(new File("image/white.png"));
		selected = ImageIO.read(new File("image/select.png"));
		//给棋盘数组每个元素赋值“+”，表示没有棋子
		for(int i =0;i < board.length; i++)
		{
			for(int j =0; j < board[i].length; j++)
			{
				board[i][j] = "+";
			}
	    }
		
		//定义鼠标移动事件，绘制选择框
		chessBoard.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent mme)
			{
				selectedX = (mme.getX() - X_OFFSET) / Rate;
				selectedY = (mme.getY() - Y_OFFSET) / Rate;
				//调用chessBoard默认带有的repaint方法重新绘制画面
				chessBoard.repaint();
			}
		});

		
		//定义鼠标点击事件，绘制棋子
		chessBoard.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent mce)
			{
				//将点击位置转换成棋子数组位置
				int xClick = (mce.getX() - X_OFFSET) / Rate;
				int yClick = (mce.getY() - Y_OFFSET) / Rate;
				if(board[xClick][yClick].equals("○") || board[xClick][yClick].equals("●"))
					//提示该位置已经有棋子
				{
					hadChess = true;
					chessBoard.repaint();
				}
				else
				{
					board[xClick][yClick] = "●";
					hadChess = false;
					chessBoard.repaint();
					//调用电脑下棋函数
					computerPlay(xClick, yClick);
				}
			
			}
		});
		frame.add(chessBoard);
		frame.setBounds(10,10,TABLE_WIDTH,TABLE_HEIGTH);
		frame.setVisible(true);
	}
	//定义电脑下棋函数
	public void computerPlay(int x, int y)
	{
		int xPos;
		int yPos;
		if(x == 0 && y != 0)
		{
			xPos = random.nextInt(0, x+2);
			if(y >=14)
				yPos = random.nextInt(y-1, y+1);
			else
				yPos = random.nextInt(y-1, y+2);
		}
		else if(y == 0 && x != 0)
	    {
			yPos = random.nextInt(0, y+2);
			if(x >= 14)
				xPos = random.nextInt(x-1, x+1);
			else
				xPos = random.nextInt(x-1, x+2);
		}
		else if (x == 0 && y == 0)
		{
			xPos = random.nextInt(0, x+2);
			yPos = random.nextInt(0, y+2);
		}	
		else
		{
			xPos = random.nextInt(x-1 , x+2);
			yPos = random.nextInt(y-1 , y+2);
		}
		if(board[xPos][yPos].equals("○") || board[xPos][yPos].equals("●"))
			//重新寻找位置下棋
		{
			computerPlay(x,y);
		}
		else
		{
			board[xPos][yPos] = "○";
			hadChess = false;
			chessBoard.repaint();
		}	
	}
	
	public static void main(String[] args) throws Exception {
		new GoBang().init();
	}

	//自定义画布类
	class ChessBoard extends JPanel
	{
		public void paint(Graphics g)
		{
			//绘制棋盘
			g.drawImage(table, 0, 0, null);
			//绘制选择框
			if(selectedX >=0 && selectedY >=0)
			g.drawImage(selected, selectedX * Rate + X_OFFSET - selected.getWidth()/4, selectedY *Rate + Y_OFFSET - selected.getHeight()/4, null);
			//绘制黑棋、白棋
			for(int i =0;i < board.length; i++)
			{
				for(int j =0; j < board[i].length; j++)
				{
					if(board[i][j].equals("●"))
						g.drawImage(black, i * Rate + X_OFFSET, j * Rate + Y_OFFSET, null);
					if(board[i][j].equals("○"))
						g.drawImage(white, i * Rate + X_OFFSET, j * Rate + Y_OFFSET, null);
				}
			}
			if(hadChess)
			{
				g.setColor(Color.RED);
				g.setFont(new Font("Times", Font.BOLD, 60));
				g.drawString("该位置已有棋子！", 20, 260);
			}
		}
	}
}
