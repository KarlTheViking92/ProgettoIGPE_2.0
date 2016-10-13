package gui.panel.singlePlayer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import gui.panel.AbstractGamePage;
import gui.panel.CustomComponent;
import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class SelectMap extends AbstractGamePage {
	private ListView<String> list;
	private List<String> l = new ArrayList<>();
	private int levelIndex = 0;
	private Map<String, String> levelMap = new HashMap<>();
	private final String levelPath = "resources/Levels/staticLevel";
	private final String customLevelPath = "resources/Levels/customLevel";

	private CustomComponent listview;
	
	private ImageView next = new ImageView();
	private ImageView back = new ImageView();
	private ImageView customLevelButton = new ImageView();
	private ImageView standardLevelButton = new ImageView();
	private Text levelName;

	public SelectMap() {
//		this.setBackground("SelectMapMenu.png");
		this.next.setImage(new Image("file:resources/images/editor/Button_Next.png"));
		this.back.setImage(new Image("file:resources/images/editor/Button_Back.png"));
		
//		this.info.setFont(FONT_BIG);
//		this.info.setStrokeWidth(1);
//		this.info.setStroke(Color.BLACK);
//		this.info.setFill(Color.web("#DC8014"));
		this.title.setText("Select a level");
		this.title.setLayoutX(screen.getWidth()*0.5 - (title.getBoundsInLocal().getWidth()/2));
//		this.info.setLayoutY(screen.getHeight()*0.2);
		next.setOnMouseClicked(e -> {
			System.out.println(list.getSelectionModel().getSelectedItem());
//			nextPage();
		});
		this.back.setOnMouseClicked(e -> {
			previousPage();
		});
		this.back.setLayoutX(screen.getWidth()*0.1);
		this.next.setLayoutX(screen.getWidth()*0.8);
		this.back.setLayoutY(screen.getHeight()*0.84);
		this.next.setLayoutY(screen.getHeight()*0.84);
		this.next.setFitWidth(screen.getWidth()*0.08);
		this.next.setFitHeight(screen.getWidth()*0.08);
		this.back.setFitWidth(screen.getWidth()*0.08);
		this.back.setFitHeight(screen.getWidth()*0.08);
		
		
		listview = new CustomListView(screen.getWidth()*0.4, screen.getHeight()*0.4 , screen.getWidth()*0.5 - (screen.getWidth()*0.4)/2 , screen.getHeight()*0.5 - (screen.getHeight()*0.4)/2);
		levelName = new Text();
		levelName.setLayoutX(screen.getWidth()*0.3);
		levelName.setLayoutY(screen.getHeight()*0.28);
		levelName.setFont(FONT_SMALL);
		levelName.setFill(Color.web("#A13C3F"));
		levelName.setStrokeWidth(2);
		levelName.setStroke(Color.web("#D99F5D"));
//		this.list = new ListView<>();		
//		this.list.setPrefWidth(screen.getWidth()*0.2);
//		this.list.setPrefHeight(screen.getHeight()*0.2);
//		this.list.setLayoutX(screen.getWidth()*0.5 - list.getPrefWidth()/2);
//		this.list.setLayoutY(screen.getHeight()*0.5 - list.getPrefHeight()/2);
		readFolders(levelPath);
		this.showLevelList();
//		levelName.setText("Level name : " + l.get(levelIndex));
//		list.setItems(FXCollections.observableArrayList(l));
		loadButtons();
		this.getChildren().addAll(next, back, title, (Node)listview);
	}

	private void loadButtons(){
		this.standardLevelButton.setLayoutX(screen.getWidth()*0.05);
		this.standardLevelButton.setLayoutY(screen.getHeight()*0.22);
		this.standardLevelButton.setFitWidth(screen.getWidth()*0.2);
		this.standardLevelButton.setFitHeight(screen.getHeight()*0.3);
		this.standardLevelButton.setImage(new Image("file:resources/images/buttontizio.png"));
		
		this.customLevelButton.setLayoutX(standardLevelButton.getLayoutX());
		this.customLevelButton.setLayoutY(standardLevelButton.getLayoutY() + standardLevelButton.getFitHeight() + screen.getHeight()*0.001);
		this.customLevelButton.setFitWidth(screen.getWidth()*0.2);
		this.customLevelButton.setFitHeight(screen.getHeight()*0.3);
		this.customLevelButton.setImage(new Image("file:resources/images/buttongatto.png"));
		
		this.standardLevelButton.setOnMouseClicked(e ->{
			System.out.println("voglio i livelli del gioco");
//			this.levelIndex++;
			readFolders(levelPath);
			this.showLevelList();
			
		});
		this.customLevelButton.setOnMouseClicked(e -> {
			System.out.println("voglio i livelli miei");
//			this.levelIndex--;
			readFolders(customLevelPath);
			this.showLevelList();
		});
		
		this.getChildren().addAll(standardLevelButton, customLevelButton);
	}
	
	private void readFolders(String path) {
		l.clear();
		Pattern regex = Pattern.compile("_color$");
		try (Stream<Path> paths = Files.walk(Paths.get(path))) {
			paths.forEach(filePath -> {
				Matcher match = regex.matcher(filePath.getFileName().toString());
				if (Files.isRegularFile(filePath) && !match.find()) {
//					System.out.println(filePath.getFileName());
					levelMap.put(filePath.getFileName().toString(), filePath.toString());
					l.add(filePath.getFileName().toString());
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void showLevelList(){
		listview.reset();
		System.out.println("scrivo le stringhe dei livelli");
		for (String level : l) {
			listview.addItem(new CustomListObject(listview.getComponentWidth()*0.8, listview.getComponentHeight()*0.1, level, (CustomListView) listview));
		}
	}
	
	@Override
	public void update() {
//		levelName.setText("Level name : " + l.get(levelIndex));
	}

}
