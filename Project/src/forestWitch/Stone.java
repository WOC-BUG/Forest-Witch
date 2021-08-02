package forestWitch;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Stone extends Actor{

	Actor actor=null;
	public Stone(int x, int y, int vx, int vy, int lenX, int lenY, Image img) {
		super(x, y, vx, vy, lenX, lenY, img);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, x, y, lenX, lenY, null);
	}
	
	public void moveUp(){
		y += vy;
	}
	public void moveDown(){
		y += vy;
	}
	public void moveLeft(){
		x += vx;
	}
	public void moveRight(){
		x += vx;
	}

	public void setVx(int vx){
		this.vx=vx;
	}
	
	public void setVy(int vy){
		this.vy=vy;
	}
	
	public void setSelf(Actor a){
		actor=a;
	}
	
	@Override
	public Actor collide(GamePlay game) {
		ArrayList<Actor> allActors = game.getAllActors();
		for(Actor a: allActors){
			if(a == actor || a instanceof Thorn){	// 跳过自己和尖刺
				continue;
			}
			boolean flag = this.collide2Actor(a);
			if(flag){
				return a;
			}
		}
		return null;
	}

}
