package gui.panel.singlePlayer;


import core.gameManagers.PlayManager;
import gui.event.KeyboardPressedEvent;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

public class GameField extends SubScene {

	private static Pane root = new Pane();
	private Camera camera = new PerspectiveCamera();
	private PlayManager manager;
	private SinglePlayerPane gamePanel;
	private Rectangle2D screen = Screen.getPrimary().getBounds();
	private Point2D widthLimit;
	private Point2D heightLimit;

	public GameField(SinglePlayerPane game, double w, double h) {
		super(root, w, h);
		manager = PlayManager.getInstance();
		gamePanel = game;
		this.camera.setScaleX(0.5);
		this.camera.setScaleY(0.5);

		this.setCamera(camera);
		// this.setOnKeyPressed(new KeyboardPressedEvent(gamePanel));
		// this.setOnKeyReleased(new KeyboardPressedEvent(gamePanel));

		widthLimit = new Point2D(manager.getLevelWidth() * 0.24, manager.getLevelWidth() * 0.75);
		heightLimit = new Point2D(manager.getLevelHeight() * 0.15, manager.getLevelHeight() * 0.86);
		initCamera();
		root.getChildren().add(gamePanel);
	}

	private void initCamera() {
		double x = manager.getPlayer().getX();
		double y = manager.getPlayer().getY();

		if ((x > widthLimit.getX() && x < widthLimit.getY()) && (y > heightLimit.getX() && y < heightLimit.getY())) {
			this.camera.setTranslateX(manager.getPlayer().getX() - (screen.getWidth() / 4));
			this.camera.setTranslateY(manager.getPlayer().getY() - (screen.getHeight() / 4));
		}

		if (x < widthLimit.getX()) {
			camera.setTranslateX(widthLimit.getX() - (screen.getWidth() / 4));
		} else if (x > widthLimit.getY()) {
			camera.setTranslateX(widthLimit.getY() - (screen.getWidth() / 4));
		}

		if (y < heightLimit.getX()) {
			camera.setTranslateY(heightLimit.getX() - (screen.getHeight() / 4));
		} else if (y > heightLimit.getY()) {
			camera.setTranslateY(heightLimit.getY() - (screen.getHeight() / 4));
		}
	}

	public void update() {
		double x = manager.getPlayer().getX();
		double y = manager.getPlayer().getY();
		if (x > widthLimit.getX() && x < widthLimit.getY()) {
			this.camera.setTranslateX(manager.getPlayer().getX() - (screen.getWidth() / 4));
		}else{
//			System.out.println("se non la muovi la x sta in ");
//			System.out.println(camera.getTranslateX());
//			System.out.println(widthLimit.getY());
//			System.out.println("sei così "+ (widthLimit.getY() - (screen.getWidth() / 4)));
		}
		if (y > heightLimit.getX() && y < heightLimit.getY()) {
			this.camera.setTranslateY(manager.getPlayer().getY() - (screen.getHeight() / 4));
		}else{
//			System.out.println("se non la muovi la y sta in ");
//			System.out.println(camera.getTranslateY());
//			System.out.println(heightLimit.getY());
//			System.out.println("sei così "+ (heightLimit.getY() - (screen.getHeight() / 4)));
		}
	}

}
