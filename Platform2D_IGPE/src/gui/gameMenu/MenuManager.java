package gui.gameMenu;

import gui.GameMain;
import gui.singlePlayer.SinglePlayerPane;
import javafx.geometry.Rectangle2D;
import javafx.scene.Camera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

public class MenuManager {
	private Rectangle2D screen = Screen.getPrimary().getBounds();
	private static MenuManager instance;
	private GameMain game;

	private GameMenu menu;
	private boolean update = false;
	private Pane root = new Pane();
	private SinglePlayerPane singlePlayer;
	private Camera camera = new PerspectiveCamera();

	private MenuManager() {
	}

	public static MenuManager getInstance() {
		if (instance == null) {
			instance = new MenuManager();
		}
		return instance;
	}

	public void initialize(GameMain game) {
		this.game = game;
		try {
			this.menu = new GameMenu(game.getScene());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// game.getScene().setCamera(camera);

		root.getChildren().add(menu);
	}

	public void goToSinglePlayer() {
		menu.stopMusic();
		root.getChildren().clear();
		Pane p = new Pane();
		singlePlayer = new SinglePlayerPane(game.getScene());
		p.setLayoutX(screen.getWidth()/2 - singlePlayer.getWidth()/2);
		p.setLayoutY(screen.getHeight()/2 - singlePlayer.getHeight()/2);
		// game.getScene().setCamera(camera);
		game.getScene().setCamera(camera);
		singlePlayer.drawWorld();
		update = true;
		p.getChildren().add(singlePlayer);
		root.getChildren().add(p);
	}

	public void updateGame() {
		if (update)
			singlePlayer.update();
	}

	public Pane getRoot() {
		return root;
	}
}
