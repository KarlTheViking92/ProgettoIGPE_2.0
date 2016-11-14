package gui.panel.singlePlayer;

import javafx.geometry.Rectangle2D;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;

public class CustomTextButton extends StackPane{
	private final Font FONT = Font.loadFont("file:resources/font/Engcomica.otf", 60);
	private final static Color STROKE_COLOR = Color.web("#ebd913");
	private final static Color BACKGROUND_COLOR = Color.web("#d58114");
	private Rectangle2D screen = Screen.getPrimary().getBounds();
	private Text message;

	private Rectangle background;
	
	public CustomTextButton(String s) {
		background = new Rectangle(screen.getWidth()*0.3, screen.getHeight()*0.07);
		
		setWidth(screen.getWidth()*0.3);
		setHeight(screen.getHeight()*0.07);
		
		setLayoutX(screen.getWidth()*0.5 - (getWidth()/2));
		message = new Text(s);
		message.setFont(FONT);
		this.background.setOpacity(0.8);
		this.background.setArcHeight(20);
		this.background.setArcWidth(20);
		this.background.setStroke(STROKE_COLOR);
		this.background.setStrokeWidth(5);
		this.background.setFill(BACKGROUND_COLOR);
		
//		this.setFill(Color.BLUE);
		
		this.setOnMouseEntered(e -> {
			this.background.setFill(STROKE_COLOR);
		});
		this.setOnMouseExited(e -> {
				this.background.setFill(BACKGROUND_COLOR);
		});
		
		this.getChildren().addAll(background , message);
	}

}
