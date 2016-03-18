package gameMap;

import java.io.BufferedReader;
import java.io.FileReader;

import element.block.Block;

public class BlockMap {
	
	// player position?
	
	private float playerX;
	private float playerY;
	
	// map 
	private int rows;
	private int columns;
	private int [][] map;
	
	// blocks
	private Block [][] blocks;
	
	// constructor
	public BlockMap() {}
	
	// add different block in map
	public void addBlocks(){
		// TODO 
	}
	
	// load map from txt file
	public void loadMap(String s){
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(s));
			
			rows = Integer.parseInt(buffer.readLine());
			columns = Integer.parseInt(buffer.readLine());
			map = new int [rows][columns];
			
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
