package core.element.character;

import core.World.World;
import core.element.Position;

public class Player extends AbstractCharacter{

	public Player(String name, int life, int damage, World world) {
		super(name, life, damage,world);
	}
	
	@Override
	public void collectGem() {
		gems++;
	}
	
	public void faicose(double x, double y){
		position.setX(x);
		position.setY(y);
	}
}
