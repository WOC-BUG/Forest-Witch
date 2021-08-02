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
 * ��Ϸ���࣬�����л���Ϸ״̬
 */
public class Index extends JFrame implements Runnable,KeyListener,MouseListener{
	Image offScreen;	// �λ���
	Graphics offScreenGraphics;	// �λ���ͼ�ζ���
	GameState curState;	// ��ǰ��Ϸ״̬
	Thread thread;	// �߳�
	static Index instance;	// ʵ��
	AudioClip menu,bg;
	
	/**
	 * ����ģʽ
	 * @return instance Indexʵ��
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
	 * ���캯��
	 */
	public Index(){
		this.setBounds(500,50,1000,960);
//		this.setLayout(new FlowLayout());
		this.setResizable(false);	// ���ɸ��Ĵ��ڴ�С
		this.addListener();
		this.setVisible(true);

		GameData.game.stop();
		GameData.menu.loop();
		
		// ���õ�ǰ��Ϸ״̬
		curState=new Startup();	// ��������
		thread=new Thread(this);
		thread.start();   //����run()����
	}
	
	/**
	 * ��Ӽ����¼�
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
			curState.update(); // ���µ�ǰ״̬
			
			try {
				Thread.sleep(40);
			}catch(InterruptedException e){
				e.printStackTrace();
			}
			
			repaint();
		}
	}
	
	/**
	 * ���������ڵĻ���
	 */
	public void paint(Graphics g){
		if(curState == null) return;
		int width=getWidth();
		int height=getHeight();
		
		// �ð�ɫ�λ�������������ˢ��
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
	 * �л���Ϸ״̬
	 * @param nextState �¸�״̬
	 */
	public void changeStateTo(GameState nextState){
		this.curState = nextState;
		validate();	// ��ԭ����ʧЧ
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		curState.mouseClicked(e); // ���뵱ǰ����¼�
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		curState.keyPressed(e);  // ���뵱ǰ�����¼�
	}

	@Override
	public void keyReleased(KeyEvent e) {}
	
}
