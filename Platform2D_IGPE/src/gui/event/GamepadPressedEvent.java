package gui.event;

import core.element.character.Direction;
import gui.panel.singlePlayer.SinglePlayerPane;
import net.java.games.input.Event;

public class GamepadPressedEvent extends AbstractGamepadEvent {

	public GamepadPressedEvent(SinglePlayerPane game) {
		super(game);
	}

	@Override
	public void handle(Event event) {
		switch (event.getComponent().getName()) {
		case "Pulsante 0":
			if (manager.getPlayer().getCanShoot()
					&& System.currentTimeMillis() - manager.getPlayer().getLast() >= 200) {
				manager.getPlayer().shoot();
				manager.getPlayer().restartCanShoot();
			}
			break;
		case "Pulsante 1":
			manager.playerJump();
			break;
		case "Pulsante 2":
			break;
		case "Pulsante 3":
			break;
		case "Pulsante 4":
			break;
		case "Hat Switch":
			if (event.getComponent().getPollData() == 1.0)
				manager.movePlayer(Direction.LEFT);

			if (event.getComponent().getPollData() == 0.5)
				manager.movePlayer(Direction.RIGHT);
			break;

		case "Pulsante 9":
			if(manager.isPaused())
				manager.resume();
			else
				manager.pause();
			break;
		default:
			break;
		}
	}
}
