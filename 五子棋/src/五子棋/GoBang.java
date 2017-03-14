package ������;

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
	//���崰��
	JFrame frame = new JFrame("������");
	//���廭��
	private ChessBoard chessBoard = new ChessBoard();
	//�������̡����ӡ�ѡ�п��ʵ������
	BufferedImage table;
	BufferedImage black;
	BufferedImage white;
	BufferedImage selected;
	//�������̴�С�����ش�С
	private static int BOARD_SIZE = 15;
	private final int TABLE_WIDTH = 530;
	private final int TABLE_HEIGTH = 557;
	//�����������������ֵ������֮��ı���
	private final int Rate =  512 / BOARD_SIZE;
	//�����������������ֵ�����̱߽�֮���ƫ�ƾ���
	private final int X_OFFSET = 4;
	private final int Y_OFFSET = 4;
	//�����ά����䵱����
	private String[][] board = new String[BOARD_SIZE][BOARD_SIZE];
	//���嵱ǰѡ������,��ʼ��Ϊ-1
	private int selectedX = -1;
	private int selectedY = -1;
	//����һ�����������ж�����λ���Ƿ�������
	private boolean hadChess = false;
	//����һ������������ڵ�������
	ThreadLocalRandom random = ThreadLocalRandom.current();
	
	
	public void init() throws Exception
	{
		//��ͼƬ���룬��������
		table = ImageIO.read(new File("image/board.jpg"));
		black = ImageIO.read(new File("image/black.png"));
		white = ImageIO.read(new File("image/white.png"));
		selected = ImageIO.read(new File("image/select.png"));
		//����������ÿ��Ԫ�ظ�ֵ��+������ʾû������
		for(int i =0;i < board.length; i++)
		{
			for(int j =0; j < board[i].length; j++)
			{
				board[i][j] = "+";
			}
	    }
		
		//��������ƶ��¼�������ѡ���
		chessBoard.addMouseMotionListener(new MouseAdapter() {
			public void mouseMoved(MouseEvent mme)
			{
				selectedX = (mme.getX() - X_OFFSET) / Rate;
				selectedY = (mme.getY() - Y_OFFSET) / Rate;
				//����chessBoardĬ�ϴ��е�repaint�������»��ƻ���
				chessBoard.repaint();
			}
		});

		
		//����������¼�����������
		chessBoard.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent mce)
			{
				//�����λ��ת������������λ��
				int xClick = (mce.getX() - X_OFFSET) / Rate;
				int yClick = (mce.getY() - Y_OFFSET) / Rate;
				if(board[xClick][yClick].equals("��") || board[xClick][yClick].equals("��"))
					//��ʾ��λ���Ѿ�������
				{
					hadChess = true;
					chessBoard.repaint();
				}
				else
				{
					board[xClick][yClick] = "��";
					hadChess = false;
					chessBoard.repaint();
					//���õ������庯��
					computerPlay(xClick, yClick);
				}
			
			}
		});
		frame.add(chessBoard);
		frame.setBounds(10,10,TABLE_WIDTH,TABLE_HEIGTH);
		frame.setVisible(true);
	}
	//����������庯��
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
		if(board[xPos][yPos].equals("��") || board[xPos][yPos].equals("��"))
			//����Ѱ��λ������
		{
			computerPlay(x,y);
		}
		else
		{
			board[xPos][yPos] = "��";
			hadChess = false;
			chessBoard.repaint();
		}	
	}
	
	public static void main(String[] args) throws Exception {
		new GoBang().init();
	}

	//�Զ��廭����
	class ChessBoard extends JPanel
	{
		public void paint(Graphics g)
		{
			//��������
			g.drawImage(table, 0, 0, null);
			//����ѡ���
			if(selectedX >=0 && selectedY >=0)
			g.drawImage(selected, selectedX * Rate + X_OFFSET - selected.getWidth()/4, selectedY *Rate + Y_OFFSET - selected.getHeight()/4, null);
			//���ƺ��塢����
			for(int i =0;i < board.length; i++)
			{
				for(int j =0; j < board[i].length; j++)
				{
					if(board[i][j].equals("��"))
						g.drawImage(black, i * Rate + X_OFFSET, j * Rate + Y_OFFSET, null);
					if(board[i][j].equals("��"))
						g.drawImage(white, i * Rate + X_OFFSET, j * Rate + Y_OFFSET, null);
				}
			}
			if(hadChess)
			{
				g.setColor(Color.RED);
				g.setFont(new Font("Times", Font.BOLD, 60));
				g.drawString("��λ���������ӣ�", 20, 260);
			}
		}
	}
}
