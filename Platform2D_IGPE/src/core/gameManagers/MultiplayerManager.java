package core.gameManagers;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.World.MultiplayerWorld;
import core.World.World;
import core.element.Item;
import core.element.Position;
import core.element.block.Block;
import core.element.character.Character;
import core.element.character.Direction;
import core.element.character.Player;
import game.GameSelector;
import network.Client;
import network.GameStatus;

public class MultiplayerManager implements GameManager{

	private static MultiplayerManager instance;
	private Client managerClient;
	private Client gameClient;
	private final int SERVER_MANAGER_PORT = 5832;
	private List<String> messages = new ArrayList<>();
	private String mapName, logicMap, coloredmap;
	private GameStatus actualStatus = GameStatus.DISCONNECTED;
	private String mapPath = "resources/Levels/temporary/";
	private String map = "";
	private boolean host = false;
	public GameSelector selector;
	
	public boolean canStart = false;
	
	private Player myPlayer;
	private List<Player> players = new ArrayList<>();
	private List<Character> enemies = new ArrayList<>();
	
	private boolean pause = false, finish = false;
	// world info
	private World world;
	
	private MultiplayerManager() {
	}

	public static MultiplayerManager getInstance() {
		if (instance == null)
			instance = new MultiplayerManager();
		return instance;
	}
	@Override
	public void init(GameSelector game) {
		
		selector = game;
		managerClient = new Client();
		try {
			managerClient.connectToServer("localhost", SERVER_MANAGER_PORT);
			managerClient.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void connect(String address, int port) {

	}

	public void disconnect() {
		managerClient.sendMessage("CLOSE_CONNECTION");
	}

	public String getMatchList() {
		managerClient.sendMessage("GET_MATCH_LIST");

		return null;
	}

	public void sendMessage(String message, boolean manager) {
		if(manager)
			managerClient.sendMessage(message);
		else
			gameClient.sendMessage(message);
	}

	public void hostGame(String creator, String map, int players, String mapPath) {
		convertMapToString(mapPath);
		managerClient.sendMessage("HOST_NEW_GAME");
		managerClient
				.sendMessage(creator + "/" + map + "/" + Integer.toString(players) + "/" + logicMap + "/" + coloredmap);
		host = true;
		mapName = map;
		actualStatus = GameStatus.CREATED_MATCH;
	}

	public void depositMessage(String message) {
		System.out.println("al manager giunge  " + message);
		messages.add(message);
	}

	public String getMessage() {

		String message = null;
		if (!messages.isEmpty()) {
			message = messages.get(0);
			messages.remove(message);
		}

		return message;
	}

	private void convertMapToString(String path) {
		try {
			logicMap = "";
			coloredmap = "";
			BufferedReader logicalPath = new BufferedReader(new FileReader(path));
			BufferedReader coloredPath = new BufferedReader(new FileReader(path + "_color"));
			int rows = Integer.parseInt(logicalPath.readLine());
			logicMap += Integer.toString(rows) + ";";
			int columns = Integer.parseInt(logicalPath.readLine());
			logicMap += Integer.toString(columns) + ";";
			// logicalMap = new int[rows][columns];
			// coloredMap = new String[rows][columns];
			for (int i = 0; i < rows; i++) {

				String logicalLine = logicalPath.readLine();
				logicMap += logicalLine + ";";
				String colorLine = coloredPath.readLine();
				coloredmap += colorLine + ";";
			}

			logicalPath.close();
			coloredPath.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void joinGame(int port) {
		gameClient = new Client();
		try {
			gameClient.connectToServer("localhost", port);
			actualStatus = GameStatus.CONNECTED;
			gameClient.start();
			System.out.println("join game " + host);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void update() {
		world.update();
		for (Player p : players) {
			p.update();
		}
		myPlayer.update();
	}

	public void loadMap() {
		if (!host) {
			gameClient.sendMessage("GET_MAP");
			while (gameClient.isWaiting())
				System.out.println("aspetto");
			String message = getMessage();
			String[] token = message.split("/");
			if (token[0].contains("SEND_MAP")) {
				mapName = token[1];
				logicMap = token[2];
				coloredmap = token[3];
			}
			writeTemporaryMap();
			map = mapPath + mapName;
		} else {
			map = "resources/Levels/customLevel/" + mapName;
		}
		
		world = new MultiplayerWorld(map);
		for (Player p : players) {
			p.setWorld(world);
		}
		myPlayer = new Player(selector.getPlayerName(), 10, 1, world);
		myPlayer.setType(selector.getPlayerType());
		world.initialize();
		enemies = world.getEnemies();
		
//		gameClient.sendMessage(myPlayer.getName() + ";READY");
		
	}

	private void writeTemporaryMap() {
		try {
			File file = new File(mapPath + mapName);
			File file_colored = new File(mapPath + mapName + "_color");

			// if file doesnt exists, then create it
			if (!file.exists() && !file_colored.exists()) {
				file.createNewFile();
				file_colored.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			FileWriter fw_color = new FileWriter(file_colored.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			BufferedWriter bw_color = new BufferedWriter(fw_color);
			String[] token = logicMap.split(";");
			String[] token_color = coloredmap.split(";");
			bw.write(token[0] + "\n");
			bw.write(token[1] + "\n");
			int rows = Integer.parseInt(token[0]);
//			int columns = Integer.parseInt(token[1]);
			// bw.write(content);
			for (int i = 0; i < rows; i++) {
				bw.write(token[i+2]+"\n");
			}
			for (int i = 0; i < rows; i++) {
				bw_color.write(token_color[i]+"\n");
			}
			bw.close();
			bw_color.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done");

	}
	
	public double getLevelWidth() {
		return world.getWidth();
	}

	public double getLevelHeight() {
		return world.getHeight();
	}
	public Player getPlayer() {
		return myPlayer; // temporaneamente null
		// return players;
	}

	public List<Character> getMeleeEnemy() {
		return enemies;
	}
	
	public Block[][] getBlocksMatrix() {
		return world.getMatrix();
	}

	public List<Item> getGemList() {
		return world.getGems();
	}
	
	public boolean isPaused(){
		return pause;
	}

	public boolean isFinished() {
		return finish;
	}

/*	@Override
	public void init(GameSelector game) {
		// TODO Auto-generated method stub
		
	}*/

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void restart() {
		
	}

	@Override
	public void finishLevel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void movePlayer(Direction direction) {
		myPlayer.setDirection(direction);
		gameClient.sendMessage(myPlayer.getName() + ";MOVED;" + direction);
		
	}

	@Override
	public void playerJump() {
		if (myPlayer.canJump() && myPlayer.canDoubleJump()) {
			myPlayer.jump();
			gameClient.sendMessage(myPlayer.getName() + ";JUMP");
		} else if (myPlayer.canDoubleJump()){
			myPlayer.doubleJump();
			gameClient.sendMessage(myPlayer.getName() + ";DOUBLEJUMP");
		}	
	}
	
	public void addPlayer(String name, String type){
		if(name.equals(selector.getPlayerName()))
			return;
		Player p = new Player(name, 5, 5, world);
		p.setType(type);
		players.add(p);
	}
	
	public void doOperation(String message){
		String [] token = message.split(";");
		if(message.contains(myPlayer.getName())){
			return;
		}
		Player p = null;
		for (Player pl : players) {
			if(pl.getName().equals(token[0]))
				p = pl;
		}
		if (message.contains("MOVED")) {
				if(p.getName().equals(token[0])){
					switch (token[2]) {
					case "RIGHT":
						p.setDirection(Direction.RIGHT);
						break;
					case "LEFT":
						p.setDirection(Direction.LEFT);
						break;
					case "STOP":
						p.setDirection(Direction.STOP);
						break;
					default:
						break;
					}
				}
		}
		if (message.contains("JUMP") && !message.contains("DOUBLEJUMP")) {
			p.jump();
		}
		else if (message.contains("DOUBLEJUMP")) {
			p.doubleJump();
		}
	}
	
	public List<Player> getPlayers(){
		return players;
	}
	
	public boolean checkAllReady(){
		return false;
	}

	public boolean canStart() {
		return canStart;
	}

}
