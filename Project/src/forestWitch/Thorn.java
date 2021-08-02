package forestWitch;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Thorn extends Actor{
	Image odd,even; //ÆæÅ¼
	public Thorn(int x, int y, int vx, int vy, int lenX, int lenY, Image img) {
		super(x, y, vx, vy, lenX, lenY, img);
		odd=new ImageIcon(getClass().getResource("../imgs/trap.png")).getImage();
		even=new ImageIcon(getClass().getResource("../imgs/empty.png")).getImage();
	}

	@Override
	public void render(Graphics g) {
		if(GameData.oddEven==0){
			img = odd;
		}else{
			img = even;
		}
		g.drawImage(img, x, y, lenX, lenY, null);
	}

	@Override
	public Actor collide(GamePlay game) {
		ArrayList<Actor> allActors = game.getAllActors();
		for(Actor a: allActors){
			if(a instanceof Thorn)	// Ìø¹ý×Ô¼º£¬ºÍÏÝÚå
				continue;
			boolean flag = this.collide2Actor(a);
			if(flag && a instanceof Witch){
				System.out.println("Åö×²£¡");
				return a;
			}
		}
		return null;
	}

}
