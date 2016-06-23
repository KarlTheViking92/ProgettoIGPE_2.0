package core.gameManagers;

import java.util.List;

import core.World.AbstractWorld;
import core.World.World;
import core.element.block.Block;
import core.element.character.Direction;
import core.element.character.Player;
import javafx.scene.input.KeyCode;

public class PlayManager {

	private static PlayManager instance;

	private World world;

	private boolean running = false;
	private boolean pause = false;

	private Player player;
	private boolean isDead = false;

	private PlayManager() {
	}

	public static PlayManager getInstance() {
		if (instance == null)
			instance = new PlayManager();

		return instance;
	}

	public void init() {

		world = new AbstractWorld();
		world.loadMap("resources/Levels/levelTest");
		world.initialize();
		this.player = world.getPlayer();

	}

	public void start() {
		running = true;
	}

	public void pause() {
		pause = true;
	}

	public void resume() {
		if (pause)
			pause = false;
	}

	public void update() {
		if (!isDead && !pause) {

			player.update();
		}
	}

//	private boolean checkCollision() {

		/*
		 * for (Block b : getBlocks()) { if (b.collision(player.getX(),
		 * player.getY(), player.getHeight(), player.getWidth())) {
		 * System.out.println("COLLIDO CON BLOCCO : " + b.getX() + "  " +
		 * b.getY()); return true; } }
		 */
//		return false;
//	}

	public void movePlayer(Direction direction) {

		player.setDirection(direction);
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
		return player;
	}

	public void playerJump() {

		if (player.canJump() && player.canDoubleJump()) {
			player.jump();
		} else if (player.canDoubleJump())
			player.doubleJump();
	}

}
