package gui.panel.singlePlayer;


import core.gameManagers.PlayManager;
import game.GameSelector;
import gui.event.KeyboardPressedEvent;
import gui.event.KeyboardReleasedEvent;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.ParallelCamera;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class ProvaPaneMenu extends Application {

	public ProvaPaneMenu() {
	}

	@Override
	public void start(Stage stage) throws Exception {
//		SelectPlayer b = new SelectPlayer();
//		PerspectiveCamera camera = new PerspectiveCamera();
//		camera.setScaleX(0.5);
//		camera.setScaleY(0.5);
		PlayManager p = PlayManager.getInstance();
		GameSelector gs = new GameSelector();
		gs.setPlayerName("c");
		gs.setPlayerType("Vincenzo");
		gs.setMapName("resources/Levels/customLevel/testSuperJ");
		p.init(gs);
		Pane root = new Pane();
		Rectangle backgr = new Rectangle(1920, 1080);
//		backgr.setFill(Color.BLACK);
		Scene scene = new Scene(root,Screen.getPrimary().getBounds().getWidth(),Screen.getPrimary().getBounds().getHeight());
		SinglePlayerPane s = new SinglePlayerPane(scene);
		stage.setScene(scene);
		scene.setOnKeyPressed(new KeyboardPressedEvent(s));
		scene.setOnKeyReleased(new KeyboardReleasedEvent(s));
//		scene.setCamera(camera);
		s.drawWorld();
		root.getChildren().addAll(s);
		Rectangle bkgr = new Rectangle(Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
		bkgr.setFill(new ImagePattern(new Image("file:resources/images/backgrounds/bkgrImg.png")));
		GameField subscene = new GameField(s,Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
		root.getChildren().addAll(bkgr,subscene);
		stage.setFullScreen(true);
		new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				s.update();
				subscene.update();
			}
		}.start();
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
