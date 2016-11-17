package gui.panel.singlePlayer;

import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class CustomButton extends ImageView {

	public CustomButton() {
		this.setFitHeight(Screen.getPrimary().getBounds().getWidth()*0.05);
		this.setFitWidth(Screen.getPrimary().getBounds().getWidth()*0.05);
	}

}
