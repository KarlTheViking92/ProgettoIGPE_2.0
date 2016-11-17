package gui.drawer;

import core.element.character.Character;
import core.element.character.Direction;

public class MeleeEnemyDrawer extends AbstractDrawer {

	public MeleeEnemyDrawer(Character c) {
		super(c);
		this.setFitHeight(90);
		this.setFitWidth(100);
	}

	@Override
	public void draw() {
		super.draw();
		this.setLayoutY(character.getY() - 7.5);
		rotation.setPivotX(this.getX() + (character.getWidth()));

		if (character.getDirection() == Direction.STOP)
			this.setImage(animation.getCharacterIdleAnimation());
		if (character.isAttacking()) {
			this.setImage(animation.getCharacterAttackAnimation());
		}
		if ((character.getDirection() == Direction.RIGHT || character.getDirection() == Direction.LEFT)
				&& !character.isAttacking())
			this.setImage(animation.getCharacterMoveAnimation());
		if (character.isDead()) {
			this.setImage(animation.getCharacterDieAnimation());
			if (animation.animationFinished())
				destroy = true;
		}
	}

}
