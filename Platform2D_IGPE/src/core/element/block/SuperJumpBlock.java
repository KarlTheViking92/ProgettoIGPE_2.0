package core.element.block;

import core.World.World;
import core.element.Position;
import core.element.character.Character;

public class SuperJumpBlock extends AbstractBlock {

	private double xSuperJump;
	private double ySuperJump;

	public SuperJumpBlock(World w,Position position) {
		super(w,position, 12);
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {
		if (super.collision(x, y, height, width)) {
			xSuperJump = 0.0;
			ySuperJump = 0.0;
			
			double Y = y + height/2;
			double X = x + width/2;
//			System.out.println("blocco è x: " + getX() +" y: " + getY());
//			System.out.println("X : " + X + " Y: " + Y);
			if (x <= this.getX() && (Y >= getY() && Y <= getY()+HEIGHT)) {
				System.out.println("sono a sinistra");
				xSuperJump = -1;
			}
			
			else if (x >= this.getX()+WIDTH && (Y >= getY() && Y <= getY()+HEIGHT)) {
				xSuperJump = 1;
				System.out.println("sono a destra");
			}
			 if (X >= getX() && X <= getX()+WIDTH && y < getY()) {
				System.out.println("sono sopra");
				ySuperJump = -1;
				animated = true;
			}
			 else if (X >= getX() && X <= getX()+WIDTH && y > getY()) {
				 System.out.println("sono sotto");
				 ySuperJump = 1;
				 
			 }

			 
		}

		return super.collision(x, y, height, width);
	}

	@Override
	public void setPlayerState(Character c) {
		System.out.println("superjump");
		c.superJump();
		c.setSuperJumpDirection(xSuperJump, ySuperJump);
	}

	@Override
	public void update() {
		/*
		 * if (collided) { animated = true; collided = false; }
		 */

	}
}
