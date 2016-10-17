package game;

import core.gameManagers.MenuManager;
import gui.panel.GamePage;

public class MapEditorState extends AbstractGameState {

	public MapEditorState(GamePage... gamePages) {
		super(gamePages);
	}
	
	@Override
	public void loadState() {
		MenuManager.getInstance().startEditor();
	}
}
