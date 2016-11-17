package gui.gameMenu;

import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class MenuButton extends StackPane {
	private Text text;

	public MenuButton(String name) {
		text = new Text(name);
		text.getFont();
		text.setFont(Font.font(20));
		text.setFill(Color.WHITE);

		Rectangle background = new Rectangle(250, 30);
		background.setOpacity(0.6);
		background.setFill(Color.BLACK);
		background.setEffect(new GaussianBlur(3.5));

		this.setAlignment(Pos.CENTER_LEFT);
		this.setRotate(0.5);
		this.getChildren().addAll(background, text);

		this.setOnMouseEntered(event -> {
			background.setTranslateX(10);
			text.setTranslateX(10);
			background.setFill(Color.WHITE);
			text.setFill(Color.BLACK);
		});

		this.setOnMouseExited(event -> {
			background.setTranslateX(0);
			text.setTranslateX(0);
			background.setFill(Color.BLACK);
			text.setFill(Color.WHITE);
		});

		DropShadow drop = new DropShadow(50, Color.WHITE);
		drop.setInput(new Glow());

		this.setOnMousePressed(event -> setEffect(drop));
		this.setOnMouseReleased(event -> setEffect(null));
	}
}