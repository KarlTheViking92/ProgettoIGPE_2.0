package gui.hud;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Screen;

public class SimpleGem extends ImageView {

	private Image off = new Image("file:resources/images/hud/gem_off.png");
	private Image on = new Image("file:resources/images/hud/gem_on.png");

	private boolean collected = false;

	public SimpleGem() {
		this.setFitWidth(Screen.getPrimary().getBounds().getWidth() * 0.05);
		this.setFitHeight(Screen.getPrimary().getBounds().getHeight() * 0.06);
		this.setImage(off);
	}

	public void collect() {
		this.setImage(on);
		this.collected = true;
	}

	public boolean isCollected() {
		return collected;
	}
}
