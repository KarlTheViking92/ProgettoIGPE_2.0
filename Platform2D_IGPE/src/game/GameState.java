package game;

import gui.panel.GamePage;

public interface GameState{
	
	public abstract GamePage getCurrentPage();
	public abstract GamePage getNextPage();
	public abstract GamePage getPreviousPage();
	
	public abstract void update();
	public abstract void reset();
}
