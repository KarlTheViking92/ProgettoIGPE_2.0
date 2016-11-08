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
import core.element.block.SpawnBlock;
import core.element.block.StandardBlock;
import core.element.character.Character;
import core.element.character.MeleeEnemy;
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

	private float playerX;
	private float playerY;

	// map path
	private String path;

	// map
	private int rows;
	private int columns;
	private int[][] logicalMap;
	private String[][] coloredMap;
	// block factory

	BlockFactory factory;

	// blocks
	// matrice o lista?
	private Block[][] blocks;
	private List<Block> blocklist = new ArrayList<>();

	// map gems

	private int gems = 0;
	// private List<Point2D> gemPosition = new ArrayList<>();
	private List<Item> gemList = new ArrayList<>();
	public List<Character> enemies = new ArrayList<>();

	private World world;

	// constructor
	public BlockMap(String path, World w) {
		this.world = w;
		this.factory = new BlockFactory();
		this.path = path;
	}

	// add different block in map
	public void addBlocks() {
		// TODO

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {

				if (logicalMap[y][x] == 0) {
					blocks[y][x] = null;
				} else if (logicalMap[y][x] == 3) { // conta una gemma
					// gemPosition.add(new Point2D(x, y));
					gemList.add(new Gem(x * BLOCKSIZE, y * BLOCKSIZE, 50, 50));
					gems++;
				}else if(logicalMap[y][x] == 16){
					System.out.println("c'è un ciccioriolo");
					System.out.println(x * BLOCKSIZE+" "+ y * BLOCKSIZE);
					MeleeEnemy enemy = new MeleeEnemy("MeleeEnemy", 5, 5, world, new Position(x * BLOCKSIZE, y * BLOCKSIZE));
					enemies.add(enemy);
				} else {
					// forse la position Ã¨ sbagliata
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

//				if (logicalMap[y][x] == 16) { // melee enemey
//					meleeEnemyList
//							.add(new MeleeEnemy("Ginetto", 1, 1, world, new Position(x * BLOCKSIZE, y * BLOCKSIZE)));
//				}

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
		// blockSize = blocks[0][0].getWIDTH();
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
