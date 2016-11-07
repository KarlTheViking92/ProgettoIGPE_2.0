package gui.drawer;

import core.element.character.Character;
import core.element.character.Direction;

public class PlayerDrawer extends AbstractDrawer {

	public PlayerDrawer(Character c) {
		super(c);
	}

	@Override
	public void draw() {
		super.draw();
		
		if (character.isJumping() || character.isDoubleJumping() || character.isSuperJumping()){
			this.setImage(animation.getCharacterJumpAnimation());    
		}else if(character.isFalling()){
			this.setImage(animation.getCharacterFallAnimation());
		}else if(character.getDirection() == Direction.STOP)
			this.setImage(animation.getCharacterIdleAnimation());
		else
			this.setImage(animation.getCharacterMoveAnimation());
	}
	
}
