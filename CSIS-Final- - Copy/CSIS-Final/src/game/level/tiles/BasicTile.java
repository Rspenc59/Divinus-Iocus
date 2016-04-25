package game.level.tiles;

import game.gfx.Screen;
import game.level.Level;

public class BasicTile extends Tile {
	
	protected int tileid;
	protected int tileColor;

	public BasicTile(int id, int x, int y, int tileColor, int levelColor) {
		super(id, false,false,levelColor);
		this.tileid=x+y;
		this.tileColor=tileColor;
	}

	public void tick(){
		
	}
	
	public void render(Screen screan, Level level, int x, int y) {
		screan.render(x, y, tileid, tileColor,0x00,1);
	}
	
}
