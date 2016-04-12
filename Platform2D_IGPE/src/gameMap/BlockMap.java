package gameMap;

import java.io.BufferedReader;
import java.io.FileReader;

import element.Position;
import element.block.Block;
import element.block.BlockFactory;

public class BlockMap {
	
	// player position?
	
	private float playerX;
	private float playerY;
	
	// map 
	private int rows;
	private int columns;
	private int [][] map;
	
	// block factory
	
	BlockFactory factory;
	
	// blocks
	private Block [][] blocks;
	
	// constructor
	public BlockMap() {
		this.factory = new BlockFactory();
	}
	
	// add different block in map
	public void addBlocks(){
		// TODO
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				
				if(map[i][j] == 0){
					blocks[i][j] = null;
				}else{
					// forse la position è sbagliata
					blocks[i][j] = factory.makeBlock(map[i][j], new Position(i, j));
				}
				
			}
		}
		
	}
	
	// load map from txt file
	public void loadMap(String s){
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(s));
			
			rows = Integer.parseInt(buffer.readLine());
			columns = Integer.parseInt(buffer.readLine());
			map = new int [rows][columns];
			blocks = new Block[rows][columns];
			
			for(int i = 0; i < rows; i++){
				
				String line = buffer.readLine();
				
				String [] tokens = line.split("\\s+");
				for(int j = 0; j < columns; j++ ){
					
					map[i][j] = Integer.parseInt(tokens[j]);
					
				}
			}
			
			buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	// getters
	public int getRows() { return rows; }
	public int getColumns() { return columns; }
	
	public Block getBlock(int row, int column) { return null; }
	
	// set player position in map
	public void setPlayerPosition(float x, float y) {}
}
