package mapEditor;

import gui.panel.UpdatablePane;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

public class EditorManager {

	private static EditorManager instance;
	private MainWorldSizeProva main;
	private Pane root = new Pane();
	private WorldSizeSelector worldSize;
	private Map map;
	private UpdatablePane currentPane;
	private Rectangle2D screen = Screen.getPrimary().getBounds();

	private EditorManager() {

	}

	public static EditorManager getInstance() {
		if (instance == null)
			instance = new EditorManager();
		return instance;
	}

	public void initialize(MainWorldSizeProva main) {
		this.main = main;
		worldSize = new WorldSizeSelector(main.getScene());
		currentPane = worldSize;
		root.getChildren().add(worldSize);
	}

	public void goToEditor(Map mapEditor) {
		root.getChildren().clear();
		root.setPrefHeight(screen.getHeight() / 2);
		root.setPrefWidth(screen.getWidth() / 2);
		map = mapEditor;
		currentPane = map;
		root.getChildren().add(mapEditor);
	}
	
	public void goToWorldSize(WorldSizeSelector world) {
		root.getChildren().clear();
		root.setPrefHeight(screen.getHeight() / 2);
		root.setPrefWidth(screen.getWidth() / 2);
		worldSize = world;
		currentPane = worldSize;
		root.getChildren().add(world);
	}

	public MainWorldSizeProva getMain() {
		return main;
	}

	public Pane getRoot() {
		return root;
	}

	public WorldSizeSelector getWorldSize() {
		return worldSize;
	}

	public void updateGame() {
		currentPane.update();
	}

	
}
