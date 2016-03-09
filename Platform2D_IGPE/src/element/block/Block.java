package element.block;

import element.Position;

public abstract class Block {
	public final static int HEIGHT = 10;
	public final static int WIDTH = 10;
	Position position;
	public Block(Position position) {
		this.position = position;
	}
}
