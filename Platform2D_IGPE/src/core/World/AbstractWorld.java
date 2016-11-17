package core.World;

import java.util.ArrayList;
import java.util.List;

import core.element.Bullet;
import core.element.Item;
import core.element.Position;
import core.element.block.Block;
import core.element.block.MoveableBlock;
import core.element.block.SuperJumpBlock;
import core.element.character.Character;
import core.element.character.Direction;
import core.element.character.Player;
import core.gameManagers.PlayManager;
import javafx.geometry.Point2D;

public class AbstractWorld implements World {

	protected BlockMap map;

	private float width = 0;
	private float height = 0;

	protected List<Player> players;

	// Modificare la parte del codice in caso di aggiunta del multiplayer(serve
	// la lista)
	private Player player;

	private List<Item> gems;
	private List<Character> enemies;
	private String level;

	public AbstractWorld() {

	}

	@Override
	public float getWidth() {
		return width;
	}

	@Override
	public float getHeight() {
		return height;
	}

	@Override
	public void setDimension(float height, float width) {
		this.height = height;
		this.width = width;
	}

	@Override
	public List<Player> getPlayers() {
		return players;
	}

	@Override
	public List<Character> getEnemies() {
		return enemies;
	}

	@Override
	public void initialize() {

		this.map = new BlockMap(level, this);
		this.map.loadMap();

		this.width = (float) (map.getColumns() * map.getBlockSize());
		this.height = (float) (map.getRows() * map.getBlockSize());
		this.players = new ArrayList<>();
		this.enemies = map.enemies;

		player = PlayManager.getInstance().getPlayer();
		player.setPosition(new Position(map.getSpawnPoint().getX(), map.getSpawnPoint().getY()));
		System.out.println("inizializzo il mondo");
		this.gems = map.getGemsList();

	}

	@Override
	public void update() {

		for (Block b : map.getBlockList()) {
			b.update();
		}

	}


	@Override
	public void loadMap(String path) {
		this.level = path;

	}

	@Override
	public BlockMap getMap() {
		return map;
	}

	@Override
	public boolean checkPlayerCollision(Character p, double x, double y) {
		boolean collide = false;

		for (Block b : map.getBlockList()) {
			if (b.collision(x, y, p.getHeight(), p.getWidth())) {
				b.setPlayerState(p);
				collide = true;
			}
		}
		return collide;
	}

	@Override
	public Point2D getCheckPoint() {
		return null;
	}

	@Override
	public Block[][] getMatrix() {
		return map.getBlockMatrix();
	}

	@Override
	public List<Item> getGems() {
		return gems;
	}

	@Override
	public boolean checkBlockCollision(Block b, double x, double y, int w) {
		boolean sameType = false;
		for (Block block : map.getBlockList()) {
			if (b.getClass() == block.getClass() && b != block) {
				sameType = true;
			}
			if (b != block && x >= block.getX() && x <= block.getX() + block.getWIDTH() && y == block.getY()) {
				if (sameType && block instanceof MoveableBlock) {
					((MoveableBlock) block).swap();
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean checkVisibility(Character e, double enemyX, double enemyY, double playerX, double playerY) {

		int direction = 1;

		if (enemyX > playerX) {
			direction = -1;
		}

		if (direction > 0) {

			while (enemyX + e.getWidth() < playerX) {

				if (checkPlayerCollision(e, enemyX, enemyY)) {
					return false;
				}

				enemyX += direction;
			}

		} else {
			while (enemyX > playerX) {

				if (checkPlayerCollision(e, enemyX, enemyY)) {
					return false;
				}

				enemyX += direction;

			}

		}

		return true;
	}

	@Override
	public boolean checkBulletBlockCollision(Bullet b, double x, double y, int h, int w) {
		for (Block block : map.getBlockList()) {
			if (block.collision(x, y, h, w)) {
				return true;
			}
		}
		for (Character character : enemies) {
			if (b.collide(character.getX(), character.getY(), character.getWidth(), character.getHeight())) {
				character.kill();
				return true;
			}
		}
		if (b.collide(player.getX(), player.getY(), player.getWidth(), player.getHeight())) {
			player.kill();
			return true;
		}
		return false;
	}

}
