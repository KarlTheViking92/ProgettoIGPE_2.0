package element.block;

import element.Position;

public abstract class Block {
	
	public final static int HEIGHT = 10;
	public final static int WIDTH = 10;
	
	private Position position;
	
	public Block(Position position) {
		this.setPosition(position);
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
}
