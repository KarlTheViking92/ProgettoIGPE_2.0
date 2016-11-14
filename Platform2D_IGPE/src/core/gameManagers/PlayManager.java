package core.gameManagers;

import java.util.ArrayList;
import java.util.List;

import core.World.AbstractWorld;
import core.World.World;
import core.element.Item;
import core.element.block.Block;
import core.element.character.Character;
import core.element.character.Direction;
import core.element.character.MeleeEnemy;
import core.element.character.Player;
import game.GameSelector;
import gui.panel.singlePlayer.SinglePlayerPane;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

public class PlayManager {

	private static PlayManager instance;

	private World world;

//	private boolean running = false;
	private boolean finishLevel = false;
	private boolean pause = false;

	private List<Player> players = new ArrayList<>();
	private Player currentPlayer;
	private List<Character> currentEnemy = new ArrayList<>();
	private boolean isDead = false;

	private long lastMillis;
	private long current;

	private PlayManager() {
	}

	public static PlayManager getInstance() {
		if (instance == null)
			instance = new PlayManager();

		return instance;
	}

	public void init(GameSelector game) {
		
		System.out.println("ricarico il manager");
		world = new AbstractWorld();

		currentPlayer = new Player(game.getPlayerName(), 10, 1, world);
		currentPlayer.setType(game.getPlayerType());
		players.add(currentPlayer);
		System.out.println("nel manager la map è " + game.getMapName());
		world.loadMap(game.getMapName());
		world.initialize();
		currentEnemy = world.getEnemies();

	}

	public void start() {
//		running = true;
	}

	public void pause() {
		pause = true;
	}

	public void resume() {
		if (pause){
			pause = false;
			
		}
	}
	
	public void restart(){
		resume();
		finishLevel = false;
		world = null;
		currentEnemy.clear();
		players.clear();
	}
	
	public boolean isPaused(){
		return pause;
	}

	public void finishLevel(){
		if(currentPlayer.getCollectedGems() >= 3)
			finishLevel = true;
	}
	
	public void update() {
		world.update();
		for (Character meleeenemy : currentEnemy) {
			meleeenemy.searchPlayer(currentPlayer);
			meleeenemy.update();
		}
		for (Player player : players) {
			if (!player.isDead()) {
				player.update();
				lastMillis = System.currentTimeMillis();
			} else {
				// System.out.println("sei morto");
				current = System.currentTimeMillis();

				if (current - lastMillis >= 1000)
					player.respawn();
			}
		}

	}

	public void movePlayer(Direction direction) {
		currentPlayer.setDirection(direction);
	}

	public double getLevelWidth() {
		return world.getWidth();
	}

	public double getLevelHeight() {
		return world.getHeight();
	}

	// public List<Block> getBlocks() {
	// return world.getBlocks();
	// }

	public Block[][] getBlocksMatrix() {
		return world.getMatrix();
	}

	public Player getPlayer() {
		return currentPlayer; // temporaneamente null
		// return players;
	}

	public List<Character> getMeleeEnemy() {
		return currentEnemy;
	}

	public void playerJump() {

		if (currentPlayer.canJump() && currentPlayer.canDoubleJump()) {
			currentPlayer.jump();
		} else if (currentPlayer.canDoubleJump())
			currentPlayer.doubleJump();
	}

	public List<Item> getGemList() {
		return world.getGems();
	}

	public boolean isFinished() {
		return finishLevel;
	}

}
