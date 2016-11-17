package gui.drawer;

import core.element.character.Character;
import core.element.character.Direction;

public class PlayerDrawer extends AbstractDrawer {

	private double originalWidth;
	private double originalHeight;

	public PlayerDrawer(Character c) {
		super(c);
		originalWidth = c.getWidth();
		originalHeight = c.getHeight();
	}

	@Override
	public void draw() {

		super.draw();
		this.setFitWidth(originalWidth);
		this.setFitHeight(originalHeight);
		this.setOpacity(1);

		if (character.isJumping() || character.isDoubleJumping() || character.isSuperJumping()) {
			this.setImage(animation.getCharacterJumpAnimation());
		} else if (character.isFalling()) {
			this.setImage(animation.getCharacterFallAnimation());
		} else if (character.getDirection() == Direction.STOP)
			this.setImage(animation.getCharacterIdleAnimation());
		else
			this.setImage(animation.getCharacterMoveAnimation());
		if (character.isDead()) {
			this.setImage(animation.getCharacterDieAnimation());
			this.setLayoutY(this.getLayoutY() - 80);
			this.setFitHeight(45);
			this.setFitWidth(50);
			this.setOpacity(0.7);
		}
		if (character.isShooting()) {
			this.setImage(animation.getCharacterAttackAnimation());
			this.setFitWidth(45);
		}
	}

}
