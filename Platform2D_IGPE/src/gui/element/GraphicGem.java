package gui.element;

import core.element.Item;
import gui.ImageProvider;

public class GraphicGem extends AbstractGraphicItem{

	public GraphicGem(Item logicGem) {
		super(logicGem);
		this.setImage(ImageProvider.getInstance().getItems("gem"));
	}

	@Override
	public void update() {
		if (logicItem.isCollected()) {
			this.setVisible(false);
		}
	}
}
