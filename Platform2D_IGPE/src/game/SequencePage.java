package game;

import java.util.ArrayList;
import java.util.List;

import gui.panel.GamePage;

public class SequencePage extends ArrayList<GamePage>{

//	private List<GamePage> pages = new ArrayList<>();
	private int index = 0;

	public SequencePage(GamePage... gamePages) {
		for (GamePage page : gamePages) {
			this.add(page);
		}
	}
	
	public GamePage getCurrentPage(){
		if(index >= 0 && index <= this.size())
			return this.get(index);
		
		return null;
	}

	public GamePage getNextPage() {
		index++;
		if (index >= this.size())
			return null;

		GamePage next = this.get(index);
		return next;
	}

	public GamePage getPreviousPage() {
		index--;
		if (index < 0)
			return null;

		GamePage prev = this.get(index);
		return prev;
	}
	
	public void resetSequence(){
		index = 0;
	}

}
