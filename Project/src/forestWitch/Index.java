package forestWitch;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * 游戏主类，负责切换游戏状态
 */
public class Index extends JFrame implements Runnable,KeyListener,MouseListener{
	Image offScreen;	// 次画面
	Graphics offScreenGraphics;	// 次画面图形对象
	GameState curState;	// 当前游戏状态
	Thread thread;	// 线程
	static Index instance;	// 实例
	AudioClip menu,bg;
	
	/**
	 * 单例模式
	 * @return instance Index实例
	 */
	public static Index getInstance(){
		if(instance == null){
			instance = new Index();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		Index.getInstance();
	}
	
	/**
	 * 构造函数
	 */
	public Index(){
		this.setBounds(500,50,1000,960);
//		this.setLayout(new FlowLayout());
		this.setResizable(false);	// 不可更改窗口大小
		this.addListener();
		this.setVisible(true);

		GameData.game.stop();
		GameData.menu.loop();
		
		// 设置当前游戏状态
		curState=new Startup();	// 启动画面
		thread=new Thread(this);
		thread.start();   //启动run()方法
	}
	
	/**
	 * 添加监听事件
	 */
	void addListener(){
		this.addKeyListener(this);
		this.addMouseListener(this);
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent we){
				System.exit(0);
			}
		});
	}

	@Override
	public void run() {
		while(thread != null){
			curState.update(); // 更新当前状态
			
			try {
				Thread.sleep(40);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			repaint();
		}
	}
	
	/**
	 * 绘制容器内的画面
	 */
	public void paint(Graphics g){
		if(curState == null) return;
		int width=getWidth();
		int height=getHeight();
		
		// 用白色次画面对主画面进行刷屏
		if(offScreen == null){
			offScreen = createImage(width,height);
			offScreenGraphics = offScreen.getGraphics();
		}
		offScreenGraphics.setColor(Color.white);
		offScreenGraphics.fillRect(0, 0, width, height);
		curState.draw(offScreenGraphics);
		g.drawImage(offScreen, 0, 0, this);
	}
	
	public void update(Graphics g){
		paint(g);
	}
	
	/**
	 * 切换游戏状态
	 * @param nextState 下个状态
	 */
	public void changeStateTo(GameState nextState){
		this.curState = nextState;
		validate();	// 让原窗口失效
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		curState.mouseClicked(e); // 传入当前点击事件
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		curState.keyPressed(e);  // 传入当前按键事件
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
}
