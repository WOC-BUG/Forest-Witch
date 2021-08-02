package forestWitch;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.ImageIcon;

/**
 * ��������
 */
public class Startup implements GameState{
	
	int life;	// ������ʱ��
	Image title,img;	// ͼƬ
	int curPos,endPos,step,distance;	//��ʼ�߶�,����λ��,����,����ͼƬ�߶Ȳ�
	
	public Startup(){
		// ���ͼƬ
		title=new ImageIcon(getClass().getResource("../imgs/title.png")).getImage();
		img=new ImageIcon(getClass().getResource("../imgs/info.png")).getImage();

		curPos=600;
		endPos=350;
		step=3;
		distance=200;
	}
	
	/**
	 * ����״̬
	 */
	@Override
	public void update() {
		if(curPos <= endPos){
			transactionState();
		}else{
			curPos -= step;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0,0,1000,960);
		g.drawImage(title,260,curPos,null);
		g.drawImage(img,350,curPos+distance,null);
	}

	@Override
	public void transactionState() {
		MainMenu mm=new MainMenu();
		Index.getInstance().changeStateTo(mm);
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		transactionState();
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		transactionState();
	}
	
}
