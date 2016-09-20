package mapEditor;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PopupError extends Pane {
	//
	// private int width = 560;
	// private int height = 180;
	private Button ok;
	private boolean exit = false;
	public static final Font FONT_SMALL = Font.loadFont("file:resources/font/Engcomica.otf", 30);

	public PopupError(int width, int height) {
		Text t = new Text("MAPPA INCOMPLETA");
		this.getStyleClass().add("popup");
		this.setPrefHeight(height);
		this.setPrefWidth(width);
		ok = new Button("Ok");
		ok.setTranslateX(width / 2);
		ok.setTranslateY(height - (height / 2));
		t.setTranslateY((height/4));
		t.setTranslateX(width / 3.5);
		t.setFont(FONT_SMALL);
		t.setFill(Color.BLACK);
		ok.setOnAction(e -> {
			setExit(true);
		});
		this.getChildren().addAll(t, ok);
	}

	public boolean isClicked() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}
}
