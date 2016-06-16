package gui;

import java.util.HashMap;

import javafx.scene.image.Image;
import sun.security.jca.GetInstance.Instance;

public class ImageProvider{
	
	GameProperties properties;
	
	HashMap<String, Image> images = new HashMap<>();
	HashMap<Integer, String> codes = new HashMap<>();
	
	private static ImageProvider instance;
	
	private ImageProvider() {
		properties = new GameProperties();
		loadImages();
	}
	
	public static ImageProvider getInstance(){
		if(instance == null){
			instance = new ImageProvider();
		}
		
		return instance;
	}
	
	private void loadImages(){
		int i = 1;
		for(String image : properties.getCubes()){
			String[] token = image.split("/");
			codes.put(i, token[4]);
			images.put(token[4], new Image("file:"+image));
			i++;
		}
		
//		stampa();
	}
	
	public void stampa(){
		for (int key : codes.keySet()){
			System.out.println("la chiave è: " + key + " il valore è : " + codes.get(key) );
		}
	}
	
	public Image getImage(int code){
		if(codes.containsKey(code)){
			return images.get(codes.get(code));
		}
		return null;
	}
	
}
