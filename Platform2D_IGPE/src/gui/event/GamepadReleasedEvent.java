package gui.event;

import core.element.character.Direction;
import gui.panel.singlePlayer.SinglePlayerPane;
import net.java.games.input.Event;

public class GamepadReleasedEvent extends AbstractGamepadEvent{

	public GamepadReleasedEvent(SinglePlayerPane game) {
		super(game);
	}

	@Override
	public void handle(Event event) {
		switch(event.getComponent().getName()){
		case "Pulsante 0":
			manager.getPlayer().noShooting();
			break;
		case "Pulsante 1":
			System.out.println("hai premuto " + event.getComponent().getName());
			break;
		case "Pulsante 2":
			System.out.println("hai premuto " + event.getComponent().getName());
			break;
		case "Pulsante 3":
			System.out.println("hai premuto " + event.getComponent().getName());
			break;
		case "Pulsante 4":
			System.out.println("hai premuto " + event.getComponent().getName());
			break;
		case "Hat Switch":
			System.out.println("case hat switch released " + event.getComponent().getPollData());
//			if(event.getComponent().getPollData() == 1.0)
				manager.movePlayer(Direction.STOP);

//			if(event.getComponent().getPollData() == 0.5)
//				manager.movePlayer(Direction.STOP);
			break;
		default:
			break;
		}
	}
}
