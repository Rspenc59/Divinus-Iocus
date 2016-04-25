package game.level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import game.entities.Entity;
import game.gfx.Screen;
import game.level.tiles.Tile;

public class Level {
	private byte[] tiles;
	public int width;
	public int height;
	public List<Entity>entities = new ArrayList<Entity>();
	private String imagePath;
	private BufferedImage image;
	
	public Level(String imagePath){
		if (imagePath != null){
			this.imagePath = imagePath;
			this.loadLevelFromFile();
		}else {
		this.width =64;
		this.height =64;
		tiles=new byte[width*height];
		this.generateLevel();
		}
	}
	
	private void loadLevelFromFile(){
		try{
			this.image = ImageIO.read(Level.class.getResourceAsStream(this.imagePath));
			this.width = image.getWidth();
			this.height = image.getHeight();
			tiles = new byte [width*height];
			this.loadTiles();
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	private void loadTiles(){
		int[] tilesColors = this.image.getRGB(0, 0, width, height, null, 0, width);
		for (int y = 0; y<height;y++){
			for (int x = 0; x<width;x++){
				tileCheck: for(Tile t : Tile.tiles){
					if (t!= null && t.getColor()==(tilesColors[x+y*width])){
						this.tiles[x+y*width]=t.getId();
						break tileCheck;
					}
				}
			}
		}
		
	}
	
	private void saveLevelToFile(){
		try{
			ImageIO.write(image,"png", new File(Level.class.getResource(this.imagePath).getFile()));
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void alterTile(int x, int y, Tile newTile){
		this.tiles[x+y*width]= newTile.getId();
		image.setRGB(x, y, newTile.getColor());
	}
	
	public void generateLevel(){
		for(int y=0; y<height;y++){
			for (int x=0; x<width;x++){
			if(x*y%10<7){	
				tiles[x+y*width]=Tile.GRASS.getId();
			}else{
				tiles[x+y*width]=Tile.STONE.getId();
			}
		}
	}
}
	
	public void tick(){
		for (Entity e : entities){
			e.tick();
		}
		for (Tile t : Tile.tiles){
			if (t==null){
				break;
			}
			t.tick();
		}
	}
	
	public void renderTiles(Screen screan,int yOffset,int xOffset){
		if(xOffset<0)xOffset=0;
		if(xOffset>(width<<3)-screan.width)xOffset=((width<<3)-screan.width);
		if(yOffset<0)yOffset=0;
		if(yOffset>(height<<3)-screan.height)yOffset=((height<<3)-screan.height);
		
		screan.setOffset(xOffset, yOffset);
		
		for (int y=0; y<height;y++){
			for (int x=0;x<width;x++){
				getTile(x,y).render(screan,this,x<<3,y<<3);
			}
		}
	}
	public void renderEntities(Screen screan){
		for (Entity e : entities){
			e.render(screan);
		}
	}
	
	public Tile getTile(int x, int y) {
		if(x<0||x>=width||y<0||y>=height)
			return Tile.VOID;
		return Tile.tiles[tiles[x+y*width]];
	}
	public void addEntity(Entity entity){
		this.entities.add(entity);
	}
}
