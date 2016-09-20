package core.element.character;

import core.World.World;
import core.element.Position;

public class Enemy extends AbstractCharacter {

	public Enemy(String name, int life, int damage, World w) {
		super(name, life, damage, w);
	}
}
