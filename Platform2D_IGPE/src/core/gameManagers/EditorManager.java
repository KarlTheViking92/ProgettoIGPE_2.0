package core.gameManagers;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import mapEditor.MapEditor;
import mapEditor.WorldSizeSelector;

public class EditorManager {

	private static EditorManager instance;
	private WorldSizeSelector worldSize;
	private MapEditor editor;
	private final String SAVEPATH = "resources/Levels/customLevel/";

	private EditorManager() {

	}

	public static EditorManager getInstance() {
		if (instance == null)
			instance = new EditorManager();
		return instance;
	}

	public void initialize(WorldSizeSelector worldSize, MapEditor editor) {
		this.worldSize = worldSize;
		this.editor = editor;
		System.out.println("inizializzo " + this );
	}


	public int getWorldWidth(){
		return worldSize.getSelectedWidth();
	}
	
	public int getWorldHeight(){
		return worldSize.getSelectedHeight();
	}
	
	public void saveMap(String mapName) {
		try {
			System.out.println("savemap " + this);
			String logicPath = SAVEPATH + mapName;
			String colorPath = logicPath + "_color";
			System.out.println("editor è : " + editor);
			System.out.println(editor.hashCode());
			int[][] logicMatrix = editor.getLogicMatrix();
			String[][] colorMatrix = editor.getColorMatrix();
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
