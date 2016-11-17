package gui.panel.singlePlayer;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class CustomTextField extends StackPane {
	private static final Font FONT_SMALL = Font.loadFont("file:resources/font/Engcomica.otf", 30);
	private Rectangle2D screen = Screen.getPrimary().getBounds();
	Rectangle background = new Rectangle();
	TextField nameField;

	public CustomTextField() {
		this.setHeight(screen.getHeight() * 0.035);
		this.setWidth(screen.getWidth() * 0.15);
		this.nameField = new TextField();
		this.background.setHeight(this.getHeight());
		this.background.setWidth(this.getWidth());
		this.background.setArcHeight(20);
		this.background.setArcWidth(20);
		this.background.setStroke(Color.YELLOW);
		this.background.setStrokeWidth(5);
		this.background.setFill(Color.web("#4A609A"));
		this.nameField.setStyle("-fx-background: null; -fx-background-color: null; -fx-text-fill:#FFF56A;");
		this.nameField.setAlignment(Pos.CENTER);
		this.nameField.setFont(FONT_SMALL);

		this.getChildren().addAll(background, nameField);
	}

	public String getText() {
		return nameField.getText();
	}

	public void resetText() {
		nameField.clear();
	}
}
