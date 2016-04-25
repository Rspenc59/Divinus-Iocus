package game.level.tiles;

import game.gfx.Screen;
import game.level.Level;

public class Animated_Tile extends BasicTile {
	
	private int [][]animTileCoords;
	private int currentAnim;
	private long lastItTime;
	private int animSwitch;

	public Animated_Tile(int id, int [][] animCoords, int tileColor, int levelColor,int animSwitch) {
		super(id, animCoords[0][0], animCoords[0][1], tileColor, levelColor);
		this.animTileCoords = animCoords;
		this.currentAnim = 0;
		this.lastItTime = System.currentTimeMillis();
		this.animSwitch = animSwitch;
	}
	public void tick(){
		if((System.currentTimeMillis()-lastItTime)>=(animSwitch)){
			lastItTime = System.currentTimeMillis();
			currentAnim =(currentAnim + 1)% animTileCoords.length;
			this.tileid = (animTileCoords[currentAnim][0]+(animTileCoords[currentAnim][1]*32));	
		}
	}
}
