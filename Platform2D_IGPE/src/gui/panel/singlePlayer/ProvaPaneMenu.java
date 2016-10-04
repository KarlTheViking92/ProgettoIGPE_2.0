package gui.panel.singlePlayer;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ProvaPaneMenu extends Application {

	public ProvaPaneMenu() {
	}

	@Override
	public void start(Stage stage) throws Exception {
		SelectPlayer b = new SelectPlayer();
		Scene scene = new Scene(b,Screen.getPrimary().getBounds().getWidth(),Screen.getPrimary().getBounds().getHeight());
		stage.setScene(scene);
		stage.setFullScreen(true);
		new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				b.update();
			}
		}.start();
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
