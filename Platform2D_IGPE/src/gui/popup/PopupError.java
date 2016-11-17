package gui.popup;

import core.gameManagers.MenuManager;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class PopupError extends AbstractPopup {

	private Button ok;
	private boolean exit = false;

	public PopupError(double width, double height) {
		super(width, height);
		message = new Text("MAPPA INCOMPLETA");
		message.setLayoutX(this.getWidth() * 0.5 - (message.getBoundsInLocal().getWidth() / 2));
		message.setLayoutY(getHeight() * 0.2);
		ok = new Button("ok");
		ok.setTranslateX(width / 2);
		ok.setTranslateY(height - (height / 2));
		message.setFont(POPUP_FONT);
		// t.setFill(Color.BLACK);
		ok.setOnAction(e -> {
			MenuManager.getInstance().remove(this);
		});
		this.getChildren().addAll(message, ok);
	}

	public void setText(String t) {
		message.setText(t);
		message.setLayoutX(this.getWidth() * 0.5 - (message.getBoundsInLocal().getWidth() / 2));
	}

	public boolean isClicked() {
		return exit;
	}

	public void setExit(boolean exit) {
		this.exit = exit;
	}
}
