package mapEditor;

import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import gui.ImageProvider;
import gui.panel.UpdatablePane;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class EditorPane extends Pane implements UpdatablePane {

	private Map map;
	private int row;
	private int column;
	private AbstractObject choice = null;
	private ImageView[][] matrix;
	private Group blocks = new Group();
	private int[][] logicMatrix;
	private String[][] colorMatrix;
	private List<String> editorPaths = new ArrayList<>();
	private boolean enableEvent = false;

	// constraints
	private boolean endingLevelCheck = false;
	private boolean spawnLevelCheck = false;
	private boolean gemsCheck = false;

	private int gemsCounter = 0;

	private ImageView currentBlock;
	private Rectangle shadow = new Rectangle();

	public EditorPane(int row, int column, Map map) {
		this.currentBlock = new ImageView();
		this.currentBlock.setFitHeight(65);
		this.currentBlock.setFitWidth(65);
		this.editorPaths = ImageProvider.getInstance().getEditorPaths();
		currentBlock.setVisible(false);
		this.row = row;
		this.column = column;
		this.map = map;
		this.setId("editor");
		this.matrix = new ImageView[row][column];
		this.logicMatrix = new int[row][column];
		this.colorMatrix = new String[row][column];
		this.choice = new AbstractObject(5, ImageProvider.getInstance().getSimpleBlock1("C4_rock"));
		this.shadow.setFill(Color.BEIGE);
		this.shadow.setOpacity(0.2);

		this.setMinWidth(column * 50);
		this.setMinHeight(row * 50);
		this.setMaxWidth(column * 50);
		this.setMaxHeight(row * 50);

		shadow.setWidth(this.getMaxWidth() + 15);
		shadow.setHeight(this.getMaxHeight() + 15);
		blocks.getChildren().add(shadow);
		this.getChildren().addAll(blocks);
		blocks.getChildren().add(currentBlock);
		addEvents();
		drawGrid();
		adjustGraphic();
	}

	public void drawGrid() {
		for (int y = row - 1; y >= 0; y--) {
			for (int x = 0; x < column; x++) {
				if ((x == 0 || x == column - 1) || ((y == 0 || y == row - 1))) {
					drawImage(x, y, choice);
					colorMatrix[y][x] = "C4_rock";
				}
			}
		}
	}

	private boolean waterCheck(int x, int y, ImageView code) {

		AbstractObject block = (AbstractObject) code;

		if ((block.getCode() == 9) && endingLevelCheck)
			return false;

		if ((block.getCode() == 15) && spawnLevelCheck)
			return false;

		if (block.getCode() == 3 && (logicMatrix[x + 1][y] == 9 || logicMatrix[x + 1][y] == 15))
			return false;

		if ((block.getCode() == 15 || block.getCode() == 9)
				&& (logicMatrix[x + 1][y] == 16 || logicMatrix[x + 1][y] == 17))
			return false;

		if ((block.getCode() == 16 || ((AbstractObject) code).getCode() == 17)
				&& (logicMatrix[x + 1][y] == 9 || logicMatrix[x + 1][y] == 15))
			return false;

		if (block.getCode() == 15 && logicMatrix[x - 1][y] != 0)
			return false;

		if (block.getCode() == 9 && logicMatrix[x - 1][y] != 0)
			return false;

		System.out.println("x " + (x + 1) + " y " + y);
		if (block.getCode() > 5 && logicMatrix[x + 1][y] == 9) {
			return false;
		}

		if (block.getCode() == 15 && logicMatrix[x + 1][y] == 7)
			return false;

		if (block.getCode() == 7
				&& (logicMatrix[x + 1][y] == 7 || logicMatrix[x - 1][y] == 7 || logicMatrix[x - 1][y] == 15
						|| logicMatrix[x + 1][y] == 15 || logicMatrix[x + 1][y] == 16 || logicMatrix[x + 1][y] == 17))
			return false;

		if ((block.getCode() == 7 || block.getCode() == 15) && logicMatrix[x - 1][y] != 0)
			return false;

		if (block.getCode() == 7 && (y == row - 2 || y == 1 || x == 1))
			return false;

		if ((block.getCode() == 5) && (logicMatrix[x + 1][y] == 7 || logicMatrix[x + 1][y] == 15))
			return false;

		if ((((block.getCode() == 6 || block.getCode() == 10 || block.getCode() == 14) && block.getCode() != 0))
				&& logicMatrix[x - 1][y] == 7)
			return false;
		return true;
	}

	private void constraintCheck(int x, int y) {
		if (logicMatrix[x][y] == 9)
			endingLevelCheck = true;

		if (logicMatrix[x][y] == 15)
			spawnLevelCheck = true;

		if (gemsCounter >= 3)
			gemsCheck = true;

		if (logicMatrix[x][y] == 3) {
			gemsCounter++;
		}
	}

	public boolean canSave() {

		if (endingLevelCheck && spawnLevelCheck && gemsCheck)
			return true;
		return false;

	}

	private void drawImage(int x, int y, ImageView code) {
		if (checkMatrix(x, y)) {
			if (matrix[y][x] == null) {
				if (x != column - 1 && y != row - 1)
					if (!waterCheck(y, x, code))
						return;
				ImageView rect = new ImageView();
				rect.setImage(code.getImage());
				rect.setFitHeight(65);
				rect.setFitWidth(65);
				rect.setLayoutX(x * 50);
				rect.setLayoutY(y * 50);
				matrix[y][x] = rect;
				logicMatrix[y][x] = ((AbstractObject) code).getCode();
				colorMatrix[y][x] = ((AbstractObject) code).getColor();
				constraintCheck(y, x);
				this.blocks.getChildren().add(rect);
			}
		}
	}

	private void adjustGraphic() {
		for (int y = row - 1; y >= 0; y--) {
			for (int x = 0; x < column; x++) {
				if (matrix[y][x] != null)
					matrix[y][x].toFront();
				else if ((int) currentBlock.getLayoutX() / 50 == x && (int) currentBlock.getLayoutY() / 50 == y) {
					currentBlock.toFront();
				}
			}
		}
		shadow.toFront();

	}

	private void erase(int x, int y) {
		if (checkMatrix(x, y) && matrix[y][x] != null) {
			if ((x < column - 1 && y < row - 1) && (x > 0 && y > 0)) {
				if (logicMatrix[y][x] == 9) {
					endingLevelCheck = false;
					this.blocks.getChildren().remove(matrix[y][x]);
					matrix[y][x] = null;
					logicMatrix[y][x] = 0;
					colorMatrix[y][x] = null;
				}
				if (logicMatrix[y][x] == 15) {
					spawnLevelCheck = false;
					this.blocks.getChildren().remove(matrix[y][x]);
					matrix[y][x] = null;
					logicMatrix[y][x] = 0;
					colorMatrix[y][x] = null;
				}
				this.blocks.getChildren().remove(matrix[y][x]);
				matrix[y][x] = null;
				logicMatrix[y][x] = 0;
				colorMatrix[y][x] = null;
			}
		}
	}

	private boolean checkMatrix(int x, int y) {
		return ((x < column && y < row) && (x >= 0 && y >= 0));
	}

	protected void clearBlocks() {
		for (int y = row - 1; y >= 0; y--) {
			for (int x = 0; x < column; x++) {
				if (matrix[y][x] != null && !((x == 0 || x == column - 1) || ((y == 0 || y == row - 1)))) {
					blocks.getChildren().remove(matrix[y][x]);
					spawnLevelCheck = false;
					endingLevelCheck = false;
					matrix[y][x] = null;
					logicMatrix[y][x] = 0;
					colorMatrix[y][x] = null;
				}
			}
		}
	}

	private void addEvents() {
		this.setOnMouseMoved(e -> {
			if (isEnabled()) {
				int x = (int) e.getX() / 50;
				int y = (int) e.getY() / 50;

				if (matrix.length <= y || matrix[0].length <= x || matrix[y][x] != null) {
					currentBlock.setVisible(false);
					((Node) e.getSource()).setCursor(Cursor.DEFAULT);
				} else {
					if (map.getChoice() != null) {
						currentBlock.setImage(this.map.getChoice().getImage());
						currentBlock.setVisible(true);
						((Node) e.getSource()).setCursor(Cursor.DISAPPEAR);
						currentBlock.setLayoutX(x * 50);
						currentBlock.setLayoutY(y * 50);
						currentBlock.toFront();
					}
				}
			}
		});

		this.setOnMouseClicked(e -> {
			if (isEnabled()) {
				currentBlock.setVisible(false);
				int x = (int) e.getX() / 50;
				int y = (int) e.getY() / 50;
				if (e.getButton() == MouseButton.PRIMARY) {
					AbstractObject choiceObj = map.getChoice();
					if (choiceObj != null) {
						drawImage(x, y, choiceObj);
					}
				} else if (e.getButton() == MouseButton.SECONDARY) {
					erase(x, y);
				}
				adjustGraphic();
			}
		});

		this.setOnMouseDragged(e -> {
			if (isEnabled()) {
				currentBlock.setVisible(false);
				int x = (int) e.getX() / 50;
				int y = (int) e.getY() / 50;
				if (e.getButton() == MouseButton.PRIMARY) {
					AbstractObject choiceObj = map.getChoice();
					if (choiceObj != null)
						drawImage(x, y, choiceObj);
				} else if (e.getButton() == MouseButton.SECONDARY) {
					erase(x, y);
				}
			}
		});
	}

	@Override
	public void update() {
		if (map.isEventDisabled()) {
			enableEvent = false;
		} else
			enableEvent = true;
	}

	public boolean isEnabled() {
		return enableEvent;
	}

	public int[][] getLogicMatrix() {
		return logicMatrix;
	}

	public String[][] getColorMatrix() {
		return colorMatrix;
	}
}