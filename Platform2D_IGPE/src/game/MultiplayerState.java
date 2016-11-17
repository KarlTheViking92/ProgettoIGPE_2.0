package game;

import core.gameManagers.MenuManager;
import core.gameManagers.MultiplayerManager;
import gui.panel.GamePage;

public class MultiplayerState extends AbstractGameState {

	public MultiplayerState(GamePage... gamePages) {
		super(gamePages);
		
	}
	
	@Override
	public void loadState() {
		if(MultiplayerManager.getInstance().canStart()){
			MultiplayerManager.getInstance().loadMap();
//			MenuManager.getInstance().startMultiplayerGame();
		}
	}

}
