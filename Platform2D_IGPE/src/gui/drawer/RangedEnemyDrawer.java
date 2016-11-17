package gui.drawer;

import core.element.character.Character;
import core.element.character.Direction;

public class RangedEnemyDrawer extends AbstractDrawer {

	public RangedEnemyDrawer(Character c) {
		super(c);
	}

	@Override
	public void draw() {

		super.draw();
		if (character.getDirection() == Direction.STOP) {
			this.setImage(animation.getCharacterIdleAnimation());
		} else if (character.isShooting() && character.getDirection() != Direction.STOP) {

			this.setImage(animation.getCharacterAttackAnimation());
		}
		if (character.isDead()) {
			this.setImage(animation.getCharacterDieAnimation());
			if (animation.animationFinished())
				destroy = true;
		}
	}

}
