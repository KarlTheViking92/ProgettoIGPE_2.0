package gui.element;

import core.element.Item;
import javafx.scene.image.ImageView;

public class AbstractGraphicItem extends ImageView implements GraphicElement {

	Item logicItem;
	
	public AbstractGraphicItem(Item item)  {
		logicItem = item;
		this.setLayoutX(logicItem.getX());
		this.setLayoutY(logicItem.getY());
		this.setFitHeight(logicItem.getHeight());
		this.setFitWidth(logicItem.getWidth());
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

}
