package gui.event;

import gui.panel.singlePlayer.SinglePlayerPane;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;
import net.java.games.input.Event;
import net.java.games.input.EventQueue;

public class Gamepad {

	Controller controller;
	GamepadPressedEvent pressedHandler;
	GamepadReleasedEvent releasedHandler;

	public Gamepad(SinglePlayerPane game) {
		Controller[] list = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (int i = 0; i < list.length; i++) {
			if (list[i].getType() == Controller.Type.GAMEPAD) {
				controller = list[i];
				pressedHandler = new GamepadPressedEvent(game);
				releasedHandler = new GamepadReleasedEvent(game);
				break;
			}
		}
	}

	public void update() {
		controller.poll();
		EventQueue queue = controller.getEventQueue();
		Event event = new Event();
		while (queue.getNextEvent(event)) {
			if (event.getComponent().getPollData() == 0.0) {
				releasedHandler.handle(event);
			} else {
				pressedHandler.handle(event);
			}
		}
	}

}
