package core.gameManagers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import core.World.AbstractWorld;
import core.World.World;
import core.element.Bullet;
import core.element.Item;
import core.element.block.Block;
import core.element.character.Character;
import core.element.character.Direction;
import core.element.character.MeleeEnemy;
import core.element.character.Player;
import game.GameSelector;
import gui.panel.singlePlayer.SinglePlayerPane;
import gui.popup.Popup;
import gui.popup.PopupError;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

public class PlayManager {

	private static PlayManager instance;

	private World world;

	public boolean finishLevel = false;
	private boolean pause = false;

	private List<Player> players = new ArrayList<>();
	private Player currentPlayer;
	private List<Item> bullets = new ArrayList<>();
	private List<Character> currentEnemy = new ArrayList<>();
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
		// running = true;
	}

	public void pause() {
		pause = true;
	}

	public void resume() {
		if (pause) {
			pause = false;

		}
	}

	public void restart() {
		System.out.println("restart playmanager");
		finishLevel = false;
		world = null;
		currentEnemy.clear();
		players.clear();
		resume();
	}

	public boolean isPaused() {
		return pause;
	}

	public void finishLevel() {
		if (currentPlayer.getCollectedGems() >= 3)
			finishLevel = true;
	}

	public void update() {
		world.update();

		for (Character enemy : currentEnemy) {
			enemy.searchPlayer(currentPlayer);
			checkBullet(enemy);
			enemy.update();
		}
		for (Player player : players) {
			if (!player.isDead()) {
				checkBullet(player);
				player.update();
				lastMillis = System.currentTimeMillis();
			} else {
				current = System.currentTimeMillis();

				if (current - lastMillis >= 1000)
					player.respawn();
			}
		}
		for (Item b : bullets) {
			b.update();
		}
		removeBullet();
		removeEnemy();
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

	public Block[][] getBlocksMatrix() {
		return world.getMatrix();
	}

	public Player getPlayer() {
		return currentPlayer;
	}

	public List<Character> getEnemies() {
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

	public void checkBullet(Character c) {
		List<Bullet> tmp = c.getBullet();
		if (!tmp.isEmpty()) {
			Iterator i = tmp.iterator();
			while (i.hasNext()) {
				Bullet bullet = (Bullet) i.next();
				bullets.add(bullet);
				i.remove();
			}
		}
	}

	private void removeBullet() {
		Iterator i = bullets.iterator();
		while (i.hasNext()) {
			Bullet bullet = (Bullet) i.next();
			if (bullet.hasCollided())
				i.remove();
		}
	}

	private void removeEnemy() {
		Iterator i = currentEnemy.iterator();
		while (i.hasNext()) {
			Character enemy = (Character) i.next();
			if (enemy.isDead()) {
				i.remove();
			}
		}
	}

	public List<Item> getBullets() {
		return bullets;
	}

}
