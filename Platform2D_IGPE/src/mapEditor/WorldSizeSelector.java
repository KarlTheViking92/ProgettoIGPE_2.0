package mapEditor;

import gui.ImageProvider;
import gui.panel.AbstractGamePage;
import javafx.scene.effect.Glow;

//public class WorldSizeSelector extends Pane implements UpdatablePane {
public class WorldSizeSelector extends AbstractGamePage {
//	private Rectangle2D screen = Screen.getPrimary().getBounds();
//	private MapEditor mapEditor;
	private MyImage next;
	private MyImage back;
	private String cssFile = "file:resources/styleFiles/editorpane.css";
	private ImageContainer height;
	private ImageContainer width;

	public WorldSizeSelector() {
		init();
		addEvents();
	}

	private void init() {
		this.setBackground("/WorldSizeBackground.png");
		this.getStylesheets().add(cssFile);
//		this.getStyleClass().add("worldsize");
		this.setPrefWidth(screen.getWidth());
		this.setPrefHeight(screen.getHeight());
		this.next = new MyImage(ImageProvider.getInstance().getEditorImage("Button_Next"));
		this.back = new MyImage(ImageProvider.getInstance().getEditorImage("Button_Back"));
		this.height = new ImageContainer("Height", 15, screen.getWidth() * 0.3, screen.getHeight() * 0.25);
		this.width = new ImageContainer("Width", 20, screen.getWidth() * 0.3, screen.getHeight() * 0.5);
		back.setPosition(screen.getWidth() * 0.1, screen.getHeight() * 0.75);
		next.setPosition(screen.getWidth() * 0.83, back.getLayoutY());
		this.getChildren().addAll(height,width,back, next);
	}

	private void addEvents() {
		next.setOnMousePressed(e -> {
//			next.setEffect(new Glow(0.8));
//			mapEditor = new MapEditor(height.getSize(), width.getSize());
//			EditorManager.getInstance().goToEditor(mapEditor);
			nextPage();
		});
		
		back.setOnMouseClicked(e -> {
			previousPage();
		});
	}


	public void update() {
		
	}

	public int getSelectedWidth() {
		return width.getSize();
	}
	
	public int getSelectedHeight() {
		return height.getSize();
	}
}
