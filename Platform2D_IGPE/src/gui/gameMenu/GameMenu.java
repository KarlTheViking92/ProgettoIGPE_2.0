package gui.gameMenu;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import core.gameManagers.MenuManager;
import gui.ImageProvider;
import gui.panel.UpdatablePane;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameMenu extends Pane implements UpdatablePane {

	private Scene root;
	private static final Font FONT = Font.loadFont("file:resources/font/customfont.ttf", 45);
	private Rectangle2D screen = Screen.getPrimary().getBounds();
	private final MediaPlayer mediaPlayer;
	// private Rectangle text = new Rectangle(800,100);
	private Text text = new Text("");
	private final double SPACING = 60.0; 
	
	public GameMenu(Scene primary) throws Exception {
		this.root = primary;
		File music = new File("resources/music/mainMenu.mp3");
		final Media media = new Media(music.toURI().toString());
		
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setStartTime(Duration.millis(525));
		// InputStream is =
		// Files.newInputStream(Paths.get("resources/images/menu/Animated.gif"));
		// Image img = new Image(is);
		//
		// is.close();

		ImageView imgView = new ImageView(ImageProvider.getInstance().getMenuImage("MainBackground"));
		imgView.setFitWidth(screen.getWidth());
		imgView.setFitHeight(screen.getHeight());

		Text about = new Text("PRESS ANY KEY TO START");

		about.setTranslateX((screen.getWidth() / 2) - (200));
		about.setTranslateY(screen.getHeight() / 2);
		about.setFill(Color.WHITE);
		about.setFont(FONT);
		about.setOpacity(0.0);
		this.getChildren().add(imgView);
		for (int i = 0; i < 1; i++) {

			FadeTransition ft = new FadeTransition(Duration.seconds(0.8), about);

			ft.setDelay(Duration.millis(i * 100));
			ft.setToValue(1);
			ft.setAutoReverse(true);
			ft.setCycleCount(TranslateTransition.INDEFINITE);
			ft.play();

		}

		this.root = primary;
		HBox menu0 = new HBox(30);
		HBox menu1 = new HBox(50);
//		VBox menu2 = new VBox(10);

		/*
		 * System.out.println(screen.getWidth() * 0.30);
		 * menu0.setTranslateX(screen.getWidth() * 0.30); //
		 * menu0.setTranslateY(screen.getHeight()*0.40);
		 * menu0.setTranslateY(screen.getHeight() * 0.45);
		 * 
		 * menu1.setTranslateX(screen.getWidth() * 0.35); //
		 * menu1.setTranslateY(screen.getHeight()*0.60);
		 * menu1.setTranslateY(screen.getHeight() * 0.65); System.out.println(
		 * "menu0 " + menu0.getTranslateX() + " menu1 " +
		 * menu1.getTranslateX());
		 */
//		final int offset = 400;
		// menu1.setTranslateX(offset);

		// MenuButton btnEditor = new MenuButton("EDITOR");

		menu0.setTranslateX(screen.getWidth() * 0.27);
		menu0.setTranslateY(screen.getHeight() * 0.35);
		menu0.setPrefWidth(screen.getWidth()*0.4);
		menu0.setPrefHeight(screen.getHeight()*0.1);
		menu0.getChildren().add(text);
		menu0.setAlignment(Pos.CENTER);
		text.setFill(Color.WHITE);
		text.setFont(FONT);
		text.setVisible(false);

		MyMenuButton btnSing = new MyMenuButton("singlePlayer", "Single Player", text);
		btnSing.setTranslateX(screen.getWidth() * 0.30);
		btnSing.setTranslateY(screen.getHeight() * 0.48);
		btnSing.setOnMouseClicked(event -> {
			// FadeTransition ft = new FadeTransition(Duration.seconds(0.5),
			// this);
			// ft.setFromValue(1);
			// ft.setToValue(0);
			// ft.setOnFinished(evt -> {
			// MenuManager.getInstance().goToSinglePlayer();
			// });
			// ft.play();
			MenuManager.getInstance().goToSinglePlayer();
		});

		/*
		 * btnResume.setOnMouseClicked(event -> {
		 * 
		 * // GameMenuDemo.this.playSound("select"); FadeTransition ft = new
		 * FadeTransition(Duration.seconds(0.5), this); ft.setFromValue(1);
		 * ft.setToValue(0); ft.setOnFinished(evt -> this.setVisible(false));
		 * ft.play(); });
		 */
		MyMenuButton btnMulti = new MyMenuButton("multiPlayer", "MultiPlayer", text);
		btnMulti.setTranslateX(btnSing.getTranslateX() + btnSing.getFitWidth() + SPACING);
		btnMulti.setTranslateY(btnSing.getTranslateY());
		/*
		 * btnResume.setOnMouseClicked(event -> {
		 * 
		 * // GameMenuDemo.this.playSound("select"); FadeTransition ft = new
		 * FadeTransition(Duration.seconds(0.5), this); ft.setFromValue(1);
		 * ft.setToValue(0); ft.setOnFinished(evt -> this.setVisible(false));
		 * ft.play(); });
		 */

		MyMenuButton btnEditor = new MyMenuButton("editor", "Editor", text);
		btnEditor.setTranslateX(btnMulti.getTranslateX() + btnMulti.getFitWidth() + SPACING);
		btnEditor.setTranslateY(btnMulti.getTranslateY());
		btnEditor.setOnMouseClicked(event -> {
			MenuManager.getInstance().goToEditor();
			// GameMenuDemo.this.playSound("select");
			// FadeTransition ft = new FadeTransition(Duration.seconds(0.5),
			// this);
			// ft.setFromValue(1);
			// ft.setToValue(0);
			//// ft.setOnFinished(evt -> this.setVisible(false));
			// ft.play();
		});

		MyMenuButton btnOptions = new MyMenuButton("options", "Options", text);
		btnOptions.setFitWidth(screen.getWidth()*0.08);
		btnOptions.setFitHeight(screen.getHeight()*0.08);
		btnOptions.setTranslateX(screen.getWidth() * 0.35);
		// menu1.setTranslateY(screen.getHeight()*0.60);
		btnOptions.setTranslateY(screen.getHeight() * 0.70);
		// MenuButton btnOptions = new MenuButton("OPTIONS");
		btnOptions.setOnMouseClicked(event -> {
			// GameMenuDemo.this.playSound("select");
			getChildren().add(menu1);

			// TranslateTransition tt = new
			// TranslateTransition(Duration.seconds(0.25), menu0);
			// tt.setToX(menu0.getTranslateX() - offset);
			//
			// TranslateTransition tt1 = new
			// TranslateTransition(Duration.seconds(0.5), menu1);
			// tt1.setToX(menu0.getTranslateX());
			//
			// tt.play();
			// tt1.play();
			//
			// tt.setOnFinished(evt -> {
			// getChildren().remove(menu0);
			// });

		});

		// MenuButton btnExit = new MenuButton("EXIT");
		MyMenuButton btnExit = new MyMenuButton("exit", "Exit Game", text);
		btnExit.setTranslateX(btnOptions.getTranslateX() + btnOptions.getFitWidth() + SPACING*2);
		btnExit.setTranslateY(btnOptions.getTranslateY());
		btnExit.setFitWidth(screen.getWidth()*0.08);
		btnExit.setFitHeight(screen.getHeight()*0.08);
		btnExit.setOnMouseClicked(event -> {
			// GameMenuDemo.this.playSound("select");
			System.exit(0);
		});

		Rectangle bg = new Rectangle(Screen.getPrimary().getBounds().getWidth(),
				Screen.getPrimary().getBounds().getHeight());
		bg.setFill(Color.GRAY);
		bg.setOpacity(0.2);

		// this.getChildren().addAll(bg, menu0 ,menu1);
		this.getChildren().addAll(bg, menu0, btnSing, btnMulti, btnEditor, btnOptions, btnExit);
		// music player main
		mediaPlayer.play();
	}

	public void stopMusic() {
		mediaPlayer.stop();
	}

	public void startMusic() {
		mediaPlayer.play();
	}

	public static void playSound(String media) {
		File music = new File("resources/music/" + media);
		final Media playable = new Media(music.toURI().toString());
		MediaPlayer player = new MediaPlayer(playable);
		player.play();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		this.setVisible(true);
	}

}