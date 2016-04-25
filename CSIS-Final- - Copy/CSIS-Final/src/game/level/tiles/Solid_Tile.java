package game.level.tiles;

public class Solid_Tile extends BasicTile {

	public Solid_Tile(int id, int x, int y, int tileColor,int levelColor) {
		super(id, x, y, tileColor,levelColor);
		this.solid =true;
	}

}
