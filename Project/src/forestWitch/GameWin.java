package forestWitch;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class GameWin implements GameState{
	
	int life;	// ������ʱ��
	Image win;	// ͼƬ
	int curPos,endPos,step;	//��ʼ�߶�,����λ��,����,����ͼƬ�߶Ȳ�
	
	GameWin(){
		// ���ͼƬ
		win=new ImageIcon(getClass().getResource("../imgs/win.png")).getImage();

		GameData.game.stop();
		GameData.menu.loop();
		
		curPos=600;
		endPos=450;
		step=3;
	}
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
		g.drawImage(win,300,curPos,null);
	}

	@Override
	public void transactionState() {
		Startup su=new Startup();
		Index.getInstance().changeStateTo(su);
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
