import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class GameObject {

	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Image img;
	protected Rectangle rect;

	public GameObject(int x, int y, int width, int height, Image img) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.img = img;
		this.rect = new Rectangle(x, y, width, height);
	}
	
	abstract void update();
	abstract void draw(Graphics g);

}
