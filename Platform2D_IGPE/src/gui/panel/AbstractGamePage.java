package gui.panel;

import core.gameManagers.MenuManager;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class AbstractGamePage extends Pane implements GamePage{
	
	protected Rectangle2D screen = Screen.getPrimary().getBounds();
	protected Rectangle background;
	protected Text title = new Text();

	public AbstractGamePage() {
		background = new Rectangle(screen.getWidth(), screen.getHeight());
		this.setBackground("BasicPageMenu.png");
		this.setWidth(screen.getWidth());
		this.setHeight(screen.getHeight());
		this.title.setFont(FONT_BIG);
		this.title.setStrokeWidth(2);
		this.title.setStroke(Color.BLACK);
		this.title.setFill(Color.web("#DC8014"));
		this.title.setLayoutY(screen.getHeight()*0.2);
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
		background.setFill(new ImagePattern(new Image("file:resources/images/backgrounds/"+name)));
	}

}
