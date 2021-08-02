package forestWitch;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;


public class Witch extends Actor{
	Image img_left,img_right; //����ͼƬ�ز�
	
	public Witch(int x, int y, int vx,int vy, int lenX, int lenY, Image img) {
		super(x, y, vx, vy, lenX, lenY, img);
		
		img_left=new ImageIcon(getClass().getResource("../imgs/witch_Q_Left.PNG")).getImage();
		img_right=new ImageIcon(getClass().getResource("../imgs/witch_Q_Right.PNG")).getImage();	
	}

	public void moveUp(){
		y += vy;
	}
	public void moveDown(){
		y += vy;
	}
	public void moveLeft(){
		img = img_left;
		x += vx;
	}
	public void moveRight(){
		img = img_right;
		x += vx;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void setVx(int vx){
		this.vx=vx;
	}
	
	public void setVy(int vy){
		this.vy=vy;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(img, x, y, lenX, lenY, null);
	}

	@Override
	public Actor collide(GamePlay game) {
		ArrayList<Actor> allActors = game.getAllActors();
		for(Actor a: allActors){
			if(a instanceof Witch || a instanceof Thorn)	// �����Լ���������   
				continue;
			boolean flag = this.collide2Actor(a);
			if(flag){
				System.out.println("��ײ��");
				return a;
			}
		}
		return null;
	}
	
	/**
	 * �����ж��Ƿ�ײ�����
	 * @param game
	 * @return
	 */
	public Actor collideThorn(GamePlay game) {
		ArrayList<Actor> allActors = game.getAllActors();
		for(Actor a: allActors){
			if(a instanceof Witch)	// �����Լ�
				continue;
			boolean flag = this.collide2Actor(a);
			if(flag){
				System.out.println("��ײ��");
				if(a instanceof Thorn)
					return a;
				else
					return null;
			}
		}
		return null;
	}

}
