package gui.gameMenu;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameMenuMain extends Application {

	private static final Font FONT = Font.font("", FontWeight.BOLD, 25);

	private GameMenu gameMenu;
	private Rectangle2D screen = Screen.getPrimary().getBounds();

	@Override
	public void start(Stage primaryStage) throws Exception {

		File music = new File("resources/music/mm.mp3");
		final Media media = new Media(music.toURI().toString());
		final MediaPlayer mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setStartTime(Duration.millis(525));
		mediaPlayer.play();

		Pane root = new Pane();
		root.resize(screen.getWidth(), screen.getHeight());
		System.out.println("root " + root.getHeight() + "  " + root.getWidth());

		InputStream is = Files.newInputStream(Paths.get("resources/images/menu/Animated.gif"));
		Image img = new Image(is);

		is.close();

		ImageView imgView = new ImageView(img);
		imgView.setFitWidth(screen.getWidth());
		imgView.setFitHeight(screen.getHeight());

		Text about = new Text("PRESS ANY KEY TO START");

		about.setTranslateX((screen.getWidth() / 2) - (200));
		about.setTranslateY(screen.getHeight() / 2);
		about.setFill(Color.WHITE);
		about.setFont(FONT);
		about.setOpacity(0.0);

		for (int i = 0; i < 1; i++) {

			FadeTransition ft = new FadeTransition(Duration.seconds(0.8), about);

			ft.setDelay(Duration.millis(i * 100));
			ft.setToValue(1);
			ft.setAutoReverse(true);
			ft.setCycleCount(TranslateTransition.INDEFINITE);
			ft.play();

		}

		Scene scene = new Scene(root);
		gameMenu = new GameMenu(scene);
		gameMenu.setVisible(false);

		root.getChildren().addAll(imgView, gameMenu, about);

		scene.setOnKeyPressed(event -> {
			if (KeyEvent.KEY_TYPED != null) {

				about.setVisible(false);

				if (!gameMenu.isVisible()) {
					FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
					ft.setFromValue(0);
					ft.setToValue(1);

					gameMenu.setVisible(true);
					ft.play();

				} else {

					FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
					ft.setFromValue(1);
					ft.setToValue(0);
					ft.setOnFinished(evt -> gameMenu.setVisible(false));
					about.setVisible(true);

					ft.play();
				}
			}
		});

		System.out.println("scene " + scene.getHeight() + "  " + scene.getWidth());
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private void playSound(String path) {
		// final URL resource = getClass().getResource(path + ".mp3");
		// final Media media = new Media(resource.toString());
		// final MediaPlayer mediaPlayer = new MediaPlayer(media);
		// mediaPlayer.play();
	}

	// il parent può contenere i children

	public static void main(String[] args) {
		launch(args);
	}

}
