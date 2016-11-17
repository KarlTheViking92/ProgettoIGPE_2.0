package core.element.character;

import core.World.World;

public class Player extends AbstractCharacter {

	public Player(String name, int life, int damage, World world) {
		super(name, life, damage, world);
	}

	@Override
	public void collectGem() {
		gems++;
	}
}
