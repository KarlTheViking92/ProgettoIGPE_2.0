package gui.drawer;

import core.element.character.Character;
import gui.animation.CharacterAnimation;

public interface Drawer {

	public abstract void draw();

	public abstract CharacterAnimation loadAnimation(Character c);

	public abstract boolean toDestroy();
}
