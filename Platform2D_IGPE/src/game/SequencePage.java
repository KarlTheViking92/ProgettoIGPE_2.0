package game;

import java.util.ArrayList;
import java.util.List;

import gui.panel.GamePage;

public class SequencePage {

	private List<GamePage> pages = new ArrayList<>();
	private int index = 0;

	public SequencePage(GamePage... gamePages) {
		for (GamePage page : gamePages) {
			pages.add(page);
		}
	}
	
	public GamePage getCurrentPage(){
		if(index >= 0 && index <= pages.size())
			return pages.get(index);
		
		return null;
	}

	public GamePage getNextPage() {
		index++;
		if (index >= pages.size())
			return null;

		GamePage next = pages.get(index);
		return next;
	}

	public GamePage getPreviousPage() {
		index--;
		if (index < 0)
			return null;

		GamePage prev = pages.get(index);
		return prev;
	}
	
	public void resetSequence(){
		index = 0;
	}

}
