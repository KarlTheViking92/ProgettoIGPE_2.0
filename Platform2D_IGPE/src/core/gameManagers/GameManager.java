package core.gameManagers;

import java.util.List;

import core.element.Item;
import core.element.block.Block;
import core.element.character.Character;
import core.element.character.Direction;
import core.element.character.Player;
import game.GameSelector;

public interface GameManager {
	
	public static GameManager getInstance() {
		return null;
	}
	
	public abstract void init(GameSelector game);
	
	public abstract void pause() ;
	
	public abstract void resume();
	public abstract void restart();
	
	public abstract boolean isPaused();
	
	public abstract void finishLevel();
	
	public abstract void update();
	
	public abstract void movePlayer(Direction direction);
	
	public abstract double getLevelWidth();
	public abstract double getLevelHeight();
	
	public abstract Block[][] getBlocksMatrix();
	
	public abstract Player getPlayer();
	
	public abstract List<Character> getMeleeEnemy();
	
	public abstract void playerJump();
	
	public abstract List<Item> getGemList();

	public abstract boolean isFinished();

}
