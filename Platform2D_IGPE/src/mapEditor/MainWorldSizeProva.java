package mapEditor;

import core.gameManagers.EditorManager;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainWorldSizeProva extends Application {
	private Scene scene;

	@Override
	public void start(Stage primaryStage) throws Exception {
		EditorManager.getInstance().initialize(this);
		scene = new Scene(EditorManager.getInstance().getRoot(), Screen.getPrimary().getBounds().getWidth(),
				Screen.getPrimary().getBounds().getHeight());
		scene.setFill(null);
		primaryStage.setFullScreen(true);
		primaryStage.setScene(getScene());
		primaryStage.setTitle("Editor");
		primaryStage.show();

		new AnimationTimer() {

			@Override
			public void handle(long now) {
				EditorManager.getInstance().updateGame();
			}
		}.start();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public Scene getScene() {
		return scene;
	}
}
