package game.level.tiles;

import game.gfx.Colors;
import game.gfx.Screen;
import game.level.Level;

public abstract class Tile {

	public static final Tile[] tiles = new Tile[256];
	public static final Tile VOID = new Solid_Tile(0,0,0,Colors.get(000, -1, -1, -1),0xff000000);
	public static final Tile STONE = new Solid_Tile(1,1,0,Colors.get(-1, 333, -1, -1),0xff555555);
	public static final Tile GRASS = new BasicTile(2,2,0,Colors.get(-1, 131, 141, -1),0xff00ff00);
	public static final Tile WATER = new Animated_Tile(3,new int[][]{{0,4},{1,4},{2,4},{1,4}},
			Colors.get(-1, 004, 115, -1),0xff0000ff,1000);
	
	protected byte id;
	protected boolean solid;
	protected boolean emitter;
	private int levelColor;
	
	public Tile(int id, boolean isSolid, boolean isEmitter, int levelColor){
		this.id = (byte)id;
		if (tiles[id]!= null)throw new RuntimeException("Duplicatetile id on"+id);
		this.solid = isSolid;
		this.emitter = isEmitter;
		this.levelColor = levelColor;
		tiles[id]=this;
	}
	
	public byte getId(){
		return id;
	}
	public boolean isSolid(){
		return solid;
	}
	public boolean isEmitter(){
		return emitter;
	}
	public int getColor(){
		return levelColor;
	}

	public abstract void tick();
	
	public abstract void render(Screen screan, Level level, int x, int y);
		
	}

