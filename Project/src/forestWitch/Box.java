package forestWitch;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Box extends Actor{
	Image empty;
	public Box(int x, int y, int vx, int vy, int lenX, int lenY, Image img) {
		super(x, y, vx, vy, lenX, lenY, img);
		empty = new ImageIcon(getClass().getResource("../imgs/empty.png")).getImage();
	}

	@Override
	public void render(Graphics g) {
		if(GameData.ifOpenBox){
			img = empty;
		}
		g.drawImage(img, x, y, lenX, lenY, null);
	}

	@Override
	public Actor collide(GamePlay game) {
		return null;
	}
	
}
