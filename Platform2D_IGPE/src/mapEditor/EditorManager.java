package mapEditor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
	private final String SAVEPATH = "resources/Levels/customLevel/";
	
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
	
	public void saveMap(String mapName) {
		try {
			String logicPath = SAVEPATH + mapName;
			String colorPath = logicPath + "_color";
			int [][] logicMatrix = map.getLogicMatrix();
			String [][] colorMatrix = map.getColorMatrix();
			FileWriter mapLogic = new FileWriter(logicPath);
			FileWriter mapColors = new FileWriter(colorPath);
			BufferedWriter outLogic = new BufferedWriter(mapLogic);
			BufferedWriter outColors = new BufferedWriter(mapColors);
			outLogic.write(Integer.toString(logicMatrix.length) + "\n");
			outLogic.write(Integer.toString(logicMatrix[0].length) + "\n");
			for (int i = 0; i < logicMatrix.length; i++) {
				for (int j = 0; j < logicMatrix[i].length; j++) {
					outLogic.write(Integer.toString(logicMatrix[i][j]) + " ");
					outColors.write(colorMatrix[i][j] + " ");
				}
				outLogic.write("\n");
				outColors.write("\n");
			}
			outLogic.close();
			outColors.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
