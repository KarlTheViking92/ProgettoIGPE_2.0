package core.element.block;

import core.World.World;
import core.element.Position;
import core.element.character.Character;
import core.element.character.Player;
import core.gameManagers.PlayManager;

public class SuperJumpBlock extends AbstractBlock {

	private double xSuperJump;
	private double ySuperJump;

	public SuperJumpBlock(World w,Position position) {
		super(w,position, 12);
	}

	@Override
	public boolean collision(double x, double y, int height, int width) {
		if (super.collision(x, y, height, width)) {
			// System.out.println("PlayerX: " + x + " PlayerY: " + y + " width:"
			// + width + " height: " + height);
			// System.out.println(
			// "CuboX: " + this.getX() + " CuboY: " + (this.getY()) + "
			// larghezza/altezza " + this.getWIDTH());
			// double p_bX = (x + (x + width)) / 2;
			// double p_bY = (y + (y + height)) / 2;
			xSuperJump = 0.0;
			ySuperJump = 0.0;
			// System.out.println("collido");

//			if (x < this.getX() && y > this.getY() && y + height < this.getY() + this.getHEIGHT()) {
//				xSuperJump = -2;
//				System.out.println("sono a sinistra");
//			}
//		 if (x + width > this.getX() + this.getWIDTH() && y > this.getY()
//					&& y + height < this.getY() + this.getHEIGHT()) {
//				xSuperJump = 2;
//				System.out.println("sono a destra");
//			}
//			 if (y + height > this.getY() + this.getHEIGHT() && x > this.getX() && x + width < this.getX() + this.getWIDTH()) {
//				ySuperJump = 2;
//				System.out.println("sono sotto");
//			}
//
			 if (y < this.getY() /*&& x > this.getX()
					&& x + width < this.getX() + this.getWIDTH()*/) {
				ySuperJump = -2;
//				System.out.println("sono sopra");
				animated = true;
			}

		}

		return super.collision(x, y, height, width);
	}

	@Override
	public void setPlayerState(Character c) {
		c.setSuperJumpDirection(xSuperJump, ySuperJump);
		 c.superJump();
	}

	@Override
	public void update() {
		/*
		 * if (collided) { animated = true; collided = false; }
		 */

	}
}
