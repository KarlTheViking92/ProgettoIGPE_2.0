package gui.event;

import core.element.character.Direction;
import core.gameManagers.PlayManager;
import gui.panel.singlePlayer.SinglePlayerPane;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardReleasedEvent implements EventHandler<KeyEvent>{

	
	private PlayManager manager; 
	private SinglePlayerPane gamePane;
	
	public KeyboardReleasedEvent(SinglePlayerPane p) {
		gamePane = p;
		manager = PlayManager.getInstance();
	}
	
	@Override
	public void handle(KeyEvent event) {
		// TODO Auto-generated method stub
		
		KeyCode keyReleased = event.getCode();
		
		switch (keyReleased) {
		case RIGHT:
			manager.movePlayer(Direction.STOP);
			break;
		case LEFT:
			manager.movePlayer(Direction.STOP);			
			break;
		case SPACE:
//			manager.movePlayer(Direction.STOP);
			break;
			
		default:
			break;
		}
	}

}
