package forestWitch;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

public class Text extends Actor{

	public Text(int x, int y, int vx, int vy, int lenX, int lenY, Image img) {
		super(x, y, vx, vy, lenX, lenY, img);
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("ºÚÌå",Font.PLAIN,30));
		g.drawString(""+GameData.curStep[GameData.curLevel],x,y);
	}

	@Override
	public Actor collide(GamePlay game) {
		return null;
	}

}
