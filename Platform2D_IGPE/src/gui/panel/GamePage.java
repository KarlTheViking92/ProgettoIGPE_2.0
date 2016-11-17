package gui.panel;

import game.GameState;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public interface GamePage extends UpdatablePane {

	public static final Font FONT_BIG = Font.loadFont("file:resources/font/Engcomica.otf",
			Screen.getPrimary().getBounds().getWidth() * 0.1);
	public static final Font FONT_SMALL = Font.loadFont("file:resources/font/Engcomica.otf",
			Screen.getPrimary().getBounds().getWidth() * 0.03);

	public abstract void nextPage();

	public abstract void previousPage();

	public void setGameState(GameState state);

	public abstract void setBackground(String path);
}
