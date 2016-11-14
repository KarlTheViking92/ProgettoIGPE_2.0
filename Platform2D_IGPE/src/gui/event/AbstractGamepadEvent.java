package gui.event;

import core.gameManagers.PlayManager;
import gui.panel.singlePlayer.SinglePlayerPane;
import net.java.games.input.Event;

public class AbstractGamepadEvent implements GamepadEvent {

	SinglePlayerPane game;
	PlayManager manager = PlayManager.getInstance();
	
	public AbstractGamepadEvent(SinglePlayerPane game) {
		this.game = game;
	}

	@Override
	public void handle(Event event) {
		
	}

}
