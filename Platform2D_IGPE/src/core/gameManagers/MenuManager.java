package core.gameManagers;

import game.GameState;
import game.MapEditorState;
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
import javafx.scene.ParallelCamera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import mapEditor.MapEditor;
import mapEditor.WorldSizeSelector;

public class MenuManager {
	private Rectangle2D screen = Screen.getPrimary().getBounds();
	private static MenuManager instance;
	private GameMain game;

	private UpdatablePane currentPane;
	// private Pane lastPane = new Pane();

	private GameState actualState;

	// private GameState menuState;
	private GameState singleGameState;
	private GameState mapEditorState;
	
	private MapEditor editor;
	private EditorManager editorManager = EditorManager.getInstance();

	private GameMenu menu;
	private Pane root = new Pane();
	private SinglePlayerPane singlePlayer;
	private PlayManager matchManager = PlayManager.getInstance();
	private Camera camera = new ParallelCamera();

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
			// System.out.println("inizializzo menu " + menu);
			currentPane = menu;
			root.getChildren().add(menu);
			// System.out.println(menu.getChildren());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// game.getScene().setCamera(camera);

	}

	public void updateGame() {
		currentPane.update();
		if (actualState != null)
			actualState.update();
	}

	public Pane getRoot() {
		return root;
	}

	public void nextPage() {
		// currentPane.nextPage();
		GamePage next = actualState.getNextPage();
		if (next != null) {
			root.getChildren().clear();
			root.getChildren().add((Node) next);
			// System.out.println("pagina successiva");
		} else {
			actualState.loadState();
		}
	}

	public void previousPage() {
		// currentPane.prevoiusPage();
		GamePage prev = actualState.getPreviousPage();
		if (prev != null) {
			root.getChildren().clear();
			root.getChildren().add((Node) prev);
		} else {
			// System.out.println("vai al menù principale");
			goToMainMenu();
			// System.out.println(root.getChildren());
		}
		// System.out.println("pagina precedente");
	}

	private void goToMainMenu() {
		root.getChildren().clear();
		actualState = null;
		menu.startMusic();
		currentPane = menu;
		root.getChildren().add(menu);
	}
	
	public void goToSinglePlayer() {
		System.out.println("go to singleplayer");
		menu.stopMusic();
		root.getChildren().clear();
		singleGameState = new SinglePlayerState(new SelectPlayer(), new SelectMap());
		singleGameState.initState();
		actualState = singleGameState;
		root.getChildren().add((Node) actualState.getCurrentPage());
	}

	public void startGame() {
		Pane p = new Pane();
		matchManager.init(actualState.getSelector());
		singlePlayer = new SinglePlayerPane(game.getScene());
		root.getChildren().clear();
		// p.setLayoutX(screen.getWidth()/2 - singlePlayer.getWidth()/2);
		// p.setLayoutY(screen.getHeight()/2 - singlePlayer.getHeight()/2);
		// game.getScene().setCamera(camera);
		// crea il player ?
		// matchManager.setCurrentPlayer(new Player("", life, damage, world));
		// camera.setNearClip(0);
		// camera.setFarClip(-10);
//		((PerspectiveCamera) camera).setFieldOfView(35);
		camera.setScaleX(0.5);
		camera.setScaleY(0.5);
		game.getScene().setCamera(camera);
		singlePlayer.drawWorld();
		currentPane = singlePlayer;
		game.getScene().setOnKeyPressed(new KeyboardPressedEvent(singlePlayer));
		game.getScene().setOnKeyReleased(new KeyboardReleasedEvent(singlePlayer));
		p.getChildren().add(singlePlayer);
		root.getChildren().add(p);
	}

	public void goToEditor() {
		System.out.println("go to editor");
		menu.stopMusic();
		root.getChildren().clear();
		WorldSizeSelector selector = new WorldSizeSelector();
		mapEditorState = new MapEditorState(selector);
		editor = new MapEditor();
		editorManager.initialize(selector, editor);
		actualState = mapEditorState;
		root.getChildren().add((Node) actualState.getCurrentPage());
	}

	public void startEditor() {
		editor.loadEditor(editorManager.getWorldHeight(), editorManager.getWorldWidth());
		currentPane = editor;
		root.getChildren().clear();
		root.getChildren().add(editor);
	}
}
