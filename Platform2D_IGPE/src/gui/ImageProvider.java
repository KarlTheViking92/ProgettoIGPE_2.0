package gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.image.Image;

public class ImageProvider {

	private GameProperties properties;
	private static ImageProvider instance;
	private HashMap<Integer, String> blockTypes;
	private HashMap<String, Integer> imageTypes;
	private HashMap<Integer, String> codes = new HashMap<>();
	private HashMap<String, Image> images = new HashMap<>();
	private HashMap<String, Image> worldElements = new HashMap<>();
	private HashMap<String, Image> editorElements = new HashMap<>();
	private HashMap<String, Image> specialBlock = new HashMap<>();
	private HashMap<String, Image> simpleBlock = new HashMap<>();
	private HashMap<String, Image> enemy = new HashMap<>();
	private HashMap<String, Image> items = new HashMap<>();
	private HashMap<String, Image> menu = new HashMap<>();
	private List<String> worldPaths = new ArrayList<>();
	private List<String> editorPaths = new ArrayList<>();
	private List<String> enemyPaths = new ArrayList<>();
	private List<String> coloredPaths = new ArrayList<>();
	private List<String> animatedPaths = new ArrayList<>();
	private List<String> itemPaths = new ArrayList<>();

	private ImageProvider() {
		properties = new GameProperties();
		blockTypes = properties.getBlockTypes();
		imageTypes = properties.getImageTypes();
		loadImages();
	}

	public static ImageProvider getInstance() {
		if (instance == null) {
			instance = new ImageProvider();
		}

		return instance;
	}

	private void loadImages() {
		Matcher match;
		Pattern regex = Pattern.compile("(\\w+)..*");
		for (String image : properties.getCubes()) {
			String[] token = image.split("/");
//			System.out.println("sto caricando " + image);
			if (token[3].equals("SpecialCube")) {
				match = regex.matcher(token[4]);
				if (match.find()) {
					animatedPaths.add(match.group(1));
					specialBlock.put(match.group(1), new Image("file:" + image));

				}
			} else if (token[3].contains("Cube")) {
				match = regex.matcher(token[4]);
				if (match.find()) {
					coloredPaths.add(match.group(1));
					simpleBlock.put(match.group(1), new Image("file:" + image));
				}
			} else if (token[2].contains("menu")) {
				match = regex.matcher(token[3]);
				if (match.find()) {
					menu.put(match.group(1), new Image("file:" + image));
				}
			} else if (token[2].contains("enemies")) {
				match = regex.matcher(token[3]);
				if (match.find()) {
					enemyPaths.add(match.group(1));
					enemy.put(match.group(1), new Image("file:" + image));
				}
			} else if (token[2].contains("item")) {
				match = regex.matcher(token[3]);
				if (match.find()) {
					itemPaths.add(match.group(1));
					items.put(match.group(1), new Image("file:" + image));
				}
			} else if (token[2].contains("editor")) {
				match = regex.matcher(token[3]);
				if (match.find()) {
					editorPaths.add(match.group(1));
					editorElements.put(match.group(1), new Image("file:" + image));
				}
			}
			else if (token[2].contains("character")) {
				match = regex.matcher(token[3]);
//				System.out.println(token[3]);
				if (match.find()) {
					images.put(match.group(1), new Image("file:" + image));
				}
			}
		}
	}

	public Image getImage(String code) {
	/*	if (codes.containsKey(code)) {
			return images.get(codes.get(code));
		}*/
		return images.get(code);
	}

	public Image getSimpleBlock(String cube, String color) {
		return simpleBlock.get(cube + "_" + color);

	}

	public Image getSimpleBlock1(String cube) {
		return simpleBlock.get(cube);

	}

	public Image getSpecialBlock(String cube) {
		return specialBlock.get(cube);
	}

	public Image getMenuImage(String name) {
		return menu.get(name);

	}

	public Image getEnemy(String enemy) {
		return this.enemy.get(enemy);
	}

	public Image getWorld(String world) {
		return this.worldElements.get(world);
	}

	public Image getEditorImage(String editor) {
		return this.editorElements.get(editor);
	}

	public Image getItems(String item) {
		return items.get(item);
	}

	public List<String> getEnemyPaths() {
		return enemyPaths;

	}

	public List<String> getColoredPaths() {
		return coloredPaths;
	}

	public List<String> getAnimatedPaths() {
		return animatedPaths;
	}

	public List<String> getItemPaths() {
		return itemPaths;
	}

	public List<String> getWorldPaths() {
		return worldPaths;
	}

	public Integer getImageTypes(String key) {
		return imageTypes.get(key);
	}

	public List<String> getEditorPaths() {
		return editorPaths;
	}

	public HashMap<Integer, String> getBlockTypes() {
		return blockTypes;
	}
}