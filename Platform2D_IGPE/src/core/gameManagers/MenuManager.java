package core.gameManagers;

import game.GameState;
import game.SinglePlayerState;
import gui.GameMain;
import gui.event.KeyboardPressedEvent;
import gui.event.KeyboardReleasedEvent;
import gui.gameMenu.GameMenu;
import gui.panel.GamePage;
import gui.panel.UpdatablePane;
import gui.panel.singlePlayer.SelectMap;
import gui.panel.singlePlayer.SelectPlayer;
import gui.panel.singlePlayer.SinglePlayerPane;
import javafx.geometry.Rectangle2D;
import javafx.scene.Camera;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class MenuManager {
	private Rectangle2D screen = Screen.getPrimary().getBounds();
	private static MenuManager instance;
	private GameMain game;

	private UpdatablePane currentPane;
	// private Pane lastPane = new Pane();

	private GameState actualState;

//	private GameState menuState;
	private GameState singleGameState;
	private GameState mapEditorState;

	private GameMenu menu;
	private Pane root = new Pane();
	private SinglePlayerPane singlePlayer;
	private PlayManager matchManager = PlayManager.getInstance();
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
			System.out.println("inizializzo menu " + menu);
			currentPane = menu;
			root.getChildren().add(menu);
			System.out.println(menu.getChildren());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// game.getScene().setCamera(camera);

	}

	public void goToSinglePlayer() {
		System.out.println("go to singleplayer");
		menu.stopMusic();
		root.getChildren().clear();
		singleGameState = new SinglePlayerState(new SelectPlayer(), new SelectMap());
		actualState = singleGameState;
		System.out.println(singleGameState);
		root.getChildren().add((Node) actualState.getCurrentPage());
	}

	public void updateGame() {
		currentPane.update();
		if(actualState != null)
			actualState.update();
	}

	public Pane getRoot() {
		return root;
	}

	public void nextPage() {
		// currentPane.nextPage();
		root.getChildren().clear();
		root.getChildren().add((Node) actualState.getNextPage());
		System.out.println("pagina successiva");
	}

	public void previousPage() {
		// currentPane.prevoiusPage();
		GamePage prev = actualState.getPreviousPage();
		if (prev != null) {
			root.getChildren().clear();
			root.getChildren().add((Node) prev);
		}else{
			System.out.println("vai al menù principale");
			goToMainMenu();
//			System.out.println(root.getChildren());
		}
		System.out.println("pagina precedente");
	}

	private void goToMainMenu() {
		root.getChildren().clear();
		System.out.println(root.getChildren());
		actualState = null;
		System.out.println(menu);
		menu.startMusic();
		currentPane = menu;
		root.getChildren().add(menu);
		System.out.println(root.getChildren());
	}

	public void startGame() {
		Pane p = new Pane();
		singlePlayer = new SinglePlayerPane(game.getScene());
		// p.setLayoutX(screen.getWidth()/2 - singlePlayer.getWidth()/2);
		// p.setLayoutY(screen.getHeight()/2 - singlePlayer.getHeight()/2);
		// game.getScene().setCamera(camera);
		// crea il player ?
		matchManager.init();
		// matchManager.setCurrentPlayer(new Player("", life, damage, world));
		// camera.setNearClip(0);
		// camera.setFarClip(-10);
		// ((PerspectiveCamera) camera).setFieldOfView(35);
		game.getScene().setCamera(camera);
		singlePlayer.drawWorld();
		currentPane = singlePlayer;
		game.getScene().setOnKeyPressed(new KeyboardPressedEvent(singlePlayer));
		game.getScene().setOnKeyReleased(new KeyboardReleasedEvent(singlePlayer));
		p.getChildren().add(singlePlayer);
		root.getChildren().add(p);
	}
}
