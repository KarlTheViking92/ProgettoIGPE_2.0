package gui.multiplayerPanel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import core.gameManagers.MenuManager;
import core.gameManagers.MultiplayerManager;
import gui.panel.AbstractGamePage;
import gui.panel.singlePlayer.CustomListObject;
import gui.panel.singlePlayer.CustomListView;
import gui.panel.singlePlayer.CustomTextButton;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import network.Client;

public class MultiplayerHomePane extends AbstractGamePage {

	// private final int SERVER_MANAGER_PORT = 5832;
	private Rectangle2D screen = Screen.getPrimary().getBounds();
	private boolean disableEvents = false;
	private CustomListView matches;
	private CustomTextButton updateMatchList;
	private Client client;
	private boolean updated = false;
	private CustomTextButton joinGame, hostGame;
	private Map<String, Integer> ports = new HashMap<>();
	private HostServerPane hostServer;
	private MultiplayerManager manager = MultiplayerManager.getInstance();

	public MultiplayerHomePane() {
		/*
		 * client = new Client(); try { client.connectToServer("localhost",
		 * SERVER_MANAGER_PORT); client.start(); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */

		title.setText("Multiplayer");
		title.setLayoutX(screen.getWidth() * 0.5 - (title.getLayoutBounds().getWidth() / 2));
		setBackground("multiPlayer.jpg");
		background.setOpacity(0.7);
		matches = new CustomListView(screen.getWidth() * 0.4, screen.getHeight() * 0.5, screen.getWidth() * 0.05,
				screen.getHeight() * 0.3);
		updateMatchList = new CustomTextButton("update match list");
		updateMatchList.setLayoutX(
				matches.getLayoutX() + matches.getComponentWidth() * 0.5 - (updateMatchList.getWidth() / 2));
		updateMatchList.setLayoutY(matches.getLayoutY() + matches.getComponentHeight() + screen.getHeight() * 0.02);
		joinGame = new CustomTextButton("Join a game");
		hostGame = new CustomTextButton("Host a game");
		joinGame.setLayoutX(screen.getWidth() * 0.6);
		joinGame.setLayoutY(screen.getHeight() * 0.5);
		hostGame.setLayoutX(screen.getWidth() * 0.6);
		hostGame.setLayoutY(screen.getHeight() * 0.65);
		addButtonEvents();

		hostServer = new HostServerPane(screen.getWidth() * 0.5, screen.getHeight() * 0.5,
				screen.getWidth() * 0.5 - ((screen.getWidth() * 0.5) / 2),
				screen.getHeight() * 0.5 - ((screen.getHeight() * 0.5) / 2), this);
		this.getChildren().addAll(matches, title, joinGame, hostGame, updateMatchList);
	}

	private void addButtonEvents() {
		updateMatchList.setOnMouseReleased(e -> {
			if (!disableEvents) {
				System.out.println("aggiorna la lista delle partite online");
				// client.sendMessage("GET_MATCH_LIST");
				manager.sendMessage("GET_MATCH_LIST", true);
				updated = false;
			}
		});

		joinGame.setOnMouseReleased(e -> {
			if (!disableEvents && matches.getSelectedMap() != null) {
				System.out.println(matches.getSelectedMap());
				System.out.println(ports.get(matches.getSelectedMap()));
				manager.joinGame(ports.get(matches.getSelectedMap()));
				String message = "ADDPLAYER;"+manager.selector.getPlayerName()+";"+manager.selector.getPlayerType();
				manager.sendMessage(message,false);
				manager.sendMessage("WAIT;"+manager.selector.getPlayerName(), false);
				MenuManager.getInstance().nextPage();
			}
		});

		hostGame.setOnMouseReleased(e -> {
			if (!disableEvents) {
				this.getChildren().add(hostServer);
				disableEvents = true;
			}
		});
	}

	@Override
	public void update() {

		String message = manager.getMessage();
		if (message != null) {
			String [] in = message.split(":");
			System.out.println(in[0]);
			if(in[0].contains("SEND_MATCH_LIST")){
				System.out.println("forse funziona");
				matches.reset();
				ports.clear();
				String response = in[1];
				String token[] = response.split(";");
				for (int i = 0; i < token.length; i++) {
					System.out.println(token[i]);
					String[] tmp = token[i].split(" ");
					String text = "";
					text += "Creator: " + tmp[0] + " ";
					text += "Level: " + tmp[1] + " \n";
					text += "	Players: " + tmp[2] + "/" + tmp[3];
					ports.put(text, Integer.parseInt(tmp[4]));
					matches.addItem(new CustomListObject(matches.getComponentWidth() * 0.8,
							matches.getComponentHeight() * 0.15, text, matches));
				}
				updated = true;
			}
		}
	}

	public void hostGame(String creator, String map, int players, String path) {
		manager.hostGame(creator, map, players, path);
		this.hostServer.resetChoice();
	}

	public void restoreEvents() {
		disableEvents = false;
	}

}
