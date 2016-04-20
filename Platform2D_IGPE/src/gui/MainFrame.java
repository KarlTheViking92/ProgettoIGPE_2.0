package gui;

import core.gameManagers.GameLoop;
import core.gameManagers.GameManager;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainFrame extends Application {

	GameManager manager;

	@Override
	public void init() throws Exception {
		// TODO Auto-generated method stub
		super.init();
	
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(new Pane(), 800, 600);
		
		manager = new GameManager(scene);
		primaryStage.setScene(scene);
		primaryStage.show();
		manager.beginLoop();
		
	}

	public static void main(String[] args) {
		launch(args);
		
	}

}
