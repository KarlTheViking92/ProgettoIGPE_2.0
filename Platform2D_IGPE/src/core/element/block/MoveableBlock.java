package core.element.block;

import core.World.World;
import core.element.Position;
import core.element.character.Character;
import core.element.character.Player;

public class MoveableBlock extends AbstractBlock {

	private int direction = 1;
	private double delta = 1;
	private boolean collide = false;
	private double prova = 0.0;

	public MoveableBlock(World w, Position position) {
		super(w, position, 11);
	}

	public void swap() {
		direction *= -1;
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {
		if (super.collision(x, y, height, width)) {
			System.out.println("sopra " + x + " " + y + " " + height + " " + this.getX() + " " + this.getWIDTH() + " "
					+ this.getY());
			if ((x >= this.getX()) && (x <= (this.getX() + this.getWIDTH())) && (int) (y + height) <= this.getY()) {

				collide = true;
				System.out.println(delta + " " + direction);
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
		System.out.println("GAYYYYYYYYYYYYYYYY " + delta + " " + direction + " " + this.getX());
		// System.out.println("stampo la x del cubo mobile "+this.getX());
	}

	@Override
	public void setPlayerState(Character c) {
		if (collide)
			c.setPosition(new Position(this.getX(), this.getY() - c.getHeight()));
	}
}
