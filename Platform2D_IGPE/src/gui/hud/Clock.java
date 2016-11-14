package gui.hud;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class Clock extends Pane {

	private final Font FONT = Font.loadFont("file:resources/font/Engcomica.otf", 45);
	private ImageView clock = new ImageView();

	private Text time;

	private long startTime = 0;
	private long startPause = 0;
	private long pausedTime = 0;
	private long elapsed = 0;
	private long minutes = 0;
	private boolean pause = false;

	public Clock() {
		this.clock.setImage(new Image("file:resources/images/hud/clock.png"));
		this.setWidth(Screen.getPrimary().getBounds().getWidth() * 0.10);
		this.setHeight(Screen.getPrimary().getBounds().getHeight() * 0.26);
		this.clock.setFitWidth(this.getWidth());
		this.clock.setFitHeight(this.getHeight());
		startTime = System.currentTimeMillis();
		time = new Text(Integer.toString((int) (elapsed / 1000)));
		time.setFont(FONT);
		time.setFill(Color.web("#343434"));
		time.setLayoutX(this.getWidth() * 0.33 - (time.getLayoutBounds().getWidth() / 2));
		time.setLayoutY(this.getHeight() * 0.74);
		this.getChildren().addAll(clock, time);
	}

	public void pause() {
		startPause = System.currentTimeMillis();
		pause = true;
	}

	public void resume() {
		startTime += pausedTime;
		pause = false;
	}

	public void update() {
		if (!pause) {
			elapsed = System.currentTimeMillis() - (startTime);
			if ((elapsed / 1000) > 59) {
				minutes++;
				startTime = System.currentTimeMillis();
			}
			String min = new String();
			String sec = new String();
			if (minutes < 10)
				min = "0";
			if ((elapsed / 1000) < 10)
				sec = "0";
			min += Integer.toString((int) minutes);
			sec += Integer.toString((int) (elapsed / 1000));
			time.setText(min + ":" + sec);
		}else{
			pausedTime = System.currentTimeMillis() - startPause;
		}
	}
	
	public String getTime(){
		String time = Integer.toString((int)minutes);
		if(minutes < 1 )
			time += "0";
		time += "," + Integer.toString((int)elapsed/1000);
		return time;
	}

}
