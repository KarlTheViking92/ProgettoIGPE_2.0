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

public class GameProperties {

	private HashMap<Integer, String> blockTypes = new HashMap<>();
	private HashMap<String, Integer> imageTypes = new HashMap<>();
	private List<String> imagePaths = new ArrayList<>();
	private List<String> cubePaths = new ArrayList<>();

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
				cubePaths.add(string + "/" + listFiles[i].getName());

			}

		}
	}

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
			fileInput = new FileInputStream(new File("resources/config/itemTypes.properties"));
			properties = new Properties();
			properties.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileInput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Set<Object> keySet = properties.keySet();
		for (Object object : keySet) {
			String value = properties.getProperty((String) object);
			blockTypes.put(Integer.parseInt((String) object), value);
			imageTypes.put(value, Integer.parseInt((String) object));
		}
	}

	public List<String> getCubes() {
		return cubePaths;
	}

	public HashMap<Integer, String> getBlockTypes() {
		return blockTypes;
	}

	public HashMap<String, Integer> getImageTypes() {
		return imageTypes;
	}
}
