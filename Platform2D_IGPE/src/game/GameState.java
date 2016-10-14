package game;

import gui.panel.GamePage;

public interface GameState{
	
	public abstract GamePage getCurrentPage();
	public abstract GamePage getNextPage();
	public abstract GamePage getPreviousPage();
	
	public abstract void setPlayer(String name, String type);
	public abstract void setMapName(String name);
	
	public abstract void initState();
	public abstract void loadState();
	
	public abstract void update();
	public abstract void reset();
	public abstract GameSelector getSelector();
}
