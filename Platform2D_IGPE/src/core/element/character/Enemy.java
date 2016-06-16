package core.element.character;

import core.World.World;
import core.element.Position;

public class Enemy extends AbstractCharacter {

	public Enemy(Position position, int life, int damage, World w) {
		super(position, life, damage, w);
	}
}
