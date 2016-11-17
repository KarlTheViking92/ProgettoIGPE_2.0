package gui.element;

import core.element.Item;
import gui.ImageProvider;

public class BulletPlayerGraphic extends AbstractGraphicItem {

	public BulletPlayerGraphic(Item item) {
		super(item);

		if (!logicItem.getType())
			this.setImage(ImageProvider.getInstance().getBulletsImage("Weapon"));
		else {
			this.setImage(ImageProvider.getInstance().getBulletsImage("Fireball"));
			this.setFitHeight(40);
		}
	}

	@Override
	public void update() {
		this.relocate(logicItem.getX(), logicItem.getY());
		if (logicItem.hasCollided())
			destroyed = true;
	}
}
