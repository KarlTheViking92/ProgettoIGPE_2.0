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

	// resolution variables
	// private final double FULLHDRESOLUTION = 1000;
	// private final double HDRESOLUTION = 500;
	// private boolean resolution = false; // false for HD, true for FULLHD
	// private Point2D cameraDistance;
	// private Point2D widthLimit;
	// private Point2D heightLimit;
	private PlayManager manager;

	private GameField subscene;
	private Hud hud;
	private PausePane pause = new PausePane(this);
	private FinishPane finishLevel = new FinishPane(this);

	private Gamepad controller = new Gamepad(this);
	private boolean gamepad = true;
	
	// nemico provvisorio
	private ImageView ciccioCattivo;

	// --------------------------------

	public double[] cameraPosition = { 0.0, 0.0 };
	private double width = 0;
	private double height = 0;

	// private Rectangle player;
	private ImageView player;

	private Scene scene;

	private Rectangle background;
	Rectangle r = new Rectangle(10, 10);
	// private Background background;

	private double SCALEFACTOR = 2;

	public SinglePlayerPane(Scene s) {

		// if (Screen.getPrimary().getBounds().getHeight() < 1080)
		// resolution = true;

		scene = s;
		// this.getChildren().add(group);

		manager = PlayManager.getInstance();
		// manager.init();

		this.background = new Rectangle(Screen.getPrimary().getBounds().getWidth(),
				Screen.getPrimary().getBounds().getHeight());
		background.setFill(new ImagePattern(new Image("file:resources/images/backgrounds/bkgrImg.png")));
		// this.background.setFill(new ImagePattern(new
		// Image("file:resources/images/bkgrImg.png")));
		this.setWidth(width);
		this.setHeight(height);
		subscene = new GameField(this, Screen.getPrimary().getBounds().getWidth(),
				Screen.getPrimary().getBounds().getHeight());
		hud = new Hud();
		// cameraDistance = new Point2D(700.0, 700.0);
		// widthLimit = new Point2D(width*0.2, width*0.8); // con 100.00,50.00
		// funziona in questo caso almeno
		// heightLimit = new Point2D(height*0.2, height*0.8);
		// this.setOnKeyPressed(new KeyboardPressedEvent(this));
		// this.setOnKeyReleased(new KeyboardReleasedEvent(this));
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
		if(gamepad){
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
			System.out.println("remove pause pane" + n.getClass());
			this.getChildren().remove(n);
		}
	}

	public void restart() {
		// this.getChildren().remove(subscene);
		// subscene = null;
		subscene.clearSubscene();
		hud.reset();
		// subscene.drawWorld();
	}

	public void resume() {
		hud.resume();
	}
}
