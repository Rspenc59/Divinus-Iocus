import java.awt.Graphics;
import java.awt.Image;

public class Player extends GameObject {

	public boolean up;
	public boolean down;
	public int health;
	public int startX, startY;

	private static final int speed = 5;
	private static final int STARThealth = 10;

	public Player(int x, int y, int width, int height, Image img) {
		super(x, y, width, height, img);
		health = STARThealth;
		this.startX = x;
		this.startY = y;
	}

	@Override
	public void update() {
		if (up) {
			if (y >= 0) {
				y -= speed;
				rect.y -= speed;
			}
		}
		if (down) {
			if (y <= 490) {
				y += speed;
				rect.y += speed;
			}
		}
	}

	@Override
	public void draw(Graphics g) {
		g.drawImage(img, x, y, width, height, null);
	}

	public void reset() {
		this.x = startX;
		this.y = startY;
		this.rect.x = startX;
		this.rect.y = startY;
		this.health = STARThealth;
	}

}
