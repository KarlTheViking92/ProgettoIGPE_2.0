package gui.event;

import core.element.character.Direction;
import gui.panel.singlePlayer.SinglePlayerPane;
import net.java.games.input.Event;

public class GamepadPressedEvent extends AbstractGamepadEvent{
	
	public GamepadPressedEvent(SinglePlayerPane game) {
		super(game);
	}
	
	@Override
	public void handle(Event event) {
		switch(event.getComponent().getName()){
		case "Pulsante 1":
			System.out.println("case 1 hai premuto " + event.getComponent().getName());
			manager.playerJump();
			break;
		case "Pulsante 2":
			System.out.println("case 2 hai premuto " + event.getComponent().getName());
			break;
		case "Pulsante 3":
			System.out.println("case 3 hai premuto " + event.getComponent().getName());
			break;
		case "Pulsante 4":
			System.out.println("case 1 hai premuto " + event.getComponent().getName());
			break;
		case "Hat Switch":
			System.out.println("case hat switch " + event.getComponent().getPollData());
			if(event.getComponent().getPollData() == 1.0)
				manager.movePlayer(Direction.LEFT);

			if(event.getComponent().getPollData() == 0.5)
				manager.movePlayer(Direction.RIGHT);
			break;
			
		case "Pulsante 9":
			manager.pause();
			break;
		default:
			break;
		}
	}
}
