package core.World;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import core.element.Position;
import core.element.block.Block;
import core.element.block.BlockFactory;
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
	private int[][] map;

	// block factory

	BlockFactory factory;

	// blocks
	// matrice o lista?
	private Block[][] blocks;
	private List<Block> blocklist = new ArrayList<>();

	// map gems

	private int gems = 0;

	// constructor
	public BlockMap(String path) {
		this.factory = new BlockFactory();
		this.path = path;
	}

	// add different block in map
	public void addBlocks() {
		// TODO

		for (int y = 0; y < rows; y++) {
			for (int x = 0; x < columns; x++) {

				if (map[y][x] == 0) {
					blocks[y][x] = null;
				}
				else if(map[y][x] == 1){ // player
					spawnPoint = new Point2D(x*BLOCKSIZE, y*BLOCKSIZE);
				}
				
				else if(map[y][x] == 3){ // conta una gemma
					gems++;
				}
				
				else if( map[y][x] == 2 ){ // spawn point
					
					
				} else {
					// forse la position è sbagliata
					blocks[y][x] = factory.makeBlock(map[y][x], new Position(x*BLOCKSIZE, y*BLOCKSIZE));
					if(!blocklist.contains(blocks[y][x]))
						blocklist.add(blocks[y][x]);
				}

			}
		}
		System.out.println("ho caricato " + blocklist.size() + " blocchi");
	}

	public int getGems() {
		return gems;
	}
	public List<Block> getBlockList(){ return blocklist; }

	// load map from txt file
	public void loadMap() {
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(path));

			rows = Integer.parseInt(buffer.readLine());
			columns = Integer.parseInt(buffer.readLine());
			map = new int[rows][columns];
			blocks = new Block[rows][columns];

			for (int i = 0; i < rows; i++) {

				String line = buffer.readLine();

				String[] tokens = line.split("\\s+");
				for (int j = 0; j < columns; j++) {

					map[i][j] = Integer.parseInt(tokens[j]);

				}
			}

			buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		this.addBlocks();
//		blockSize = blocks[0][0].getWIDTH();
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

}
