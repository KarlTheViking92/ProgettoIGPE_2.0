package gui.popup;

import gui.ImageProvider;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import mapEditor.EditorManager;

public class SavemapPopup extends AbstractPopup {
	private Button ok;
	private Button cancel;
	private Text endMessage;
	private TextField textField = new TextField();
	private boolean saved = false;

	public SavemapPopup(double width, double height) {
		super(width, height);
		message = new Text("INSERISCI IL NOME DELLA MAPPA");
		message.setLayoutX(this.getWidth() * 0.4 - (message.getBoundsInLocal().getWidth() / 2));
		message.setLayoutY(getHeight() * 0.2);
		message.setFont(POPUP_FONT);
		this.ok = new Button("Save");
		this.cancel = new Button("Exit");
		ok.setLayoutX(width * 0.60);
		ok.setLayoutY(height - (height / 1.7));
		cancel.setLayoutX(width * 0.70);
		cancel.setLayoutY(height - (height / 1.7));
		textField.setLayoutX(width * 0.30);
		textField.setLayoutY(height - (height / 1.7));
		ok.setOnAction(e -> {
			if (textField.getText().length() > 0) {
				EditorManager.getInstance().saveMap(textField.getText());
				endMessage = new Text("Mappa salvata con successo");
				endMessage.setFont(POPUP_FONT);
				endMessage.setLayoutX(this.getWidth() * 0.45 - (endMessage.getBoundsInLocal().getWidth() / 2));
				endMessage.setLayoutY(height - (height / 2.5));
				this.getChildren().add(endMessage);
				this.saved = true;
			}
		});
		cancel.setOnAction(e -> {
			this.delete = true;
		});
		message.setFill(Color.BLACK);
		this.getChildren().addAll(message, cancel, textField, ok);
	}

	public boolean isSave() {
		return saved;
	}

	public void setSave(boolean save) {
		this.saved = save;
	}

	public TextField getTextField() {
		return textField;
	}

}
