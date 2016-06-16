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

		// bg sta per background
		Rectangle bg = new Rectangle(250, 30);
		bg.setOpacity(0.6);
		bg.setFill(Color.BLACK);
		bg.setEffect(new GaussianBlur(3.5));

		this.setAlignment(Pos.CENTER_LEFT);
		this.setRotate(0.5);
		this.getChildren().addAll(bg, text);

		// settiamo adesso cosa succede se il mouse seleziona il pannello

		this.setOnMouseEntered(event -> {
//			playSound("switch");
			bg.setTranslateX(10);
			text.setTranslateX(10);
			bg.setFill(Color.WHITE);
			text.setFill(Color.BLACK);
		});

		this.setOnMouseExited(event -> {
			bg.setTranslateX(0);
			text.setTranslateX(0);
			bg.setFill(Color.BLACK);
			text.setFill(Color.WHITE);
		});

		DropShadow drop = new DropShadow(50, Color.WHITE);
		drop.setInput(new Glow());

		this.setOnMousePressed(event -> setEffect(drop));
		this.setOnMouseReleased(event -> setEffect(null));
	}
}