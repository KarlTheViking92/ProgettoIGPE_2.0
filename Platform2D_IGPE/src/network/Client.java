package network;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import core.gameManagers.MultiplayerManager;

public class Client extends Thread {

	private Socket socket;
	private DataOutputStream outToServer;
	private BufferedReader inFromServer;
	private boolean connected = false;
	private boolean wait = false;
	private String response = "";
	private MultiplayerManager manager = MultiplayerManager.getInstance();

	public Client() {
	}

	public void connectToServer(String server, int myport) throws UnknownHostException, IOException {

		socket = new Socket("localhost", myport);
		outToServer = new DataOutputStream(socket.getOutputStream());
		inFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		connected = true;
		sendMessage("sono collegato");

	}

	@Override
	public void run() {
		while (connected) {
			try {
				String message = inFromServer.readLine();
				String token[] = message.split(":");
				// System.out.println("client: " + token[0]);
				System.out.println("client message: " + message);
				if (message.contains("STARTGAME")) {
					manager.canStart = true;
				}
				if (token[0].contains("SEND_MATCH_LIST")) {
					// response = inFromServer.readLine();
					manager.depositMessage(message);
					wait = false;
				}
				if (message.equals("WAITING")) {
					response = inFromServer.readLine();
					wait = false;
				}
				if (message.contains("SEND_MAP")) {
					manager.depositMessage(message);
					wait = false;
				}
				if (message.contains("PLAYERSLIST")) {
					String[] t = token[1].split("/");
					for (int i = 0; i < t.length; i++) {
						String[] info = t[i].split(";");
						manager.addPlayer(info[0], info[1]);
					}
					sendMessage(MultiplayerManager.getInstance().selector.getPlayerName() + ";READY");
				}
				if (message.contains("MOVED")) {
					manager.doOperation(message);
				}
				if (message.contains("JUMP") && !message.contains("DOUBLEJUMP")) {
					manager.doOperation(message);
				}
				else if (message.contains("DOUBLEJUMP")) {
					manager.doOperation(message);
				}
				// if(token[0].equals("GAME_CREATED"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void sendMessage(String message) {
		if (connected) {
			System.out.println("scrivo al server " + message);
			try {
				outToServer.writeBytes(message + "\n");
				wait = true;
				// outToServer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void closeConnection() throws IOException {
		socket.close();
	}

	public String getResponse() {
		return response;
	}

	public boolean isWaiting() {
		return wait;
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		// Client c = new Client(ServerManager.getInstance());
		// c.connectToServer("localhost", 2002);
		// c.start();
		// c.sendMessage("gesu");
		// Client c1 = new Client(ServerManager.getInstance());
		// c1.connectToServer("localhost", 2002);
		// c1.start();
		// c.getResponse();

	}
}
