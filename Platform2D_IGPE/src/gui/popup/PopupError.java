package gui.popup;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PopupError extends AbstractPopup {

	private Text message = new Text("MAPPA INCOMPLETA");
	private Button ok;
	private boolean exit = false;
	
	public PopupError(double width, double height) {
		super(width, height);
		
		message.setLayoutX(this.getWidth() * 0.5 - (message.getBoundsInLocal().getWidth()/2));
		message.setLayoutY(getHeight()*0.2);
		ok = new Button("Ok");
		ok.setTranslateX(width / 2);
		ok.setTranslateY(height - (height / 2));
		message.setFont(POPUP_FONT);
//		t.setFill(Color.BLACK);
		ok.setOnAction(e -> {
			delete = true;
		});
		this.getChildren().addAll(message, ok);
	}

	public boolean isClicked() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}
}
