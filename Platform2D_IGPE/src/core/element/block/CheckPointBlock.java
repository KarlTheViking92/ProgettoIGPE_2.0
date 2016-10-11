package core.element.block;

import core.element.Position;
import core.element.character.Character;

public class CheckPointBlock extends AbstractBlock {

	public CheckPointBlock(Position position) {
		super(position, 8);
	}

	@Override
	public void setPlayerState(Character c) {
		if (c.getY() + c.getHeight() < this.getY())
			c.setLastSpawnPoint(this.getX(), this.getY() - this.getHEIGHT());
			
		
		}
	}

