package core.gameManagers;

import javafx.scene.input.KeyCode;

public abstract class GameState {

	private GameStateManager manager;

	public GameState(GameStateManager g) {
		manager = g;
	}

	public abstract void init();

	public abstract void update();

	public abstract void draw();

	public abstract void keyPressed(KeyCode k);

	public abstract void keyReleased(KeyCode k);

}
