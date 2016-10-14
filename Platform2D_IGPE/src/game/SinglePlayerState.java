package game;

import core.gameManagers.MenuManager;
import gui.panel.GamePage;

public class SinglePlayerState extends AbstractGameState {

	public SinglePlayerState(GamePage... gamePages) {
		super(gamePages);
	}
	
	@Override
	public void loadState() {
		MenuManager.getInstance().startGame();
	}

}
