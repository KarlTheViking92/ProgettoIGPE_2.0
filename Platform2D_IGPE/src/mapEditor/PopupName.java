package mapEditor;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PopupName extends Pane {
	private Button ok;
	private Button cancel;
	HBox hb = new HBox();
	private TextField textField = new TextField();
	private boolean save = false;
	private boolean canc = false;

	public PopupName(int width, int height) {
		this.setPrefHeight(height);
		this.setPrefWidth(width);
		this.getStyleClass().add("popup");
		this.ok = new Button("Save");
		this.cancel = new Button("Cancel");
		Text t = new Text("INSERISCI IL NOME");
		t.setTranslateY((height/4));
		t.setTranslateX(width / 3.5);
		ok.setOnAction(e -> {
			setSave(true);
		});
		cancel.setOnAction(e -> {
			setCanc(true);
		});
		t.setFont(Font.font("Verdana", 30));
		t.setFill(Color.BLACK);
		hb.setTranslateX(width / 3.5);
		hb.setTranslateY(height - (height / 2));
		hb.getChildren().addAll(getTextField(), cancel, ok);
		hb.setSpacing(30);
		this.getChildren().addAll(t, hb);
	}

	public boolean isSave() {
		return save;
	}

	public TextField getTextField() {
		return textField;
	}

	public void setSave(boolean save) {
		this.save = save;
	}

	public boolean isCanc() {
		return canc;
	}

	public void setCanc(boolean canc) {
		this.canc = canc;
	}

}
