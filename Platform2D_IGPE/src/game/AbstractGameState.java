package game;

import gui.panel.GamePage;

public class AbstractGameState implements GameState {

	protected GameSelector gameInfo = new GameSelector();
	protected SequencePage pages;
	protected GamePage current;

	public AbstractGameState(GamePage... gamePages) {
		pages = new SequencePage(gamePages);
		current = pages.getCurrentPage();

	}

	@Override
	public GamePage getCurrentPage() {
		return pages.getCurrentPage();
	}

	@Override
	public GamePage getNextPage() {
		current = pages.getNextPage();
		return current;
	}

	@Override
	public GamePage getPreviousPage() {
		current = pages.getPreviousPage();
		return current;
	}

	@Override
	public void update() {
		if (current != null)
			current.update();
	}

	@Override
	public void reset() {
		pages.resetSequence();
		current = pages.getCurrentPage();
	}

	@Override
	public void setPlayer(String name, String type) {
		gameInfo.setPlayerName(name);
		gameInfo.setPlayerType(type);
	}

	@Override
	public void setMapName(String name) {
		gameInfo.setMapName(name);
	}

	@Override
	public void loadState() {
	}

	@Override
	public void initState() {
		for (GamePage page : pages) {
			page.setGameState(this);
		}
	}

	@Override
	public GameSelector getSelector() {
		return gameInfo;
	}

}
