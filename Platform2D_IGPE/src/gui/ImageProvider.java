package gui;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.image.Image;

public class ImageProvider {

	GameProperties properties;
	HashMap<Integer, String> blockTypes;
	HashMap<String, Image> images = new HashMap<>();
	HashMap<Integer, String> codes = new HashMap<>();
	HashMap<String, Image> specialBlock = new HashMap<>();
	HashMap<String, Image> simpleBlock = new HashMap<>();
	HashMap<String, Image> menu = new HashMap<>();

	private static ImageProvider instance;

	private ImageProvider() {
		properties = new GameProperties();
		blockTypes = properties.getBlockTypes();
		loadImages();
	}

	public static ImageProvider getInstance() {
		if (instance == null) {
			instance = new ImageProvider();
		}

		return instance;
	}

	private void loadImages() {
		int i = 1;
		Matcher match;
		Pattern regex = Pattern.compile("(\\w+)..*");
		for (String image : properties.getCubes()) {
			System.out.println("Sto caricando string " + image);
			String[] token = image.split("/");
			if (token[3].equals("SpecialCube")) {
				match = regex.matcher(token[4]);
				if (match.find()) {
					specialBlock.put(match.group(1), new Image("file:" + image));
				}
			} else if (token[3].contains("Cube")) {
				match = regex.matcher(token[4]);
				if (match.find()) {
					simpleBlock.put(match.group(1), new Image("file:" + image));
				}
			} else if (token[2].contains("menu")) {
				match = regex.matcher(token[3]);
				if (match.find()) {
					menu.put(match.group(1), new Image("file:" + image));
				}
			}
			i++;
		}

	}

	public void stampa() {
		for (int key : codes.keySet()) {
			System.out.println("la chiave è: " + key + " il valore è : " + codes.get(key));
		}
	}

	public Image getImage(int code) {
		if (codes.containsKey(code)) {
			return images.get(codes.get(code));
		}
		return null;
	}

	public Image getSimpleBlock(String cube, String color) {
		return simpleBlock.get(cube + "_" + color);

	}

	public Image getSpecialBlock(String cube) {
		return specialBlock.get(cube);

	}
	public Image getMenuImage(String name) {
		return menu.get(name);

	}
	/*
	 * public static void main(String[] args) { GameProperties g = new
	 * GameProperties(); ImageProvider i = ImageProvider.getInstance(); }
	 */
}
