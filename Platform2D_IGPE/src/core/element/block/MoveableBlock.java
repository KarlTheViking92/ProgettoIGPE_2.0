package core.element.block;

import core.World.World;
import core.element.Position;
import core.element.character.Character;
import core.element.character.Player;

public class MoveableBlock extends AbstractBlock {

	private int direction = 1;
	private double delta = 1;
	private boolean collide = false;

	public MoveableBlock(World w, Position position) {
		super(w, position, 11);
	}

	public void swap() {
		direction *= -1;
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {
		if (super.collision(x, y, height, width)) {
			if ((x >= this.getX()) && (x <= (this.getX() + this.getWIDTH())) && (int) (y + height) <= this.getY()) {
				collide = true;
			}
			if (x >= (this.getX() + this.getWIDTH()) && direction > 0) {
				swap();
			} else if ((x + width) <= getX() && direction < 0) {
				swap();
			}

			return true;
		}
		return false;
	}

	@Override
	public void update() {

		double x = this.getX() + this.getHEIGHT() + (delta * direction);
		if (direction < 0)
			x = this.getX() + (delta * direction);

		if (world.checkBlockCollision(this, x, this.getY(), (int) this.getHEIGHT())) {
			direction *= -1;
		}
		this.setX(this.getX() + (delta * direction));
	}

	@Override
	public void setPlayerState(Character c) {
		c.moveCharacter(delta * direction);

	}
}
