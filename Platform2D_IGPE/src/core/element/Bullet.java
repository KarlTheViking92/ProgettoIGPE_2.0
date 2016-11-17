package core.element;


import core.World.World;
import core.element.character.Direction;

public class Bullet extends AbstractItem {

	private boolean type = false; // se è false indica il bullet del player ,
									// altrimenti indica il bullet di un nemico
	private Direction direction;
	private double speed = 60.0;
	private double deltaTime = 0.08;
	private double x1;
	private double y1;
	private double ay = -9.8; // gravità
	private double ax = 0;
	private double angle = 30;
	private double vx;
	private double vy;


	private World w;

	public Bullet(double x, double y, Direction d, World w, boolean type) {
		super(x, y, 40, 15);
		this.w = w;
		this.direction = d;
		this.x1 = x;
		this.y1 = y;
		this.vx = this.speed * Math.cos(this.angle * (Math.PI / 180.0));
		this.vy = this.speed * Math.sin(this.angle * (Math.PI / 180.0));
		this.type = type;
	}

	@Override
	public void update() {

		if (direction == Direction.LEFT) {
			this.x1 -= this.vx * this.deltaTime;
			this.y1 -= this.vy * this.deltaTime;

			this.vx += this.ax * this.deltaTime;
			this.vy += this.ay * this.deltaTime;

		} else if (direction == Direction.RIGHT) {

			this.x1 += this.vx * this.deltaTime;
			this.y1 -= this.vy * this.deltaTime;

			this.vx += this.ax * this.deltaTime;
			this.vy += this.ay * this.deltaTime;
		}
		if (!w.checkBulletBlockCollision(this, x1, y1, height, width)) {
			this.setX(x1);
			this.setY(y1);
		} else
			collided = true;

	}

	@Override
	public boolean getType() {
		return type;
	}

	public void setEnemyBulletType() {
		this.type = true;
	}

}
