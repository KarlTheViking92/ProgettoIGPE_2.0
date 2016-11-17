package core.World;

import java.util.ArrayList;

import core.element.Position;
import core.element.character.Player;
import core.gameManagers.MultiplayerManager;

public class MultiplayerWorld extends AbstractWorld {

	public MultiplayerWorld(String level) {
		this.loadMap(level);
	}
	
	@Override
	public void initialize() {

		this.map = new BlockMap(level, this);
		this.map.loadMap();
		System.out.println("initalize multiplayer world " + this.map);
		this.width = (float) (map.getColumns() * map.getBlockSize());
		this.height = (float) (map.getRows() * map.getBlockSize());
		this.players = new ArrayList<>();
		this.enemies = map.enemies;
		// for (Player player : players) {
		//// player = new Player("giocatore1", new
		// Position(map.getSpawnPoint().getX(), map.getSpawnPoint().getY()), 10,
		// 1, this);
		// }
//		PlayManager.getInstance().getPlayer()
//				.setPosition(new Position(map.getSpawnPoint().getX(), map.getSpawnPoint().getY()));
		MultiplayerManager.getInstance().getPlayer().setPosition(new Position(map.getSpawnPoint().getX(), map.getSpawnPoint().getY()));
		for (Player p : MultiplayerManager.getInstance().getPlayers()){
			p.setPosition(new Position(map.getSpawnPoint().getX(), map.getSpawnPoint().getY()));
		}
		
		
		System.out.println("inizializzo il mondo");
		this.gems = map.getGemsList();
	}
	
	
}
