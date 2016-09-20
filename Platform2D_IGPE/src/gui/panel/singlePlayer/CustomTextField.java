package gui.panel.singlePlayer;

import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CustomTextField extends StackPane{

	Rectangle background = new Rectangle();
	TextField nameField;
	public CustomTextField() {
		this.setHeight(40);
		this.setWidth(300);
		this.nameField = new TextField();
		this.background.setHeight(40);
		this.background.setWidth(300);
		this.background.setArcHeight(20);
		this.background.setArcWidth(20);
		this.background.setStroke(Color.YELLOW);
		this.background.setStrokeWidth(5);
		this.background.setFill(Color.web("#4A609A"));
		this.nameField.setStyle("-fx-background: null; -fx-background-color: null; -fx-text-fill:white");
		this.nameField.setAlignment(Pos.CENTER);
		this.nameField.setFont(SelectPlayer.FONT_SMALL);
		this.setAlignment(Pos.CENTER);
		this.getChildren().addAll(background, nameField);
	}

}
