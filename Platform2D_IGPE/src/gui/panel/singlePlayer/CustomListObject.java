package gui.panel.singlePlayer;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CustomListObject extends StackPane {
	private final Font FONT = Font.loadFont("file:resources/font/Engcomica.otf", 40);
	private final static Color STROKE_COLOR = Color.web("#eaeceb");
	private final static Color BACKGROUND_COLOR = Color.web("#6b7773");
	private Rectangle background;
	private String name;
	private Text t;
	private CustomListView list;

	public CustomListObject(double w, double h, String s, CustomListView c) {

		this.list = c;
		background = new Rectangle(w, h);
		name = s;
		t = new Text(s);

		// this.item = new Rectangle(width*0.8, height*0.1);
		// this.item.setFill(value);
		this.background.setOnMouseEntered(e -> {
			this.background.setFill(STROKE_COLOR);
		});
		this.background.setOnMouseExited(e -> {
			if (list.getSelectedMap() == null || !name.equals(list.getSelectedMap())){
				this.background.setFill(BACKGROUND_COLOR);
			}
		});

		this.setOnMouseClicked(e -> {
			this.background.setFill(STROKE_COLOR);
			list.selectMap(this);
		});
		this.setOnMouseReleased(e -> {
		});
		this.background.setArcHeight(20);
		this.background.setArcWidth(20);
		this.background.setStroke(STROKE_COLOR);
		this.background.setStrokeWidth(5);
		this.background.setFill(BACKGROUND_COLOR);
		t.setFont(FONT);
		t.setFill(Color.web("#A13C3F"));
		t.setStrokeWidth(1.5);
		t.setStroke(Color.web("#D99F5D"));
		this.getChildren().addAll(background, t);

	}

	public String getName() {
		return name;
	}
	public void resetBackground(){
		this.background.setFill(BACKGROUND_COLOR);
	}

}
