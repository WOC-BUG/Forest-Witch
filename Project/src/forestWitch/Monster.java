package forestWitch;

import java.awt.Graphics;
import java.awt.Image;

public class Monster extends Actor{

	public Monster(int x, int y, int vx, int vy, int lenX, int lenY, Image img) {
		super(x, y, vx, vy, lenX, lenY, img);
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(img, x, y, lenX, lenY, null);
	}

	@Override
	public Actor collide(GamePlay game) {
		return null;
	}

}
