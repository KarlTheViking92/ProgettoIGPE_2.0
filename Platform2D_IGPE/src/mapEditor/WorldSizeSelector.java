package mapEditor;

import gui.ImageProvider;
import gui.panel.UpdatablePane;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

public class WorldSizeSelector extends Pane implements UpdatablePane {
	private Rectangle2D screen = Screen.getPrimary().getBounds();
	private Map mapEditor;
	private MyImage next;
	private MyImage back;
	private Scene s;
	private String cssFile = "file:resources/styleFiles/editorpane.css";
	private ImageContainer height;
	private ImageContainer width;

	public WorldSizeSelector(Scene s) {
		this.s = s;
		
		init();
		addEvents();
	}

	private void init() {
		this.getStylesheets().add(cssFile);
		this.getStyleClass().add("worldsize");
		this.setPrefWidth(screen.getWidth());
		this.setPrefHeight(screen.getHeight());
		this.next = new MyImage(ImageProvider.getInstance().getEditorImage("startButton"));
		this.back = new MyImage(ImageProvider.getInstance().getEditorImage("BackButton1"));
		this.height = new ImageContainer("Height", 15, screen.getWidth() * 0.3, screen.getHeight() * 0.25);
		this.width = new ImageContainer("Width", 15, screen.getWidth() * 0.3, screen.getHeight() * 0.5);
		back.setPosition(screen.getWidth() * 0.1, screen.getHeight() * 0.75);
		next.setPosition(screen.getWidth() * 0.83, back.getLayoutY());
		this.getChildren().addAll(height,width,back, next);
	}

	private void addEvents() {
		next.setOnMousePressed(e -> {
			next.setEffect(new Glow(0.8));
			mapEditor = new Map(height.getSize(), width.getSize(), s);
			EditorManager.getInstance().goToEditor(mapEditor);
		});

	}


	public void update() {
		
	}
}
