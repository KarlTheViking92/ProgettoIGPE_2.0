package gui.animation;

import javafx.scene.image.Image;

public interface CharacterAnimation {

	public abstract Image getCharacterMoveAnimation();

	public abstract Image getCharacterIdleAnimation();

	public abstract Image getCharacterJumpAnimation();

	public abstract Image getCharacterFallAnimation();

	public abstract Image getCharacterAttackAnimation();

	public abstract Image getCharacterDieAnimation();
	
	public abstract boolean animationFinished();

	public abstract double getValueX();

	public abstract double getValueY();

	public abstract double getWidth();

	public abstract double getHeight();
}