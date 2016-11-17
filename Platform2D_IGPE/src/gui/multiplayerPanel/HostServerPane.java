package gui.multiplayerPanel;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import gui.panel.AbstractCustomComponent;
import gui.panel.singlePlayer.CustomListObject;
import gui.panel.singlePlayer.CustomListView;
import gui.panel.singlePlayer.CustomTextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class HostServerPane extends AbstractCustomComponent {
	private final String customLevelPath = "resources/Levels/customLevel";
	private Text title = new Text("Create your match");
	private CustomTextField creator, playerNumber;
	private ImageView clear, exit, confirm;
	private String chosenMap = "";
	private Map<String, String> levelMap = new HashMap<String, String>();
	private CustomListView maps;
	private MultiplayerHomePane panel;
	
	public HostServerPane(double width, double height, double x, double y, MultiplayerHomePane panel) {
		super(width, height, x, y);
		this.panel = panel;
		setComponentBackground("backgrounds/hostServer");
		title.setFont(TITLE_FONT);
		title.setStroke(Color.web("a29977"));
		title.setStrokeWidth(2);
		title.setFill(Color.web("b8631e"));
		title.setLayoutX(getComponentWidth()*0.5 - (title.getLayoutBounds().getWidth()/2));
		title.setLayoutY(getComponentHeight()*0.13);
		
		Text creator_title = new Text("Insert your name here");
		creator_title.setFont(INFO_FONT);
		creator_title.setFill(Color.web("ff9f38"));
		creator_title.setLayoutX(getComponentWidth()*0.08);
		creator_title.setLayoutY(getComponentHeight()*0.28);
		creator = new CustomTextField();
		creator.setTextColor("ff9f38");
		creator.setBackgroundColor(Color.web("a29977"), Color.web("b8631e"));
		creator.setLayoutX(getComponentWidth()*0.1);
		creator.setLayoutY(getComponentHeight()*0.3);
		Text playerNumber_title = new Text("Insert player number");
		playerNumber_title.setFont(INFO_FONT);
		playerNumber_title.setFill(Color.web("ff9f38"));
		playerNumber_title.setLayoutX(getComponentWidth()*0.58);
		playerNumber_title.setLayoutY(getComponentHeight()*0.28);
		playerNumber = new CustomTextField();
		playerNumber.setTextColor("ff9f38");
		playerNumber.setBackgroundColor(Color.web("a29977"), Color.web("b8631e"));
		playerNumber.setLayoutX(getComponentWidth()*0.6);
		playerNumber.setLayoutY(getComponentHeight()*0.3);
		maps = new CustomListView(getComponentWidth()*0.7, getComponentHeight()*0.4, getComponentWidth()*0.5 -((getComponentWidth()*0.7)/2), getComponentHeight()*0.5);
		this.addAll(title, creator_title, creator, playerNumber_title, playerNumber, maps);
		loadButtons();
		loadMaps();
	}
	
	
	private void loadButtons(){
		confirm = new ImageView(new Image("file:resources/images/multiplayer/confirm.png"));
		confirm.setFitWidth(getComponentWidth()*0.1);
		confirm.setFitHeight(getComponentWidth()*0.1);
		confirm.setLayoutX(getComponentWidth()*0.89);
		confirm.setLayoutY(getComponentHeight()*0.79);
		clear = new ImageView(new Image("file:resources/images/multiplayer/clear.png"));
		clear.setFitWidth(getComponentWidth()*0.1);
		clear.setFitHeight(getComponentWidth()*0.1);
		clear.setLayoutX(getComponentWidth()*0.02);
		clear.setLayoutY(getComponentHeight()*0.55);
		exit = new ImageView(new Image("file:resources/images/multiplayer/exit.png"));
		exit.setFitWidth(getComponentWidth()*0.1);
		exit.setFitHeight(getComponentWidth()*0.1);
		exit.setLayoutX(getComponentWidth()*0.02);
		exit.setLayoutY(getComponentHeight()*0.79);
		
		clear.setOnMouseReleased(e -> {
			this.resetHostPanel();
		});
		
		confirm.setOnMouseReleased(e -> {
			try {
				this.createGame();
				this.resetHostPanel();
				this.panel.getChildren().remove(this);
				this.panel.restoreEvents();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});
		
		exit.setOnMouseReleased(e -> {
			this.panel.getChildren().remove(this);
			this.panel.restoreEvents();
		});
		this.addAll(confirm,clear,exit);
	}
	
	public void loadMaps(){
		Pattern regex = Pattern.compile("_color$");
		try (Stream<Path> paths = Files.walk(Paths.get(customLevelPath))) {
			paths.forEach(filePath -> {
				Matcher match = regex.matcher(filePath.getFileName().toString());
				if (Files.isRegularFile(filePath) && !match.find()) {
					levelMap.put(filePath.getFileName().toString(), filePath.toString());
					maps.addItem(new CustomListObject(maps.getComponentWidth() * 0.8, maps.getComponentHeight() * 0.12, filePath.getFileName().toString(), maps));
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createGame() throws Exception{
		if(creator.getText().equals("")){
			throw new Exception("nome non inserito");
		}if(Integer.parseInt(playerNumber.getText()) < 2 && Integer.parseInt(playerNumber.getText()) > 4){
			throw new Exception("numero inserito non valido");
		}if(maps.getSelectedMap() == null){
			throw new Exception("mappa non selezionata");
		}
		
		panel.hostGame(creator.getText(), maps.getSelectedMap(), Integer.parseInt(playerNumber.getText()),levelMap.get(maps.getSelectedMap()));
		
	}
	private void resetHostPanel(){
		this.playerNumber.resetText();
		this.creator.resetText();
		maps.resetChoice();
	}
	
}
