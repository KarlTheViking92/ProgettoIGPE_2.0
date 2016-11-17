package gui.multiplayerPanel;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ProvaMultiplayer extends Application {

	public ProvaMultiplayer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void start(Stage stage) throws Exception {
		Pane root = new Pane();
		Scene scene = new Scene(root,Screen.getPrimary().getBounds().getWidth(),Screen.getPrimary().getBounds().getHeight());
		stage.setScene(scene);
		
		MultiplayerHomePane m = new MultiplayerHomePane();
		root.getChildren().add(m);
		Rectangle2D screen = Screen.getPrimary().getBounds();
//		HostServerPane h = new HostServerPane(screen.getWidth()*0.5, screen.getHeight()*0.5, screen.getWidth()*0.5 - ((screen.getWidth()*0.5)/2), screen.getHeight()*0.5-((screen.getHeight()*0.5)/2));
		stage.setFullScreen(true);
//		root.getChildren().add(h);
		new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				m.update();
			}
		}.start();
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
