package gui.panel;

import core.gameManagers.MenuManager;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class AbstractGamePage extends Pane implements GamePage{
	
	protected Rectangle2D screen = Screen.getPrimary().getBounds();
	protected Rectangle background;

	public AbstractGamePage() {
		background = new Rectangle(screen.getWidth(), screen.getHeight());
		this.setWidth(screen.getWidth());
		this.setHeight(screen.getHeight());
		this.getChildren().add(background);
	}

	@Override
	public void nextPage() {
		MenuManager.getInstance().nextPage();		
	}

	@Override
	public void previousPage() {
		MenuManager.getInstance().previousPage();
	}

	@Override
	public void update() {
		
	}

	@Override
	public void setBackground(String name) {
		background.setFill(new ImagePattern(new Image("file:resources/images/"+name)));
	}

}
