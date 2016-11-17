package core.World;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import core.element.Gem;
import core.element.Item;
import core.element.Position;
import core.element.block.Block;
import core.element.block.BlockFactory;
import core.element.block.StandardBlock;
import core.element.character.Character;
import core.element.character.MeleeEnemy;
import core.element.character.RangedEnemy;
import core.gameManagers.PlayManager;
import javafx.geometry.Point2D;

public class BlockMap {

	// block size

	private final double BLOCKSIZE = 50.0;

	// player position?

	public Point2D getSpawnPoint() {
		return spawnPoint;
	}

	private Point2D spawnPoint;

	// map path
	private String path;

	// map
	private int rows;
	private int columns;
	private int[][] logicalMap;
	private String[][] coloredMap;
	// block factory

	private BlockFactory factory;

	private Block[][] blocks;
	private List<Block> blocklist = new ArrayList<>();

	// map gems

	private int gems = 0;
	private List<Item> gemList = new ArrayList<>();
	public List<Character> enemies = new ArrayList<>();

	private World world;

	public BlockMap(String path, World w) {
		this.world = w;
		this.factory = new BlockFactory();
		this.path = path;
	}

	// add different block in map
	public void addBlocks() {

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {

				if (logicalMap[y][x] == 0) {
					blocks[y][x] = null;
				} else if (logicalMap[y][x] == 3) { // conta una gemma
					gemList.add(new Gem(x * BLOCKSIZE, y * BLOCKSIZE, 50, 50));
					gems++;
				} else if (logicalMap[y][x] == 16) {
					MeleeEnemy meleeEnemy = new MeleeEnemy("MeleeEnemy", 5, 5, world,
							new Position(x * BLOCKSIZE, y * BLOCKSIZE));
					meleeEnemy.setPosition(new Position(meleeEnemy.getX(),
							meleeEnemy.getY() - (meleeEnemy.getHeight() - BLOCKSIZE) - 5));
					enemies.add(meleeEnemy);
				} else if (logicalMap[y][x] == 17) {
					RangedEnemy rangedEnemy = new RangedEnemy("RangedEnemy", 5, 5, world,
							new Position(x * BLOCKSIZE, y * BLOCKSIZE));
					rangedEnemy.setPosition(new Position(rangedEnemy.getX(),
							rangedEnemy.getY() - (rangedEnemy.getHeight() - BLOCKSIZE) - 5));
					enemies.add(rangedEnemy);
				} else {
					blocks[y][x] = factory.makeBlock(world, logicalMap[y][x],
							new Position(x * BLOCKSIZE, y * BLOCKSIZE));
					if (blocks[y][x] instanceof StandardBlock) {
						blocks[y][x].setColor(coloredMap[y][x]);
					}
					if (!blocklist.contains(blocks[y][x])) {
						blocklist.add(blocks[y][x]);
					}
				}
				if (logicalMap[y][x] == 15) { // player
					spawnPoint = new Point2D(
							(x * BLOCKSIZE) + (BLOCKSIZE / 2) - PlayManager.getInstance().getPlayer().getWidth() / 2,
							y * BLOCKSIZE - BLOCKSIZE);
				}
			}
		}
	}

	public int getGems() {
		return gems;
	}

	public List<Block> getBlockList() {
		return blocklist;
	}

	// load map from txt file
	public void loadMap() {
		try {
			BufferedReader logicalPath = new BufferedReader(new FileReader(path));
			BufferedReader coloredPath = new BufferedReader(new FileReader(path + "_color"));
			rows = Integer.parseInt(logicalPath.readLine());
			columns = Integer.parseInt(logicalPath.readLine());
			logicalMap = new int[rows][columns];
			coloredMap = new String[rows][columns];
			blocks = new Block[rows][columns];

			for (int i = 0; i < rows; i++) {

				String logicalLine = logicalPath.readLine();
				String colorLine = coloredPath.readLine();
				String[] logicalTokens = logicalLine.split("\\s+");
				String[] coloredTokens = colorLine.split("\\s+");
				for (int j = 0; j < columns; j++) {
					logicalMap[i][j] = Integer.parseInt(logicalTokens[j]);
					coloredMap[i][j] = coloredTokens[j];
				}
			}

			logicalPath.close();
			coloredPath.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.addBlocks();
	}

	public double getBlockSize() {
		return BLOCKSIZE;
	}

	// getters
	public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public Block getBlock(int row, int column) {
		return null;
	}

	// set player position in map
	public void setPlayerPosition(float x, float y) {
	}

	public Block[][] getBlockMatrix() {
		return blocks;
	}

	public List<Item> getGemsList() {
		return gemList;
	}

}
