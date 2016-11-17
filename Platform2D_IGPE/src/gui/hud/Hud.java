package gui.hud;

import core.element.character.Character;
import core.gameManagers.PlayManager;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

public class Hud extends Pane {

	private Rectangle2D screen = Screen.getPrimary().getBounds();

	private ImageView exitMessage = new ImageView(new Image("file:resources/images/hud/exitOpen.png"));
	private Clock clock;
	private GemIndicator gems;
	private Character player;
	private PlayManager manager = PlayManager.getInstance();
	private int gemCollected;
	private int keys = 3;

	public Hud() {
		this.setWidth(screen.getWidth());
		this.setHeight(screen.getHeight());
		this.setStyle("-fx-background: null; -fx-background-color: null; ");
	}

	public void init() {
		clock = new Clock();
		clock.setLayoutX(5);
		clock.setLayoutY(5);
		exitMessage.setFitWidth(screen.getWidth() * 0.3);
		exitMessage.setFitHeight(screen.getHeight() * 0.09);
		exitMessage.setLayoutX(screen.getWidth() * 0.5 - (exitMessage.getFitWidth() / 2));
		exitMessage.setLayoutY(screen.getHeight() * 0.05);
		exitMessage.setVisible(false);
		gems = new GemIndicator();
		gemCollected = 0;
		gems.setLayoutX(screen.getWidth() * 0.8);
		gems.setLayoutY(screen.getHeight() * 0.03);
		player = manager.getPlayer();
		this.getChildren().addAll(clock, gems, exitMessage);
	}

	public void update() {
		if (gemCollected < player.getCollectedGems()) {
			gemCollected++;
			gems.collect();
		}
		if (gemCollected == keys) {
			exitMessage.setVisible(true);
		}
		clock.update();
	}

	public String getLevelTime() {
		return clock.getTime();
	}

	public void reset() {
		this.getChildren().clear();
	}

	public void resume() {
		clock.resume();
	}

	public void pause() {
		clock.pause();
	}
}
