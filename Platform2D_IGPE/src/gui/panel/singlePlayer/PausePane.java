package gui.panel.singlePlayer;


import core.gameManagers.MenuManager;
import core.gameManagers.PlayManager;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class PausePane extends Pane {
	
	private Rectangle background;
	private final Font TITLE_FONT = Font.loadFont("file:resources/font/Engcomica.otf", 200);
	private Text title;
	
	private SinglePlayerPane game;
	
	CustomTextButton resume;
	CustomTextButton restart;
	CustomTextButton mainMenu;
	CustomTextButton exitGame;
	
	public PausePane(SinglePlayerPane g) {
		
		game = g;
		background = new Rectangle(Screen.getPrimary().getBounds().getWidth(), Screen.getPrimary().getBounds().getHeight()); 
		background.setFill(new ImagePattern(new Image("file:resources/images/backgrounds/pauseBackground.jpg")));
		background.setOpacity(0.8);
		
		title = new Text("pause");
		title.setFont(TITLE_FONT);
		title.setFill(Color.WHITE);
		title.setStrokeWidth(2);
		title.setStroke(Color.BLACK);
		title.setLayoutX(Screen.getPrimary().getBounds().getWidth()*0.5 - (title.getLayoutBounds().getWidth()/2));
		title.setLayoutY(Screen.getPrimary().getBounds().getHeight()*0.15);
	
		
		resume = new CustomTextButton("resume game");
		restart = new CustomTextButton("restart level");
		mainMenu = new CustomTextButton("go back to main menu");
		exitGame = new CustomTextButton("exit game");
		
		resume.setLayoutY(300);
		restart.setLayoutY(resume.getLayoutY() + restart.getHeight() + Screen.getPrimary().getBounds().getHeight()*0.05);
		mainMenu.setLayoutY(restart.getLayoutY() + mainMenu.getHeight() + Screen.getPrimary().getBounds().getHeight()*0.05);
		exitGame.setLayoutY(mainMenu.getLayoutY() + exitGame.getHeight() + Screen.getPrimary().getBounds().getHeight()*0.05);
		this.getChildren().addAll(background, title, resume, restart, mainMenu, exitGame);
		addEvents();
	}
	
	private void addEvents(){
		resume.setOnMouseReleased(e -> {
			game.removePanel(this);
			PlayManager.getInstance().resume();
			game.resume();
		});
		restart.setOnMouseReleased(e -> {
			game.removePanel(this);
//			PlayManager.getInstance().restartLevel();
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
