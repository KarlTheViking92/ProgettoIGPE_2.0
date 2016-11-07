package gui.panel.singlePlayer;


import java.util.ArrayList;
import java.util.List;

import core.element.Item;
import core.element.block.Block;
import core.element.block.CloudBlock;
import core.element.block.GhostBlock;
import core.element.block.WaterBlock;
import core.element.character.Character;
import core.element.character.MeleeEnemy;
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
//	private final double FULLHDRESOLUTION = 1000;
//	private final double HDRESOLUTION = 500;
//	private boolean resolution = false; // false for HD, true for FULLHD
//	private Point2D cameraDistance;
//	private Point2D widthLimit;
//	private Point2D heightLimit;
	private CharacterDrawer drawer;
	private PlayManager manager;
	
	private GameField subscene;

	// nemico provvisorio
	private ImageView ciccioCattivo;
	
	// --------------------------------
	
	public double[] cameraPosition = {0.0,0.0};
	private double width = 0;
	private double height = 0;

	// private Rectangle player;
	private ImageView player;

	private Scene scene;

	private Rectangle background;
	Rectangle r = new Rectangle(10, 10);
	// private Background background;
	
	private double SCALEFACTOR = 2;
	
	public SinglePlayerPane(Scene s) {

//		if (Screen.getPrimary().getBounds().getHeight() < 1080)
//			resolution = true;

		scene = s;
//		this.getChildren().add(group);

		manager = PlayManager.getInstance();
//		manager.init();
		width = manager.getLevelWidth();
		height = manager.getLevelHeight();
		this.background = new Rectangle(Screen.getPrimary().getBounds().getWidth(),
				Screen.getPrimary().getBounds().getHeight());
		background.setFill(new ImagePattern(new Image("file:resources/images/backgrounds/bkgrImg.png")));
//		this.background.setFill(new ImagePattern(new Image("file:resources/images/bkgrImg.png")));
		this.setWidth(width);
		this.setHeight(height);
//		cameraDistance = new Point2D(700.0, 700.0);
//		widthLimit = new Point2D(width*0.2, width*0.8); // con 100.00,50.00 funziona in questo caso almeno
//		heightLimit = new Point2D(height*0.2, height*0.8);
		// this.setOnKeyPressed(new KeyboardPressedEvent(this));
		// this.setOnKeyReleased(new KeyboardReleasedEvent(this));
	}

	public void draw(){
		subscene = new GameField(this, Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
		subscene.drawWorld();
		this.getChildren().addAll(background, subscene);
	}
	
	public Rectangle getBackgroundImage() {
		return background;
	}

	
	@Override
	public void update() {
		manager.update();
		subscene.update();
//		player.relocate(manager.getPlayer().getX(), manager.getPlayer().getY());
//		System.out.println("player in " + manager.getPlayer().getX() + "  " + manager.getPlayer().getY());
		

	}

}
