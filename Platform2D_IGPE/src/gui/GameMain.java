package gui;

import core.gameManagers.MenuManager;
import gui.gameMenu.GameMenu;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class GameMain extends Application {

	private Rectangle2D screen = Screen.getPrimary().getBounds();
	private Scene scene;

	@Override
	public void init() throws Exception {
		super.init();
		MenuManager.getInstance().initialize(this);
		scene = new Scene(MenuManager.getInstance().getRoot(), screen.getWidth(), screen.getHeight());
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		new AnimationTimer() {

			@Override
			public void handle(long now) {
				MenuManager.getInstance().updateGame();
			}
		}.start();

		primaryStage.setTitle("Platform2D");
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();
	}

	public Scene getScene() {
		return scene;
	}

	public static void main(String[] args) {
		launch(args);
	}

}
