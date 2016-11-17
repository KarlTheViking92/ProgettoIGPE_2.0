package gui.hud;

import javafx.scene.layout.HBox;

public class GemIndicator extends HBox {

	private int keys = 3;
	private int collected = 0;

	public GemIndicator() {
		this.setSpacing(5);
		for (int i = 0; i < keys; i++) {
			SimpleGem gem = new SimpleGem();
			this.getChildren().add(gem);
		}
	}

	public void collect() {
		collected++;
		if (collected < 4) {
			SimpleGem g = (SimpleGem) this.getChildren().get(collected - 1);
			g.collect();
		}
	}

}
