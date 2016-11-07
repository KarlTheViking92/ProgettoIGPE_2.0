package core.World;


import java.util.List;

import core.element.Item;
import core.element.block.Block;
import core.element.character.Character;
import core.element.character.Player;
import javafx.geometry.Point2D;

public interface World {
	
	// world size 
	abstract public float getWidth();
	abstract public float getHeight();
	abstract public void setDimension(float h, float w);
	
	// player and enemies
	abstract public List<Player> getPlayers();
	abstract public List<Character> getEnemies();
	
	// world function
	abstract public void initialize();
	abstract public void update();
	abstract public void reset();
	
	// map function
	abstract public void loadMap(String path); // forse non serve ?
	abstract public BlockMap getMap();
	abstract public List<Block> getNearBlocks();
	abstract public Block[][] getMatrix();
	
	abstract public boolean checkPlayerCollision(Character player, double x , double y);
	abstract public boolean checkBlockCollision(Block b,double x, double y, int w);
	// checkpoint
	
	abstract public Point2D getCheckPoint();
	abstract public List<Item> getGems();
	
}
