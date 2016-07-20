package gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

//se qualcosa non funge chiedere a Paola
public class GameProperties {

	private HashMap<Integer, String> blockTypes = new HashMap<>();
	private List<String> imagePaths = new ArrayList<>();
	private List<String> cubePaths = new ArrayList<>();
	// private List<String> playerPaths = new ArrayList<>();
	// private List<String> enemyPaths = new ArrayList<>();
	// private List<String> itemPaths = new ArrayList<>();

	public GameProperties() {
		loadCubeTypes();
		loadImagePaths();
		loadCubes();
	}

	private void loadCubes() {
		for (String string : imagePaths) {
			File file = new File(string);
			File[] listFiles = file.listFiles();
			Arrays.sort(listFiles);
			for (int i = 0; i < listFiles.length; i++) {
//				System.out.println(string + "/" + listFiles[i].getName());
				cubePaths.add(string + "/" + listFiles[i].getName());

			}

		}
	}

/*	public static void main(String[] args) {
		GameProperties p = new GameProperties();
	}*/

	private void loadImagePaths() {
		Properties properties = null;
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(new File("resources/config/imagePaths.properties"));
			properties = new Properties();
			properties.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileInput.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Set<Object> keySet = properties.keySet();
		for (Object object : keySet) {
			String value = properties.getProperty((String) object);
			imagePaths.add(value);

		}
		java.util.Collections.sort(imagePaths);
	}

	private void loadCubeTypes() {
		Properties properties = null;
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(new File("resources/config/blockTypes.properties"));
			properties = new Properties();
			properties.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileInput.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Set<Object> keySet = properties.keySet();
		for (Object object : keySet) {
			String value = properties.getProperty((String) object);
			blockTypes.put(Integer.parseInt((String) object), value);
		}

		/*
		 * for (int i : blockTypes.keySet()) { System.out.println("codice " + i
		 * + " cubo " + blockTypes.get(i)); }
		 */
		// java.util.Collections.sort (imagePaths);
	}

	public List<String> getCubes() {
		return cubePaths;
	}

	public HashMap<Integer, String> getBlockTypes() {
		return blockTypes;
	}

}
