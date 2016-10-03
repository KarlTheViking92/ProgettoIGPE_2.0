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

public class SavemapPopup extends AbstractPopup{
	private Button ok;
	private Button cancel;
	private TextField textField = new TextField();
	private boolean saved = false;

	public SavemapPopup(double width, double height) {
		super(width, height);
//		this.ok = new Button("Save");
//		this.cancel = new Button("Cancel");
//		Text t = new Text("INSERISCI IL NOME");
//		ok.setOnAction(e -> {
//			if (textField.getText().length() > 0) {
////				editor.saveMap(nameLevel);
//				EditorManager.getInstance().saveMap(textField.getText());
//				this.saved = true;
//			}
//		});
//		cancel.setOnAction(e -> {
//			this.delete = true;
//		});
//		t.setFill(Color.BLACK);
//		this.getChildren().addAll(t);
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
