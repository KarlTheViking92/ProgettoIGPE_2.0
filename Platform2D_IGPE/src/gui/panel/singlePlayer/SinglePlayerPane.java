package gui.panel.singlePlayer;


import java.util.ArrayList;
import java.util.List;

import core.element.Item;
import core.element.block.Block;
import core.element.block.CloudBlock;
import core.element.block.GhostBlock;
import core.element.block.WaterBlock;
import core.gameManagers.PlayManager;
import gui.ImageProvider;
import gui.drawer.CharacterDrawer;
import gui.element.GraphicBlockFactory;
import gui.element.GraphicElement;
import gui.element.GraphicGem;
import gui.element.StandardBlockGraphic;
import gui.panel.UpdatablePane;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class SinglePlayerPane extends Pane implements UpdatablePane {

	// resolution variables
	private final double FULLHDRESOLUTION = 1000;
	private final double HDRESOLUTION = 500;
	private boolean resolution = false; // false for HD, true for FULLHD
	private Point2D cameraDistance;
	private Point2D widthLimit;
	private Point2D heightLimit;
	private CharacterDrawer drawer;
	private PlayManager manager;

	public double[] cameraPosition = {0.0,0.0};
	private double width = 0;
	private double height = 0;

	private List<GraphicElement> imgs = new ArrayList<>();
	private GraphicBlockFactory factory = new GraphicBlockFactory();
	private Group group = new Group();

	// private Rectangle player;
	private ImageView player;

	private Scene scene;

	private Rectangle background;
	Rectangle r = new Rectangle(10, 10);
	// private Background background;
	
	private double SCALEFACTOR = 2;
	
	public SinglePlayerPane(Scene s) {

		if (Screen.getPrimary().getBounds().getHeight() < 1080)
			resolution = true;

		scene = s;
		this.getChildren().add(group);

		manager = PlayManager.getInstance();
//		manager.init();
		width = manager.getLevelWidth();
		height = manager.getLevelHeight();
		this.background = new Rectangle(Screen.getPrimary().getBounds().getWidth(),
				Screen.getPrimary().getBounds().getHeight());
		this.background.setFill(new ImagePattern(new Image("file:resources/images/bkgrImg.png")));
		this.setWidth(width);
		this.setHeight(height);
		
		cameraDistance = new Point2D(700.0, 700.0);
		widthLimit = new Point2D(width*0.2, width*0.8); // con 100.00,50.00 funziona in questo caso almeno
		heightLimit = new Point2D(height*0.2, height*0.8);
		// this.setOnKeyPressed(new KeyboardPressedEvent(this));
		// this.setOnKeyReleased(new KeyboardReleasedEvent(this));

	}

	public Rectangle getBackgroundImage() {
		return background;
	}

	public void drawWorld() {


		for (int y = manager.getBlocksMatrix().length - 1; y >= 0; y--) {
			for (int x = 0; x < manager.getBlocksMatrix()[y].length; x++) {
				Block b = manager.getBlocksMatrix()[y][x];
				if (b != null) {
					
					GraphicElement img = factory.makeBlock(b);
					imgs.add(img);
					// Rectangle r = new Rectangle(50,50);
					// r.setLayoutX(b.getX());
					// r.setLayoutY(b.getY());
					// r.setFill(Color.RED);
					this.group.getChildren().add((ImageView)img);
				}
			}
		}
		for (Item gem : manager.getGemList()) {
//			ImageView img = new ImageView();
//			img.setImage(ImageProvider.getInstance().getImage("gem"));
//			img.setLayoutX(gem.getX()*50);
//			img.setLayoutY(gem.getY()*50);
//			img.setFitHeight(gem.getHeight());
//			img.setFitWidth(gem.getWidth());
//			System.out.println("creo una gemma " + ImageProvider.getInstance().getImage("gem"));
//			System.out.println("posizione "+img.getLayoutX() + "   " + img.getLayoutY());
			GraphicElement img = new GraphicGem(gem);
			this.imgs.add(img);
			this.group.getChildren().add((ImageView)img);
		}
		// player = new Rectangle(manager.getPlayer().getX(),
		// manager.getPlayer().getY(), manager.getPlayer().getWidth(),
		// manager.getPlayer().getHeight() );

		player = new ImageView();
		drawer = new CharacterDrawer(manager.getPlayer());
//		player.setImage(ImageProvider.getInstance().getImage(23));
		player.setImage(ImageProvider.getInstance().getSimpleBlock("Cube3", "yellow"));
		player.setFitWidth(manager.getPlayer().getWidth());
		player.setFitHeight(manager.getPlayer().getHeight());
		player.setLayoutX(manager.getPlayer().getX());
		player.setLayoutY(manager.getPlayer().getY());

//		System.out.println("camera");
//		System.out.println(scene.getCamera());
/*		if (!resolution)
			scene.getCamera().setTranslateZ(FULLHDRESOLUTION);
		else
			scene.getCamera().setTranslateZ(HDRESOLUTION); */
		// System.out.println( scene.getCamera());
//		scene.getCamera().setTranslateZ(HDRESOLUTION/2);
		
//		this.group.getChildren().add(player);
		this.group.getChildren().add(drawer);
//		scene.getCamera().setTranslateX(manager.getPlayer().getX() - (1920/4));
//		scene.getCamera().setTranslateY(manager.getPlayer().getY() - (1080/4));
		// for (int i = 0; i < group.getChildren().size(); i++) {
		// group.getChildren().get(i).toBack();
		// }
//		this.group.setScaleX(group.getScaleX()*SCALEFACTOR);
//		this.group.setScaleY(group.getScaleY()*SCALEFACTOR);
		
	}
	
	@Override
	public void update() {
		manager.update();
//		updateCamera();
		updateBlocks();
//		player.relocate(manager.getPlayer().getX(), manager.getPlayer().getY());
//		System.out.println("player in " + manager.getPlayer().getX() + "  " + manager.getPlayer().getY());
		drawer.draw();

	}

	private void updateCamera() {
/*		if (manager.getPlayer().getX() < (manager.getLevelWidth() - mapLimit.getX())
				&& manager.getPlayer().getX() > 450) {
			scene.getCamera().setTranslateX(manager.getPlayer().getX() - cameraDistance.getX());
		} else {
			scene.getCamera().setTranslateX(
					((manager.getPlayer().getX() < 450) ? 450 : (manager.getLevelWidth() - mapLimit.getX()))
							- cameraDistance.getX() + ((manager.getPlayer().getX() % 2 == 0) ? 0.1 : -0.1));
		}

		if (manager.getPlayer().getY() < (manager.getLevelHeight() - mapLimit.getY())
				&& manager.getPlayer().getY() > 200) {
			scene.getCamera().setTranslateY(manager.getPlayer().getY() - cameraDistance.getY());
		} else {
			scene.getCamera().setTranslateY(
					((manager.getPlayer().getY() < 200) ? 200 : (manager.getLevelHeight() - mapLimit.getY()))
							- cameraDistance.getY() + ((manager.getPlayer().getY() % 2 == 0) ? 0.1 : -0.1));
		}*/
		double x = manager.getPlayer().getX();
		double y = manager.getPlayer().getY();
		
		/*if((x > widthLimit.getX() && x < widthLimit.getY()) ||(y > heightLimit.getX() && y < heightLimit.getY()) ){
			System.out.println("puoi aggiornare sulle x");
		}*/
		scene.getCamera().setTranslateX(manager.getPlayer().getX() - (1920/4));
		scene.getCamera().setTranslateY(manager.getPlayer().getY() - (1080/4));
	/*	
		if (y > heightLimit.getX() && y < heightLimit.getY()) {
			System.out.println("puoi aggiornare sulle y");
			scene.getCamera().setTranslateY(manager.getPlayer().getY() - (1080/4));
		}*/
//		r.setLayoutX(cameraPosition[0]);
//		r.setLayoutY(cameraPosition[1]);
		
//		scene.getCamera().setTranslateX(cameraPosition[0]);
//		scene.getCamera().setTranslateY(cameraPosition[1]);
//		System.out.println(scene.getCamera().getTranslateX() + "   "  + scene.getCamera().getTranslateY());
//		scene.getCamera().setTranslateZ();
//		System.out.println(((PerspectiveCamera)scene.getCamera()).getFieldOfView());
	}

	private void updateBlocks(){
		for(GraphicElement block : imgs){
			block.update();
		}
	}
}
