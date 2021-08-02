package forestWitch;

import java.awt.Graphics;
import java.awt.Image;

abstract public class Actor {
	int x,y;
	int vx,vy;
	int lenX,lenY;
	Image img;
	
	public Actor(int x, int y,int vx,int vy,int lenX,int lenY,Image img){
		this.x=x;
		this.y=y;
		this.vx=vx;
		this.vy=vy;
		this.lenX=lenX;
		this.lenY=lenY;
		this.img=img;
	}
	
	// ¸üÐÂ×ø±ê
	public void update() { }	
	public void moveUp(){ }
	public void moveDown(){ }
	public void moveLeft(){ }
	public void moveRight(){ }
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	
	/**
	 * Åö×²ÆäËûÎïÌå
	 * @param other
	 * @return ÊÇ·ñ·¢ÉúÅö×²
	 */
	public boolean collide2Actor(Actor other) {
		boolean flag=false;
		// Åö×²×ó±ßÔµ
//		if(other.x==220 && other.y==380){
//			System.out.println("x = "+x+",vx = "+vx+",lenX = "+lenX+",x+vx+lenX = "+(x+vx+lenX));
//			System.out.println("other.x = "+other.x + ",other.lenX = "+other.lenX+",sum = "+(other.x+other.lenX));
//			System.out.println("y = "+y+",vy = "+vy+",y+vy = "+(y+vy));
//			System.out.println("other.y = "+other.y + ",other.lenY = "+other.lenY+",sum = "+(other.y+other.lenY));	
//		}
		// [307]>220 , [307]<380 , [658]<740 [658]>380
		if(x+vx+lenX>=other.x && x+vx+lenX<=other.x+ other.lenX && ((y+vy<=other.y+other.lenY && y+vy>=other.y)||(y+vy+lenY<=other.y+other.lenY && y+vy+lenY>=other.y)))
		{
			flag=true;
		}
		// Åö×²ÓÒ±ßÔµ
		if(x+vx<=other.x+vx+other.lenX && x+vx>=other.x && ((y+vy<=other.y+other.lenY && y+vy>=other.y)||(y+vy+lenY<=other.y+other.lenY && y+vy+lenY>=other.y)))
		{
			flag=true;
		}
		// Åö×²ÉÏ±ßÔµ
		if(y+vy+lenY>=other.y && y+vy+lenY<=other.y+other.lenY && ((x+vx<=other.x+other.lenX && x+vx>=other.x)||(x+vx+lenX<=other.x+other.lenX && x+vx+lenX>=other.x)))
		{
			flag=true;
		}
		// Åö×²ÏÂ±ßÔµ
		if(y+vy<=other.y+other.lenY && y+vy>=other.y && ((x+vx<=other.x+other.lenX && x+vx>=other.x)||(x+vx+lenX<=other.x+other.lenX && x+vx+lenX>=other.x)))
		{
			flag=true;
		}
		return flag;
	}
	
	abstract public void render(Graphics g);
	
	abstract public Actor collide(GamePlay game);
}
