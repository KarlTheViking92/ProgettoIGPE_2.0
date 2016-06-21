package gui.gameMenu;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class GameMenu extends Parent {
	
	private Scene root;
	private static final Font FONT = Font.font("", FontWeight.BOLD, 25);
	private Rectangle2D screen = Screen.getPrimary().getBounds();
	private final MediaPlayer mediaPlayer; 

	public GameMenu(Scene primary) throws Exception{
		this.root = primary;
		File music = new File("resources/music/mm.mp3");
		final Media media = new Media(music.toURI().toString());
		mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setStartTime(Duration.millis(525));
		mediaPlayer.play();
		
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
		VBox menu0 = new VBox(20);
		VBox menu1 = new VBox(20);
		VBox menu2 = new VBox(20);

		menu0.setTranslateX(100);
		menu0.setTranslateY(200);

		menu1.setTranslateX(100);
		menu1.setTranslateY(200);

		menu2.setTranslateX(100);
		menu2.setTranslateY(200);

		final int offset = 400;
		menu1.setTranslateX(offset);

		MenuButton btnEditor = new MenuButton("EDITOR");
		btnEditor.setOnMouseClicked(event -> {

//			 GameMenuDemo.this.playSound("select");
			FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
			ft.setFromValue(1);
			ft.setToValue(0);
			ft.setOnFinished(evt -> this.setVisible(false));
			ft.play();
		});

		MenuButton btnNew = new MenuButton("NEW GAME");
		btnNew.setOnMouseClicked(event -> {
			getChildren().add(menu2);

			// devo fare una fiunzione che riproduce il select
			// GameMenuDemo.this.playSound("select");

			TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
			tt.setToX(menu0.getTranslateX() - offset);

			TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
			tt1.setToX(menu0.getTranslateX());

			tt.play();
			tt1.play();

			tt.setOnFinished(evt -> {
				getChildren().remove(menu0);
				
			});

		});

		MenuButton btnResume = new MenuButton("RESUME");
		btnResume.setOnMouseClicked(event -> {

			// GameMenuDemo.this.playSound("select");
			FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
			ft.setFromValue(1);
			ft.setToValue(0);
			ft.setOnFinished(evt -> this.setVisible(false));
			ft.play();
		});

		MenuButton btnSing = new MenuButton("SINGLE PLAYER");
		
		btnSing.setOnMouseClicked(event -> {
			FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
			ft.setFromValue(1);
			ft.setToValue(0);
			ft.setOnFinished(evt -> {
//				root.setScene(new Scene(new Pane() ,Screen.getPrimary().getBounds().getWidth() , Screen.getPrimary().getBounds().getHeight()));
				//root.setRoot(new Pane());
				
				MenuManager.getInstance().goToSinglePlayer();
			});
			ft.play();
			
		});
		
		btnResume.setOnMouseClicked(event -> {

			// GameMenuDemo.this.playSound("select");
			FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
			ft.setFromValue(1);
			ft.setToValue(0);
			ft.setOnFinished(evt -> this.setVisible(false));
			ft.play();
		});

		MenuButton btnMulti = new MenuButton("MULTI PLAYER");
		btnResume.setOnMouseClicked(event -> {

			// GameMenuDemo.this.playSound("select");
			FadeTransition ft = new FadeTransition(Duration.seconds(0.5), this);
			ft.setFromValue(1);
			ft.setToValue(0);
			ft.setOnFinished(evt -> this.setVisible(false));
			ft.play();
		});

		MenuButton btnOptions = new MenuButton("OPTIONS");
		btnOptions.setOnMouseClicked(event -> {
			// GameMenuDemo.this.playSound("select");
			getChildren().add(menu1);

			TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
			tt.setToX(menu0.getTranslateX() - offset);

			TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu1);
			tt1.setToX(menu0.getTranslateX());

			tt.play();
			tt1.play();

			tt.setOnFinished(evt -> {
				getChildren().remove(menu0);
			});

		});

		MenuButton btnExit = new MenuButton("EXIT");
		btnExit.setOnMouseClicked(event -> {
			// GameMenuDemo.this.playSound("select");
			System.exit(0);
		});

		MenuButton btnBack = new MenuButton("BACK");
		btnBack.setOnMouseClicked(event -> {
			// GameMenuDemo.this.playSound("select");

			getChildren().add(menu0);

			TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu1);
			tt.setToX(menu1.getTranslateX() + offset);

			TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
			tt1.setToX(menu1.getTranslateX());

			tt.play();
			tt1.play();

			tt.setOnFinished(evt -> {
				getChildren().remove(menu1);
			});

		});

		MenuButton btnBack2 = new MenuButton("BACK");
		btnBack2.setOnMouseClicked(event -> {
			// GameMenuDemo.this.playSound("select");
			getChildren().add(menu0);

			TranslateTransition tt = new TranslateTransition(Duration.seconds(0.25), menu0);
			tt.setToX(menu2.getTranslateX() + offset);

			TranslateTransition tt1 = new TranslateTransition(Duration.seconds(0.5), menu0);
			tt1.setToX(menu2.getTranslateX());

			tt.play();
			tt1.play();

			tt.setOnFinished(evt -> {
				getChildren().remove(menu2);
			});

		});

		MenuButton btnSound = new MenuButton("SOUND");
		MenuButton btnVideo = new MenuButton("VIDEO");

		menu0.getChildren().addAll(btnNew, btnOptions, btnEditor, btnExit);
		menu1.getChildren().addAll(btnBack, btnSound, btnVideo);
		menu2.getChildren().addAll(btnBack2, btnResume, btnSing, btnMulti);

		Rectangle bg = new Rectangle(Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight());
		bg.setFill(Color.GRAY);
		bg.setOpacity(0.2);

		getChildren().addAll(bg, menu0);
	}
	
	public void stopMusic(){ mediaPlayer.stop(); }

}