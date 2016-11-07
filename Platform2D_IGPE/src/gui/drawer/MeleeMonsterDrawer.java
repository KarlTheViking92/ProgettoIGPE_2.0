package gui.drawer;

import core.element.character.Character;
import core.element.character.Direction;

public class MeleeMonsterDrawer extends AbstractDrawer {

	public MeleeMonsterDrawer(Character c) {
		super(c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		super.draw();

		// if (character.isJumping() || character.isDoubleJumping() ||
		// character.isSuperJumping()){
		// this.setImage(animation.getCharacterJumpAnimation());
		// }else if(character.isFalling()){
		// this.setImage(animation.getCharacterFallAnimation());
		// }else
		if (character.getDirection() == Direction.STOP)
			this.setImage(animation.getCharacterIdleAnimation());
		else
			this.setImage(animation.getCharacterMoveAnimation());
	}

}
