package gui.panel.singlePlayer;


import java.util.ArrayList;
import java.util.List;

import core.element.Item;
import core.element.block.Block;
import core.gameManagers.PlayManager;
import gui.ImageProvider;
import gui.drawer.CharacterDrawer;
import gui.element.GraphicBlockFactory;
import gui.element.GraphicElement;
import gui.element.GraphicGem;
import gui.event.KeyboardPressedEvent;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

public class GameField extends SubScene {

	private static Pane root = new Pane();
	private Camera camera = new PerspectiveCamera();
	
	private PlayManager manager;
	private SinglePlayerPane gamePanel;
	private CharacterDrawer drawer;
	private List<GraphicElement> imgs = new ArrayList<>();
	private GraphicBlockFactory factory = new GraphicBlockFactory();
	private Group group = new Group();
	private Rectangle2D screen = Screen.getPrimary().getBounds();
	private Point2D widthLimit;
	private Point2D heightLimit;

	public GameField(SinglePlayerPane game, double w, double h) {
		super(root, w, h);
		manager = PlayManager.getInstance();
		gamePanel = game;
		this.camera.setScaleX(0.5);
		this.camera.setScaleY(0.5);
		drawer = new CharacterDrawer(manager.getPlayer());
		this.setCamera(camera);

		widthLimit = new Point2D(manager.getLevelWidth() * 0.24, manager.getLevelWidth() * 0.75);
		heightLimit = new Point2D(manager.getLevelHeight() * 0.15, manager.getLevelHeight() * 0.86);
		initCamera();
		root.setStyle("-fx-background: null; -fx-background-color: null; ");
		root.getChildren().add(group);
		
	}

	private void initCamera() {
		double x = manager.getPlayer().getX();
		double y = manager.getPlayer().getY();

		if ((x > widthLimit.getX() && x < widthLimit.getY()) && (y > heightLimit.getX() && y < heightLimit.getY())) {
			this.camera.setTranslateX(manager.getPlayer().getX() - (screen.getWidth() / 4));
			this.camera.setTranslateY(manager.getPlayer().getY() - (screen.getHeight() / 4));
		}

		if (x < widthLimit.getX()) {
			camera.setTranslateX(widthLimit.getX() - (screen.getWidth() / 4));
		} else if (x > widthLimit.getY()) {
			camera.setTranslateX(widthLimit.getY() - (screen.getWidth() / 4));
		}

		if (y < heightLimit.getX()) {
			camera.setTranslateY(heightLimit.getX() - (screen.getHeight() / 4));
		} else if (y > heightLimit.getY()) {
			camera.setTranslateY(heightLimit.getY() - (screen.getHeight() / 4));
		}
	}

	public void update() {
		updateCamera();
		updateBlocks();
		drawer.draw();
	}
	
	public void drawWorld() {

		for (int y = manager.getBlocksMatrix().length - 1; y >= 0; y--) {
			for (int x = 0; x < manager.getBlocksMatrix()[y].length; x++) {
				Block b = manager.getBlocksMatrix()[y][x];
				if (b != null) {
					
					GraphicElement img = factory.makeBlock(b);
					imgs.add(img);
					this.group.getChildren().add((ImageView)img);
				}
			}
		}
		for (Item gem : manager.getGemList()) {
			GraphicElement img = new GraphicGem(gem);
			this.imgs.add(img);
			this.group.getChildren().add((ImageView)img);
		}
//		List<Character> l = manager.getMeleeEnemy();
//		
//		MeleeEnemy e  = (MeleeEnemy)l.get(0);
//		System.out.println(e);
//		ciccioCattivo = new ImageView(ImageProvider.getInstance().getSpecialBlock("CloudBlock"));
//		ciccioCattivo.setLayoutX(e.getX());
//		ciccioCattivo.setLayoutY(e.getY());
		
//		this.group.getChildren().add(ciccioCattivo);
		this.group.getChildren().add(drawer);
				
	}
	
	private void updateBlocks(){
		for(GraphicElement block : imgs){
			block.update();
		}
	}
	
	private void updateCamera(){
		double x = manager.getPlayer().getX();
		double y = manager.getPlayer().getY();
		if (x > widthLimit.getX() && x < widthLimit.getY()) {
			this.camera.setTranslateX(manager.getPlayer().getX() - (screen.getWidth() / 4));
		}
		if (y > heightLimit.getX() && y < heightLimit.getY()) {
			this.camera.setTranslateY(manager.getPlayer().getY() - (screen.getHeight() / 4));
		}
	}

}
