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
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class SinglePlayerPane extends Pane implements UpdatablePane {

	// resolution variables
	private final double FULLHDRESOLUTION = 1000;
	private final double HDRESOLUTION = 500;
	private boolean resolution = false; // false for HD, true for FULLHD
	private Point2D cameraDistance;
	private Point2D mapLimit;
	private CharacterDrawer drawer;
	private PlayManager manager;

	private double width = 0;
	private double height = 0;

	private List<GraphicElement> imgs = new ArrayList<>();
	private GraphicBlockFactory factory = new GraphicBlockFactory();
	private Group group = new Group();

	// private Rectangle player;
	private ImageView player;

	private Scene scene;

	private Rectangle background;

	// private Background background;

	public SinglePlayerPane(Scene s) {

		if (Screen.getPrimary().getBounds().getHeight() < 1080)
			resolution = true;

		System.out.println(Screen.getPrimary().getBounds().getHeight());
		scene = s;
		this.getChildren().add(group);

		manager = PlayManager.getInstance();
		manager.init();
		width = manager.getLevelWidth();
		height = manager.getLevelHeight();
		this.background = new Rectangle(Screen.getPrimary().getBounds().getWidth(),
				Screen.getPrimary().getBounds().getHeight());
		this.background.setFill(new ImagePattern(new Image("file:resources/images/bkgrImg.png")));
		this.setWidth(width);
		this.setHeight(height);

		cameraDistance = new Point2D(500.0, 500.0);
		mapLimit = new Point2D(400.0, 200.0);
		// this.setOnKeyPressed(new KeyboardPressedEvent(this));
		// this.setOnKeyReleased(new KeyboardReleasedEvent(this));

	}

	public Rectangle getBackgroundImage() {
		return background;
	}

	public void drawWorld() {

		System.out.println(this.getWidth());
		System.out.println(this.getHeight());

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
		System.out.println(group.getChildren().size());
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
		System.out.println(group.getChildren().size());
		// player = new Rectangle(manager.getPlayer().getX(),
		// manager.getPlayer().getY(), manager.getPlayer().getWidth(),
		// manager.getPlayer().getHeight() );

		player = new ImageView();
		System.out.println("plaier " + manager.getPlayer());
		drawer = new CharacterDrawer(manager.getPlayer());
//		player.setImage(ImageProvider.getInstance().getImage(23));
		player.setImage(ImageProvider.getInstance().getSimpleBlock("Cube3", "yellow"));
		player.setFitWidth(manager.getPlayer().getWidth());
		player.setFitHeight(manager.getPlayer().getHeight());
		player.setLayoutX(manager.getPlayer().getX());
		player.setLayoutY(manager.getPlayer().getY());

		System.out.println("camera");
		System.out.println(scene.getCamera());
		if (!resolution)
			scene.getCamera().setTranslateZ(FULLHDRESOLUTION);
		else
			scene.getCamera().setTranslateZ(HDRESOLUTION);

		// System.out.println( scene.getCamera());

//		this.group.getChildren().add(player);
		this.group.getChildren().add(drawer);

		// for (int i = 0; i < group.getChildren().size(); i++) {
		// group.getChildren().get(i).toBack();
		// }
	}
	
	@Override
	public void update() {
		manager.update();
		updateCamera();
		updateBlocks();
//		player.relocate(manager.getPlayer().getX(), manager.getPlayer().getY());
		drawer.draw();

	}

	private void updateCamera() {
		if (manager.getPlayer().getX() < (manager.getLevelWidth() - mapLimit.getX())
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
		}
	}

	private void updateBlocks(){
		for(GraphicElement block : imgs){
			block.update();
		}
	}
}
