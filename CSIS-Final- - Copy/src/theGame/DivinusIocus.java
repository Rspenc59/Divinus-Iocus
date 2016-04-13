//Created With Tutorial By DesignsbyZephyr
package theGame;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import theGFX.Colors;
import theGFX.Screen;
import theGFX.SpriteSheet;

public class DivinusIocus extends Canvas implements Runnable {

	public static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 160;
	public static final int HEIGHT = WIDTH/12*9;
	public static final int SCALE = 3;
	public static final String NAME = "quod Divinus Iocus";
	
	private JFrame screen;
	
	public boolean running = false;
	public int tickCount = 0;
	
	private BufferedImage image = new BufferedImage (WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private int[] pixels =((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	private int[] colours = new int[6*6*6];
	
	public Screen screan;
	public InputHandler input;
	
public DivinusIocus(){
	setMinimumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
	setMaximumSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
	setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
	
	screen = new JFrame(NAME);
	
	screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	screen.setLayout(new BorderLayout());
	
	screen.add(this,BorderLayout.CENTER);
	screen.pack();
	
	screen.setResizable(false);
	screen.setLocationRelativeTo(null);
	screen.setVisible(true);
}

	public void init(){
		int index=0;
		for (int r = 0;r<6;r++){
			for (int g = 0; g<6;g++){
				for (int b = 0; b<6;b++){
					int rr = (r*255/5);
					int gg = (g*255/5);
					int bb = (b*255/5);
					
					colours [index++]=rr<<16|gg<<8|bb;
				}
			}
		}
		
		screan = new Screen(WIDTH,HEIGHT,new SpriteSheet("/Sprite_Sheet.png"));
		input = new InputHandler(this);
	}

	public synchronized void start(){
		running = true;
		new Thread(this).start();
	}
	public synchronized void stop(){
		running = false;
	}
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		init();
		
		while(running){
			long now =System.nanoTime();
			delta+=(now-lastTime)/nsPerTick;
			lastTime=now;
			boolean shouldRender = true;
			
			while(delta>=1){
				ticks++;
				tick();
				delta-=1;
			}
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
			if (shouldRender){
				frames++;
				render();
			}
			if (System.currentTimeMillis()-lastTimer>=100){
				lastTimer+=1000;
				frames=0;
				ticks=0;
			}
		}
	}
	
	public void tick(){
		tickCount++;
		if (input.up.isPressed()){
			screan.yOffset--;
			}
		if (input.down.isPressed()){
			screan.yOffset++;
			}
		if (input.left.isPressed()){
			screan.xOffset--;
			}
		if (input.right.isPressed()){
			screan.xOffset++;
			}
		}
	
	public void render(){
		BufferStrategy bs = getBufferStrategy();
		if (bs==null){
			createBufferStrategy(3);
			return;
		}
		
		for (int y=0; y<32;y++){
			for (int x=0; x<32;x++){
				boolean flipX=x%2==1;
				boolean flipY=y%2==1;
				screan.render(x<<3, y<<3, 0,Colors.get(555,500,050,005),flipX,flipY);
			}
		}
		for (int y=0; y<screan.height;y++){
			for (int x=0; x<screan.width;x++){
				int colorCode = screan.pixel[x+y*screan.width];
				if(colorCode<255)pixels[x+y*WIDTH]=colours[colorCode];
			}
		}
		
		
		Graphics g=bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0,getWidth(),getHeight());
		
		g.drawImage(image, 0, 0, getWidth(),getHeight(),null);
		
		g.dispose();
		bs.show();
	}
	public static void main (String[]args){
		new DivinusIocus().start();
	}
}
