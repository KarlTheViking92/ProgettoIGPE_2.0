package mapEditor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;

@SuppressWarnings("static-access")
public class MapEditor extends GridPane {

	// matrix objects
	private int row, column;

	private int[][] matrix;

	private List<AbstractObject> listVbox;

	

	// graphic object

	private AnchorPane leftPane;
	private AnchorPane rightPane;

	private ChoiceObject choiceObject;

	private ScrollPane scrollEditor;
	private EditorPane editorPane;

	public boolean save = false;

	public MapEditor(int row, int column) {

		this.row = row;
		this.column = column;
		this.matrix = new int[row][column];

		// this.getChildren().add(gridPane);
		this.listVbox = new ArrayList<>();
		this.editorPane = new EditorPane(row, column);
		this.choiceObject = new ChoiceObject(listVbox, this);

		setChoicePane();
		setEditorPane();

		this.add(leftPane, 0, 0);
		// this.add(editorPane, 1, 0);
		this.add(rightPane, 1, 0);
		setListener();

	}

	private void setEditorPane() {
		this.rightPane = new AnchorPane();
		this.scrollEditor = new ScrollPane(editorPane);
		this.scrollEditor.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.scrollEditor.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.scrollEditor.setPannable(true);
		rightPane.setTopAnchor(scrollEditor, 0.0);
		rightPane.setLeftAnchor(scrollEditor, 0.0);
		rightPane.setRightAnchor(scrollEditor, 0.0);
		rightPane.setBottomAnchor(scrollEditor, 0.0);
		scrollEditor.fitToHeightProperty().set(true);
		scrollEditor.fitToWidthProperty().set(true);
		this.rightPane.getChildren().add(scrollEditor);

	}

	private void setChoicePane() {

		// set anchorPane
		this.leftPane = new AnchorPane();
		leftPane.setTopAnchor(choiceObject, 0.0);
		leftPane.setLeftAnchor(choiceObject, 0.0);
		leftPane.setRightAnchor(choiceObject, 0.0);
		leftPane.setBottomAnchor(choiceObject, 0.0);
		leftPane.getChildren().add(choiceObject);

		Button saveButton = new Button("Save");
		this.leftPane.setBottomAnchor(saveButton, 0.0);
		leftPane.setLeftAnchor(saveButton, 0.0);
		leftPane.setRightAnchor(saveButton, 0.0);
		leftPane.getChildren().add(saveButton);
		System.out.println(choiceObject.getChildren().size());
		saveButton.setLayoutX(20);
		saveButton.setLayoutY(600);
		saveButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				save = true;
				writeFile("map.txt");
				System.out.println("Clicco " + save);

			}
		});

		this.setGridLinesVisible(true);
		setPercentage();
	}

	private void setListener() {
		editorPane.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				if (event.getTarget() instanceof Rectangle) {

					Rectangle r = (Rectangle) event.getTarget();

					if (event.getButton() == MouseButton.SECONDARY) {
						// remove rectangle
						System.out.println("tasto destro");
						editorPane.erase((int) r.getX(), (int) r.getY());
						savePosition((int) r.getX(), (int) r.getY(), 0);

					} else {

						System.out.println(event.getTarget());

						System.out.println(r.getX() + " " + r.getY());
						editorPane.draw((int) r.getX(), (int) r.getY(), choiceObject.getChoice());
						savePosition((int) r.getX(), (int) r.getY(), choiceObject.getChoice());
						System.out.println("Codice selezionato " + choiceObject.getChoice());

					}
				}
			}
		});
	}

	private void setPercentage() {
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(30);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(70);
		this.getColumnConstraints().addAll(column1, column2);

		RowConstraints row = new RowConstraints();
		row.setPercentHeight(100);
		this.getRowConstraints().add(row);

	}

	public void savePosition(int x, int y, int code) {
		matrix[x][y] = code;
	}

	public void drawMatrix() {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void writeFile(String fileName) {
		try {

			FileWriter map = new FileWriter(fileName);
			BufferedWriter out = new BufferedWriter(map);
			out.write(Integer.toString(matrix.length) + "\n");
			out.write(Integer.toString(matrix[0].length) + "\n");
			for (int i = 0; i < row; i++) {
				for (int j = 0; j < column; j++) {
					out.write(Integer.toString(matrix[i][j]) + " ");
				}
				out.write("\n");
			}
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void addObject(Pane p) {
		if (!this.getChildren().contains(p))
			this.getChildren().add(p);
	}

}
