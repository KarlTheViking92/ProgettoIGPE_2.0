package core.gameManagers;

import javafx.scene.Scene;

public class GameManager {

	GameStateManager gsm;
	Scene gameScene;
	GameLoop loop;
	

	public GameManager(Scene gameScene) {
		this.gameScene = gameScene;
		this.loop = new GameLoop(this);
		// TODO Auto-generated constructor stub
	}
	
	
	public void beginLoop(){
		loop.start();
	}

	
	
}
