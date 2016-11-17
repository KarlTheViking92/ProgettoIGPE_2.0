package gui.panel.finishPanel;

import gui.panel.AbstractCustomComponent;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class DataContainer extends AbstractCustomComponent {

	private final Font font = Font.loadFont("file:resources/font/Engcomica.otf",
			Screen.getPrimary().getBounds().getWidth() * 0.032);

	private String playerName;
	private String finalTime;
	private int gemCollected;

	private Text player = new Text(), time = new Text(), gems = new Text();
	private VBox board = new VBox(20);

	public DataContainer(double width, double height, double x, double y) {
		super(width, height, x, y);
		this.setComponentBackground("finishPanel/finishTest");
		addItem(board);
		board.getChildren().addAll(player, time, gems);
	}

	public void loadInfo(MatchInfo info) {
		playerName = info.getPlayer();
		finalTime = info.getFinalTime();
		gemCollected = info.getCollectedGems();

		board.setPrefWidth(this.getWidth() * 0.5);
		board.setPrefHeight(this.getHeight() * 0.5);
		board.setLayoutX(this.getWidth() * 0.08);
		board.setLayoutY(this.getHeight() * 0.18);
		board.setAlignment(Pos.CENTER);

		player.setText("Player Name : " + playerName);
		player.setFont(font);
		time.setText("time : " + finalTime);
		time.setFont(font);
		gems.setText("gem collected : " + gemCollected);
		gems.setFont(font);

	}

}
