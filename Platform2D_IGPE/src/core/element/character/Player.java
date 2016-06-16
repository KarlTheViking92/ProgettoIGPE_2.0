package core.element.character;

import core.World.World;
import core.element.Position;

public class Player extends AbstractCharacter{

	public Player(Position position, int life, int damage, World world) {
		super(position, life, damage,world);
	}
}
