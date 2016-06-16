package gui.gameMenu;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameMenu extends Parent {
	
	private Stage root;
	
	public GameMenu(Stage primary) {
	
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

			// GameMenuDemo.this.playSound("select");
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
				root.setScene(new Scene(new Pane() ,Screen.getPrimary().getBounds().getWidth() , Screen.getPrimary().getBounds().getHeight()));
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

}