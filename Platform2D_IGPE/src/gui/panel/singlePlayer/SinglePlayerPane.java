package gui.panel.singlePlayer;

import core.gameManagers.PlayManager;
import gui.event.Gamepad;
import gui.hud.Hud;
import gui.panel.UpdatablePane;
import gui.panel.finishPanel.FinishPane;
import gui.panel.finishPanel.MatchInfo;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class SinglePlayerPane extends Pane implements UpdatablePane {

	private PlayManager manager;

	private GameField subscene;
	private Hud hud;
	private PausePane pause = new PausePane(this);
	private FinishPane finishLevel = new FinishPane(this);

	private Gamepad controller = new Gamepad(null);
	private boolean gamepad = false;

	// --------------------------------

	public double[] cameraPosition = { 0.0, 0.0 };
	private double width = 0;
	private double height = 0;

	// private Rectangle player;

	private Scene scene;

	private Rectangle background;
	Rectangle r = new Rectangle(10, 10);
	// private Background background;

	private double SCALEFACTOR = 2;

	public SinglePlayerPane(Scene s) {
		scene = s;

		manager = PlayManager.getInstance();

		this.background = new Rectangle(Screen.getPrimary().getBounds().getWidth(),
				Screen.getPrimary().getBounds().getHeight());
		background.setFill(new ImagePattern(new Image("file:resources/images/backgrounds/bkgrImg.png")));
		this.setWidth(width);
		this.setHeight(height);
		subscene = new GameField(this, Screen.getPrimary().getBounds().getWidth(),
				Screen.getPrimary().getBounds().getHeight());
		hud = new Hud();
		this.getChildren().addAll(background, subscene, hud);
	}

	public void draw() {
		width = manager.getLevelWidth();
		height = manager.getLevelHeight();
		subscene.drawWorld();
		hud.init();
	}

	public Rectangle getBackgroundImage() {
		return background;
	}

	@Override
	public void update() {
		if (gamepad) {
			// aggiorno gli eventi?
			controller.update();
		}

		if (!manager.isPaused()) {
			manager.update();
			subscene.update();

		} else {
			if (!this.getChildren().contains(pause)) {
				hud.pause();
				this.getChildren().add(pause);
			}
		}
		hud.update();

		if (manager.isFinished()) {
			System.out.println("HAI FINITO!!!");
			if (!this.getChildren().contains(finishLevel)) {
				finishLevel.init(new MatchInfo(manager.getPlayer().getName(), hud.getLevelTime(),
						manager.getPlayer().getCollectedGems()));
				this.getChildren().add(finishLevel);
			}
		}

	}

	public void removePanel(Node n) {
		if (this.getChildren().contains(n)) {
			this.getChildren().remove(n);
		}
	}

	public void restart() {
		subscene.clearSubscene();
		hud.reset();
	}

	public void resume() {
		hud.resume();
	}
}
