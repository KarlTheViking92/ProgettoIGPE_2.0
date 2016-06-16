package gui.singlePlayer;

import core.element.block.Block;
import core.gameManagers.PlayManager;
import gui.ImageProvider;
import gui.event.KeyboardPressedEvent;
import gui.event.KeyboardReleasedEvent;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class SinglePlayerPane extends Pane {

	private PlayManager manager;

	private double width = 0;
	private double height = 0;

	private ImageView[][] matrix;

	private Group group = new Group();
	
	private Rectangle player;
	
	private Scene scene;
	

	public SinglePlayerPane(Scene s) {
		scene = s;
		this.getChildren().add(group);

		this.setScaleY(1.5);
		this.setScaleX(1.5);
		manager = PlayManager.getInstance();
		manager.init();
		width = manager.getLevelWidth();
		height = manager.getLevelHeight();
		
		this.setWidth(width);
		this.setHeight(height);
		
//		this.setOnKeyPressed(new KeyboardPressedEvent(this));
//		this.setOnKeyReleased(new KeyboardReleasedEvent(this));
		

	}

	public void drawWorld() {

	
		System.out.println(this.getWidth());
		System.out.println(this.getHeight());

		for (int y = manager.getBlocksMatrix().length - 1; y >= 0; y--) {
			for (int x = 0; x < manager.getBlocksMatrix()[y].length; x++) {
				Block b = manager.getBlocksMatrix()[y][x];
				if (b != null) {
					ImageView img = new ImageView(ImageProvider.getInstance().getImage(15));
					img.setLayoutX(b.getX()-7.5);
					img.setLayoutY(b.getY()-7.5);
					img.setFitHeight(65);
					img.setFitWidth(65);
					
//					Rectangle r = new Rectangle(50,50);
//					r.setLayoutX(b.getX());
//					r.setLayoutY(b.getY());
//					r.setFill(Color.RED);
					this.group.getChildren().add(img);
				}
			}
		}
		
		player = new Rectangle(manager.getPlayer().getX(), manager.getPlayer().getY(), manager.getPlayer().getWidth(), manager.getPlayer().getHeight() );
		
		player.layoutXProperty().addListener((obs, old, newValue) -> {
			
			int offset = newValue.intValue();
			double playerX = manager.getPlayer().getX();
			double levelWidth = manager.getLevelWidth();
			
			System.out.println("offset " + offset + " levelwidth " + levelWidth);
			System.out.println("playerx " + playerX);
			
		});
		/*player.layoutYProperty().addListener((obs, old, newValue) -> {
			
			double offset = newValue.intValue();
			double screenHeight = Screen.getPrimary().getBounds().getHeight();
			double levelHeight = manager.getLevelHeight();
			
			System.out.println("offset " + offset + " levelHeigth " + levelHeight);
			if (offset > screenHeight && offset < levelHeight - screenHeight) {
				System.out.println("entro nell if strano");
			}
		});*/
		
		
		this.group.getChildren().add(player);
		
//		for (int i = 0; i < group.getChildren().size(); i++) {
//			group.getChildren().get(i).toBack();
//		}
	}

	public void update() {
		manager.update();
//		player.setTranslateX(manager.getPlayer().getX());
//		player.setTranslateY(manager.getPlayer().getY());
//		
		
//		System.out.println(" camera x " + scene.getCamera().getTranslateX());
//		System.out.println(" camera y " + scene.getCamera().getTranslateY());
		
		scene.getCamera().setTranslateX(manager.getPlayer().getX() - 300);
		scene.getCamera().setTranslateY(manager.getPlayer().getY() - 300);
		player.relocate(manager.getPlayer().getX(), manager.getPlayer().getY());
		

	}

}
