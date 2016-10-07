package game;

import gui.panel.GamePage;

public class AbstractGameState implements GameState{
	
	protected SequencePage pages;
	protected GamePage current;
	
	public AbstractGameState(GamePage...gamePages) {
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
		current.update();
	}

	@Override
	public void reset() {
		pages.resetSequence();
		current = pages.getCurrentPage();
	}

}
