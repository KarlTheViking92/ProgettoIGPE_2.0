package gui;

import gui.event.KeyboardPressedEvent;
import gui.event.KeyboardReleasedEvent;
import gui.panel.singlePlayer.SinglePlayerPane;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainFrame extends Application {

	private Rectangle2D screen = Screen.getPrimary().getBounds();
	
	static ImageProvider imgs = ImageProvider.getInstance();
	
	
	SinglePlayerPane singlePlayer;
	
	PerspectiveCamera camera = new PerspectiveCamera(false);
	
	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		super.init();
	
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
	
//		Pane root = new Pane();
		
		Pane p = new Pane();

//		p.setBackground(new Background(new BackgroundImage(new Image("file:resources/images/ciccio.jpg"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT)));
//		p.getChildren().add(s);
		
		Scene scene = new Scene(p, screen.getWidth(), screen.getHeight());
		
		scene.setCamera(camera);
		
		singlePlayer = new SinglePlayerPane(scene);
		
		System.out.println("x del pannello " + (screen.getWidth()/2 - singlePlayer.getWidth()/2));
		System.out.println("y del pannello " + (screen.getHeight()/2 - singlePlayer.getHeight()/2));
		p.setLayoutX(screen.getWidth()/2 - singlePlayer.getWidth()/2);
		p.setLayoutY(screen.getHeight()/2 - singlePlayer.getHeight()/2);
//		System.out.println(scene.getCamera());
		p.getChildren().addAll(singlePlayer);
//		Scene scene = new Scene(p, 1000, 700);
		primaryStage.setTitle("Platform2D");
		primaryStage.setScene(scene);
//		primaryStage.setFullScreen(true);
		scene.setOnKeyPressed(new KeyboardPressedEvent(singlePlayer));
		scene.setOnKeyReleased(new KeyboardReleasedEvent(singlePlayer));
		
		scene.setOnMouseClicked(e -> {
			
			System.out.println("clicco con x : " + e.getX() + " y invece : "+ e.getY());
			
		});
//		root.getChildren().addAll(singlePlayer.getBackgroundImage(), p);
		singlePlayer.drawWorld();
//		PerspectiveCamera camera = new PerspectiveCamera();
		


		
		
		
		new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				singlePlayer.update();
			}
		}.start();
		
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
		
	}

}
