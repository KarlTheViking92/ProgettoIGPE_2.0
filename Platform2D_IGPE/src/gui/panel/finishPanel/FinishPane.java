package gui.panel.finishPanel;

import core.gameManagers.MenuManager;
import gui.panel.CustomComponent;
import gui.panel.singlePlayer.CustomTextButton;
import gui.panel.singlePlayer.SinglePlayerPane;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class FinishPane extends Pane {

	private Rectangle2D screen = Screen.getPrimary().getBounds();
	private Rectangle background;
	private final Font FONT = Font.loadFont("file:resources/font/fridaynight.ttf", screen.getWidth() * 0.12);
	private Text title = new Text("Level Clear !!!");
	private CustomComponent statistics;

	private SinglePlayerPane game;
	private ImageView clipboard_1;
	private ImageView clipboard_2;
	private CustomTextButton restart;
	private CustomTextButton mainMenu;
	private CustomTextButton levelSelector;
	private CustomTextButton exitGame;

	public FinishPane(SinglePlayerPane s) {
		game = s;
		background = new Rectangle(screen.getWidth(), screen.getHeight());
		background.setFill(new ImagePattern(new Image("file:resources/images/backgrounds/finishBackground.png")));
		statistics = new DataContainer(screen.getWidth() * 0.45, screen.getHeight() * 0.8,
				screen.getWidth() * 0.55 - (screen.getWidth() * 0.45 / 2), screen.getHeight() * 0.22);
		restart = new CustomTextButton("restart this level");
		levelSelector = new CustomTextButton("select another level");
		mainMenu = new CustomTextButton("go back to main menu");
		exitGame = new CustomTextButton("exit game");

		restart.setLayoutX(screen.getWidth() * 0.01);
		restart.setLayoutY(screen.getHeight() * 0.6);
		levelSelector.setLayoutX(screen.getWidth() * 0.01);
		levelSelector.setLayoutY(screen.getHeight() * 0.75);
		mainMenu.setLayoutX(screen.getWidth() * 0.68);
		mainMenu.setLayoutY(screen.getHeight() * 0.6);
		exitGame.setLayoutX(screen.getWidth() * 0.68);
		exitGame.setLayoutY(screen.getHeight() * 0.75);
		addEvents();
		clipboard_1 = new ImageView(new Image("file:resources/images/finishPanel/clipboard_1.png"));
		clipboard_2 = new ImageView(new Image("file:resources/images/finishPanel/clipboard_2.png"));

		this.getChildren().addAll(background, title, clipboard_1, clipboard_2, (Node) statistics, restart, mainMenu,
				levelSelector, exitGame);
	}

	public void init(MatchInfo i) {
		((DataContainer) statistics).loadInfo(i);
		title.setStroke(Color.web("#866b5b"));
		title.setStrokeWidth(5);
		title.setStyle("-fx-fill: radial-gradient(radius 100%, #f2fa5b, #fafcad, #f2c668, #c53d0d);");
		title.setFont(FONT);
		title.setLayoutX(screen.getWidth() * 0.5 - (title.getLayoutBounds().getWidth() / 2));
		title.setLayoutY(screen.getHeight() * 0.2);

		clipboard_1.setFitWidth(screen.getWidth() * 0.15);
		clipboard_1.setFitHeight(screen.getHeight() * 0.2);
		clipboard_1.setLayoutX(screen.getWidth() * 0.05);
		clipboard_1.setLayoutY(screen.getHeight() * 0.3);
		clipboard_2.setFitWidth(screen.getWidth() * 0.15);
		clipboard_2.setFitHeight(screen.getHeight() * 0.2);
		clipboard_2.setLayoutX(screen.getWidth() * 0.8);
		clipboard_2.setLayoutY(screen.getHeight() * 0.3);

	}

	private void addEvents() {
		levelSelector.setOnMouseReleased(e -> {
			// game.removePanel(this);
		});
		restart.setOnMouseReleased(e -> {
			game.removePanel(this);
			MenuManager.getInstance().startGame();
		});
		mainMenu.setOnMouseReleased(e -> {
			game.removePanel(this);
			MenuManager.getInstance().goToMainMenu();
		});
		exitGame.setOnMouseReleased(e -> {
			MenuManager.getInstance().exitGame();
		});
	}
}
