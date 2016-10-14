package gui.panel;

import game.GameState;
import javafx.scene.text.Font;

public interface GamePage extends UpdatablePane{
	
	public static final Font FONT_BIG = Font.loadFont("file:resources/font/Engcomica.otf", 200);
	public static final Font FONT_SMALL = Font.loadFont("file:resources/font/Engcomica.otf", 55);
	
	public abstract void nextPage();
	public abstract void previousPage();
	
	public void setGameState(GameState state);
	
	public abstract void setBackground(String path);
}
