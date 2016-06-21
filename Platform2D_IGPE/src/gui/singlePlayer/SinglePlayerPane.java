package gui.singlePlayer;

import core.element.block.Block;
import core.gameManagers.PlayManager;
import gui.ImageProvider;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;

public class SinglePlayerPane extends Pane {

	// resolution variables
	private final double FULLHDRESOLUTION = 1000;  
	private final double HDRESOLUTION = 500;  
	private boolean resolution = false; // false for HD, true for FULLHD
	
	private PlayManager manager;

	private double width = 0;
	private double height = 0;

	private ImageView[][] matrix;

	private Group group = new Group();
	
//	private Rectangle player;
	private ImageView player;
	
	private Scene scene;
	
	private Rectangle background;
	
//	private Background background;
	

	public SinglePlayerPane(Scene s) {
		
		if(Screen.getPrimary().getBounds().getHeight() < 1080)
			resolution = true;
		
		System.out.println(Screen.getPrimary().getBounds().getHeight() );
		scene = s;
		this.getChildren().add(group);
		
		manager = PlayManager.getInstance();
		manager.init();
		width = manager.getLevelWidth();
		height = manager.getLevelHeight();
		this.background =  new Rectangle(Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
		this.background.setFill(new ImagePattern(new Image("file:resources/images/ciccio.jpg")));
		this.setWidth(width);
		this.setHeight(height);

//		this.setOnKeyPressed(new KeyboardPressedEvent(this));
//		this.setOnKeyReleased(new KeyboardReleasedEvent(this));
		

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
		
//		player = new Rectangle(manager.getPlayer().getX(), manager.getPlayer().getY(), manager.getPlayer().getWidth(), manager.getPlayer().getHeight() );
		
		player =  new ImageView();
		player.setImage(ImageProvider.getInstance().getImage(10));
		player.setFitWidth(manager.getPlayer().getWidth());
		player.setFitHeight(manager.getPlayer().getHeight());
		player.setLayoutX(manager.getPlayer().getX());
		player.setLayoutY(manager.getPlayer().getY());
		
	/*	player.layoutXProperty().addListener((obs, old, newValue) -> {
			
//			int offset = newValue.intValue();
			double minPanelX = 335.0;
			double levelWidth = manager.getLevelWidth();
			double maxPanelX = minPanelX + levelWidth;
			System.out.println("camera x" + scene.getCamera().getTranslateX());
			
//			if(manager.getPlayer().getX()+420 > 700 && scene.getCamera().getTranslateX()+700 < maxPanelX)
//				scene.getCamera().setTranslateX(scene.getCamera().getTranslateX()+ 1);
				
//			System.out.println("offset " + offset + " levelwidth " + levelWidth);
//			System.out.println("playerx " + playerX);
			
		});*/
		/*player.layoutYProperty().addListener((obs, old, newValue) -> {
			
			double offset = newValue.intValue();
			double screenHeight = Screen.getPrimary().getBounds().getHeight();
			double levelHeight = manager.getLevelHeight();
			
			System.out.println("offset " + offset + " levelHeigth " + levelHeight);
			if (offset > screenHeight && offset < levelHeight - screenHeight) {
				System.out.println("entro nell if strano");
			}
		});*/
		System.out.println("camera");
		System.out.println(scene.getCamera());
		if(!resolution) scene.getCamera().setTranslateZ(FULLHDRESOLUTION);
		else scene.getCamera().setTranslateZ(HDRESOLUTION);
		
//		System.out.println( scene.getCamera());
	
		this.group.getChildren().add(player);
		
//		for (int i = 0; i < group.getChildren().size(); i++) {
//			group.getChildren().get(i).toBack();
//		}
	}

	public void update() {
		manager.update();
		
//		else if(manager.getPlayer().getX()-420 < 0 && manager.getPlayer().getX()-850 < 0){
//			scene.getCamera().setTranslateX(scene.getCamera().getTranslateX()- 1);
//		}
		updateCamera();
		
		player.relocate(manager.getPlayer().getX(), manager.getPlayer().getY());
		
	}

	private void updateCamera() {
		
		if (manager.getPlayer().getX() < (manager.getLevelWidth() - 500) && manager.getPlayer().getX() > 300) {
			scene.getCamera().setTranslateX(manager.getPlayer().getX() - 500);
			}else{
				scene.getCamera().setTranslateX( ((manager.getPlayer().getX() < 300) ? 300 : (manager.getLevelWidth() - 500)) - 500 
						+ ((manager.getPlayer().getX() % 2 == 0) ? 0.1 : -0.1) );
				System.out.println("Aggiorno la X");		
				System.out.println(((manager.getPlayer().getX() < 300) ? 300 : (manager.getLevelWidth() - 500)) - 500 
						+ ((manager.getPlayer().getX() % 2 == 0) ? 0.1 : -0.1));
			}
			
			if ( manager.getPlayer().getY() < (manager.getLevelHeight() - 200) && manager.getPlayer().getY() > 200 ) {
				scene.getCamera().setTranslateY(manager.getPlayer().getY() - 500);
			}else{
				scene.getCamera().setTranslateY( ((manager.getPlayer().getY() < 200) ? 200 : (manager.getLevelHeight() - 200)) - 500 
						+ ((manager.getPlayer().getY() % 2 == 0) ? 0.1 : -0.1) );
				
				System.out.println("Aggiorno la Y");		
				System.out.println(((manager.getPlayer().getY() < 200) ? 200 : (manager.getLevelHeight() - 200)) - 500 
						+ ((manager.getPlayer().getY() % 2 == 0) ? 0.1 : -0.1));
			}
	}

}
