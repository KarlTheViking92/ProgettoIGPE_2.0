package gui.element;

import core.element.Item;
import javafx.scene.image.ImageView;

public class AbstractGraphicItem extends ImageView implements GraphicElement {

	protected Item logicItem;
	protected boolean destroyed = false;
	public AbstractGraphicItem(Item item)  {
		logicItem = item;
		this.setLayoutX(logicItem.getX());
		this.setLayoutY(logicItem.getY());
		this.setFitHeight(logicItem.getHeight());
		this.setFitWidth(logicItem.getWidth());
	}

	@Override
	public void update() {
		
	}
	@Override
	public boolean destroy() {
		return destroyed;
	}
}
