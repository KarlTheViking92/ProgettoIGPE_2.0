package core.gameManagers.states;

import java.util.List;
import java.util.Stack;

public class GameStateManager {

	private GameState actualState;
	
	// vedere se implementato meglio come stack di stati?
	
	//	private List<GameState> states; 
	
	
	public GameStateManager() {

		// prima inizializzazione menuState
		this.actualState = null;
//		states = new Stack<>();
	}
	
	public void changeState(GameState g){
		this.actualState = g;
	}
	
	public GameState getActualState() { return actualState; }
	
	public void update (){
		actualState.update();
	}
	
	public void draw(){
		actualState.draw();
	}
	
	
}
