package forestWitch;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Bullet extends Actor{

	public Bullet(int x, int y, int vx, int vy, int lenX, int lenY, Image img) {
		super(x, y, vx, vy, lenX, lenY, img);
	}
	
	public void setV(int vx,int vy){
		this.vx=vx;
		this.vy=vy;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
	
	public void update(){
		x += vx;
		y += vy;
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, x, y, lenX, lenY, null);
	}

	@Override
	public Actor collide(GamePlay game) {
		ArrayList<Actor> allActors = game.getAllActors();
		for(Actor a: allActors){
			if(a instanceof Bullet || a instanceof Thorn)	//跳过自己和尖刺
				continue;
			boolean flag = this.collide2Actor(a);
			if(flag){
				return a;
			}
		}
		return null;
	}

}
