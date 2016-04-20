package mapEditor;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

public class EditorPane extends GridPane {

	private int row;
	private int column;
	private Rectangle[][] matrix;
	private DrawableObjectFactory factory;

	public EditorPane(int row, int column) {
		this.row = row;
		this.column = column;
		this.matrix = new Rectangle[row][column];
		this.setAlignment(Pos.CENTER);
		this.factory = new DrawableObjectFactory();
		// this.setPrefSize(800, 800);
		this.setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		this.setPadding(new Insets(20.0));
		this.setGridLinesVisible(true);

		drawGrid();
	}

	public void draw(int x, int y, int code) {
		System.out.println("path " + factory.getImageByCode(code));
		if ((x != 0 && y != 0) && (x != row - 1 && y != column - 1))
			matrix[x][y].setFill(new ImagePattern(new Image("file:" + factory.getImageByCode(code))));
	}

	public void erase(int x, int y) {
		if ((x != 0 && y != 0) && (x != row - 1 && y != column - 1))
			if (matrix[x][y].getFill() != Color.TRANSPARENT) {
				matrix[x][y].setFill(Color.TRANSPARENT);
			}
	}

	public void drawGrid() {

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				Rectangle rect = new Rectangle(i, j, 60, 60);
				if ((i != 0 && j != 0) && (i != row - 1 && j != column - 1)) {
					rect.setFill(Color.TRANSPARENT);
				} else {
					rect.setFill(Color.HOTPINK);
				}
				this.add(rect, j, i);
				this.matrix[i][j] = rect;
			}
		}
	}

}
