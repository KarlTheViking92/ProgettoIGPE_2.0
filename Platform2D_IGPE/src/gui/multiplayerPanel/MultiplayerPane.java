package gui.multiplayerPanel;

import core.gameManagers.MultiplayerManager;
import gui.hud.Hud;
import gui.panel.GamePane;
import gui.panel.UpdatablePane;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class MultiplayerPane extends Pane implements GamePane{

	private MultiplayerManager manager = MultiplayerManager.getInstance();
	private GameFieldMultiplayer subscene;
	private Hud hud;
//	private PausePane pause = new PausePane(this);
//	private FinishPane finishLevel = new FinishPane(this);

//	private Gamepad controller = new Gamepad(this);
	private boolean gamepad = false;
	
	// nemico provvisorio
//	private ImageView ciccioCattivo;

	// --------------------------------

	public double[] cameraPosition = { 0.0, 0.0 };
	private double width = 0;
	private double height = 0;

	private Scene scene;

	private Rectangle background;
	Rectangle r = new Rectangle(10, 10);
	// private Background background;

	private double SCALEFACTOR = 2;

	public MultiplayerPane(Scene s) {

		// if (Screen.getPrimary().getBounds().getHeight() < 1080)
		// resolution = true;

		scene = s;
		// this.getChildren().add(group);

		manager = MultiplayerManager.getInstance();
		// manager.init();

		this.background = new Rectangle(Screen.getPrimary().getBounds().getWidth(),
				Screen.getPrimary().getBounds().getHeight());
		background.setFill(new ImagePattern(new Image("file:resources/images/backgrounds/bkgrImg.png")));
		// this.background.setFill(new ImagePattern(new
		// Image("file:resources/images/bkgrImg.png")));
		this.setWidth(width);
		this.setHeight(height);
		subscene = new GameFieldMultiplayer(this, Screen.getPrimary().getBounds().getWidth(),
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
	/*	if(gamepad){
			// aggiorno gli eventi?
			controller.update();
		}*/
		
		if (!manager.isPaused()) {
			manager.update();
			subscene.update();

		} else {
			/*if (!this.getChildren().contains(pause)) {
				hud.pause();
				this.getChildren().add(pause);
			}*/
		}
		hud.update();

		if (manager.isFinished()) {
			System.out.println("HAI FINITO!!!");
		/*	if (!this.getChildren().contains(finishLevel)) {
				finishLevel.init(new MatchInfo(manager.getPlayer().getName(), hud.getLevelTime(),
						manager.getPlayer().getCollectedGems()));
				this.getChildren().add(finishLevel);
			}*/
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
