package mapEditor;

import gui.panel.UpdatablePane;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Screen;

public class WorldSize extends GridPane implements UpdatablePane {
	private Rectangle2D screen = Screen.getPrimary().getBounds();
	// private List<String> worldPaths = new ArrayList<>();
	private Map mapEditor;
	private int worldWidth = 15;
	private int worldHeight = 15;
	private HBox hboxWidth;
	private HBox hboxHeight;
	private HBox hboxStart;
	private ImageView widthImg = new ImageView(new Image("file:resources/images/editor_panel_elements/worldWidth.png"));
	private ImageView heightImg = new ImageView(
			new Image("file:resources/images/editor_panel_elements/worldHeight.png"));
	private ImageView increaseWidth;
	private ImageView decreaseWidth;
	private ImageView increaseHeight;
	private ImageView decreaseHeight;
	private ImageView width = new ImageView();
	private ImageView height = new ImageView();
	private ImageView start;
	private ImageView back;
	private Scene s;
	private String cssFile = "file:resources/styleFiles/editorpane.css";

	public WorldSize(Scene s) {
		this.s = s;
		this.getStylesheets().add(cssFile);
		this.getStyleClass().add("worldsize");
		this.hboxWidth = new HBox();
		this.hboxHeight = new HBox();
		this.hboxStart = new HBox();
		this.setPrefWidth(screen.getWidth());
		this.setPrefHeight(screen.getHeight());
		this.decreaseWidth = new ImageView(new Image("file:resources/images/editor_panel_elements/decreases.png"));
		this.increaseWidth = new ImageView(new Image("file:resources/images/editor_panel_elements/increases.png"));
		this.decreaseHeight = new ImageView(new Image("file:resources/images/editor_panel_elements/decreases.png"));
		this.increaseHeight = new ImageView(new Image("file:resources/images/editor_panel_elements/increases.png"));
		this.start = new ImageView(new Image("file:resources/images/editor_panel_elements/startButton.png"));
		this.back = new ImageView(new Image("file:resources/images/editor_panel_elements/backButton1.png"));
		hboxWidth.getChildren().addAll(widthImg, decreaseWidth, width, increaseWidth);
		hboxHeight.getChildren().addAll(heightImg, decreaseHeight, height, increaseHeight);
		hboxStart.getChildren().addAll(back, start);
		hboxWidth.setAlignment(Pos.CENTER);
		hboxHeight.setAlignment(Pos.CENTER);
		hboxWidth.setSpacing(65);
		hboxHeight.setSpacing(65);
		hboxStart.setAlignment(Pos.CENTER);
		hboxStart.setSpacing(900);
		this.add(hboxWidth, 0, 0);
		this.add(hboxHeight, 0, 1);
		this.add(hboxStart, 0, 2);
		setConstraints();
		addEvents();
	}

	private void addEvents() {
		increaseHeight.setOnMousePressed(e -> {

			increaseHeight.setEffect(new Glow(0.8));
			if (worldHeight < 50) {
				worldHeight++;
			}
		});
		increaseHeight.setOnMouseReleased(e -> {
			increaseHeight.setEffect(null);
		});

		decreaseHeight.setOnMousePressed(e -> {
			decreaseHeight.setEffect(new Glow(0.8));
			if (worldHeight > 15) {
				worldHeight--;
			}
		});

		decreaseHeight.setOnMouseReleased(e -> {
			decreaseHeight.setEffect(null);
		});

		increaseWidth.setOnMousePressed(e -> {
			increaseWidth.setEffect(new Glow(0.8));
			if (worldWidth < 50) {
				worldWidth++;
			}
		});

		increaseWidth.setOnMouseReleased(e -> {
			increaseWidth.setEffect(null);
		});

		decreaseWidth.setOnMousePressed(e -> {
			decreaseWidth.setEffect(new Glow(0.8));
			if (worldWidth > 15) {
				worldWidth--;
			}
		});

		decreaseWidth.setOnMouseReleased(e -> {
			decreaseWidth.setEffect(null);
		});

		start.setOnMousePressed(e -> {
			start.setEffect(new Glow(0.8));
			mapEditor = new Map(worldHeight, worldWidth, s);
			EditorManager.getInstance().goToEditor(mapEditor);
		});

	}

	public int getWorldHeight() {
		return worldHeight;
	}

	public int getWorldWidth() {
		return worldWidth;
	}

	private void setConstraints() {
		RowConstraints row1 = new RowConstraints();
		row1.setPercentHeight(50);
		RowConstraints row2 = new RowConstraints();
		row1.setPercentHeight(50);
		this.getRowConstraints().addAll(row1, row2);
		ColumnConstraints col = new ColumnConstraints();
		col.setPercentWidth(100);
		this.getColumnConstraints().addAll(col);
	}

	public void update() {
		String heightPath = "file:resources/images/editor_panel_elements/PathTest_" + Integer.toString(worldHeight)
				+ ".png";
		String widthPath = "file:resources/images/editor_panel_elements/PathTest_" + Integer.toString(worldWidth)
				+ ".png";
		height.setImage(new Image(heightPath));
		width.setImage(new Image(widthPath));
	}
}
