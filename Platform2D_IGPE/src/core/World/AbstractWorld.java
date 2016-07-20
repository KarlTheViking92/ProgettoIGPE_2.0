package core.World;

import java.util.ArrayList;
import java.util.List;
import core.element.Position;
import core.element.block.Block;
import core.element.character.Character;
import core.element.character.Player;
import javafx.geometry.Point2D;

public class AbstractWorld implements World {
	
	protected BlockMap map;
	
	private float width = 0;
	private float height = 0;
	
	protected Player player;
	
	private int levelKeys;
	
	private String level;

	public AbstractWorld() {
//		this.map = new BlockMap(map);
		
		// init mappa
//		initialize();

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
	public Player getPlayer() {
		return player;
	}

	@Override
	public List<Character> getEnemies() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void initialize() {
		
		this.map = new BlockMap(level);
		this.map.loadMap();
		
		this.width = (float) (map.getColumns() * map.getBlockSize()); 
		this.height = (float) (map.getRows() * map.getBlockSize());
		
		this.player = new Player(new Position(map.getSpawnPoint().getX(), map.getSpawnPoint().getY()), 10, 1, this);
		System.out.println("inizializzo il mondo");
		//spawn point non ancora definito
//		this.player = new Player(new Position((float)map.getSpawnPoint().getX(), (float)map.getSpawnPoint().getY()), 7, 1, 50, 50);

	}

	@Override
	public void update() {
		
		// TODO Auto-generated method stub
		
		// for tutti i blocchi chiama update
		
		
	}

	@Override
	public void reset() {
		// forse si può cacciare 
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
	public List<Block> getNearBlocks() {
		
		int x = (int) (player.getX()/50);
		int y = (int) (player.getY()/50);

		double xd =  (player.getX());
		double yd =  (player.getY());
		
//		System.out.println("player sta in x : " + xd + " y: " + yd);
		Block[][] matrix = getMatrix();
		List<Block> result = new ArrayList<>();
		
	/*	if ( matrix[y][x+1] != null )// destra
			result.add(matrix[y][x+1]);
		if ( matrix[y][x-1] != null ) // sinistra
			result.add(matrix[y][x-1]);
		if ( matrix[y-1][x] != null ) // sopra
			result.add(matrix[y-1][x]);
		if ( matrix[y+1][x] != null ){// sotto
			result.add(matrix[y+1][x]);
//			player.setGrounded(true);
			System.out.println(" y + 1 " + (y+1) + " x " + x);
		}
//		else
//			player.setGrounded(false);
		if ( matrix[y-1][x+1] != null ) // in alto a destra
			result.add(matrix[y-1][x+1]);
		if ( matrix[y-1][x-1] != null ) // in alto a sinistra
			result.add(matrix[y-1][x-1]);
		if ( matrix[y+1][x-1] != null ) // in basso a sinistra
			result.add(matrix[y+1][x-1]);
		if ( matrix[y+1][x+1] != null ) // in basso a destra
			result.add(matrix[y+1][x+1]);
			*/
		return map.getBlockList();
		
	}
	@Override
	public boolean checkPlayerCollision(double x , double y){
		
//		System.out.println("player x " + x + " player y " + y);
		
		for (Block b : map.getBlockList()) {
			if(b.collision(x, y, player.getHeight(), player.getWidth()))
				return true;
		}
		
		return false;
	}

	@Override
	public Point2D getCheckPoint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Block[][] getMatrix() {
		return map.getBlockMatrix();
	}
	
}
