package forestWitch;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;

public class GameLose implements GameState{
	
	int life;	// ������ʱ��
	Image lose;	// ͼƬ
	int curPos,endPos,step;	//��ʼ�߶�,����λ��,����,����ͼƬ�߶Ȳ�
	
	GameLose(){
		// ���ͼƬ
		lose=new ImageIcon(getClass().getResource("../imgs/lose.png")).getImage();
		
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
		g.drawImage(lose,300,curPos,null);
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
