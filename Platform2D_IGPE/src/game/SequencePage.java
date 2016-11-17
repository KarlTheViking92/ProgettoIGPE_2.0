package game;

import java.util.ArrayList;

import gui.panel.GamePage;

public class SequencePage extends ArrayList<GamePage> {

	private int index = 0;

	public SequencePage(GamePage... gamePages) {
		for (GamePage page : gamePages) {
			this.add(page);
		}
	}

	public GamePage getCurrentPage() {
		if (index >= 0 && index <= this.size())
			return this.get(index);

		return null;
	}

	public GamePage getNextPage() {
		index++;
		if (index >= this.size())
			return null;

		GamePage next = this.get(index);
		next.reset();
		return next;
	}

	public GamePage getPreviousPage() {
		index--;
		if (index < 0)
			return null;

		GamePage prev = this.get(index);
		prev.reset();
		return prev;
	}

	public void resetSequence() {
		index = 0;
	}

}
