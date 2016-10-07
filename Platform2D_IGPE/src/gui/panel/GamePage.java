package gui.panel;

public interface GamePage extends UpdatablePane{
	
	public abstract void nextPage();
	public abstract void previousPage();
	
	public abstract void setBackground(String path);
}
