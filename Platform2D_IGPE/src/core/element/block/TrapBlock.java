package core.element.block;

import core.World.World;
import core.element.Position;
import core.element.character.Character;

public class TrapBlock extends AbstractBlock {

	private long current;
	private long lastMillis;

	public TrapBlock(World w, Position position) {
		super(w, position, 13);
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {

		if (super.collision(x, y, height, width)) {
			if ((this.position.getX() + WIDTH - width / 1.5) < x && y < getY()) {
				return false;
			}
			if (this.position.getX() + width / 1.5 > (x + width) && y < getY()) {
				return false;
			}

		}
		return super.collision(x, y, height, width);
	}

	@Override
	public void setPlayerState(Character c) {
		if (c.getY() + c.getHeight() < this.getY()) {
			lastMillis = System.currentTimeMillis();
			collided = true;
			c.kill();
		}
	}

	@Override
	public void update() {
		if (collided) {
			animated = true;
			collided = false;
		} else {
			current = System.currentTimeMillis();
			if (current - lastMillis >= 1000) {
				animated = false;
				collided = false;
			}
		}

	}
}