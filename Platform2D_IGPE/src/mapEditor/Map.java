package mapEditor;

import java.util.ArrayList;
import java.util.List;

import gui.panel.UpdatablePane;
import gui.popup.Popup;
import gui.popup.PopupError;
import gui.popup.SavemapPopup;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import mapEditor.AbstractObject;

public class Map extends GridPane implements UpdatablePane {

	private List<AbstractObject> listImageView;
	private ChoicePane left;
	private ScrollPane right;
	private EditorPane editor;
	private boolean eventDisabled = false;
	private Pane test = new Pane();
	private String cssFile = "file:resources/styleFiles/editorpane.css";
	private Rectangle2D screen = Screen.getPrimary().getBounds();
	private Popup errorPopup;
	private Popup savemapPopup;
	private String nameLevel;
	StackPane center = new StackPane();

	public Map(int r, int c, Scene s) {
		this.right = new ScrollPane();
		this.getStylesheets().add(cssFile);
		this.setPrefWidth(screen.getWidth());
		this.setPrefHeight(screen.getHeight());
		this.listImageView = new ArrayList<>();
		this.left = new ChoicePane(listImageView, this, s);
		this.editor = new EditorPane(r, c, this);
		setConstraints();
		right.setFitToWidth(true);
		right.setFitToHeight(true);

		center.setAlignment(Pos.CENTER);
		center.getChildren().add(editor);
		right.setId("rightpane");
		right.setContent(center);

		// %%%%%%%%%%%%%%%%%%%%%%%%%
		test.setPrefWidth(screen.getWidth() * 0.9);
		test.setPrefHeight(screen.getHeight());
		// test.setLayoutX(0);
		// test.setLayoutY(0);
		// test.setBackground(new Background(new BackgroundFill(Color.RED,
		// CornerRadii.EMPTY, Insets.EMPTY)));
		// System.out.println("test " + test.getPrefWidth()*0.5 + " " +
		// test.getPrefHeight()*0.5);
		errorPopup = new PopupError(screen.getWidth() * 0.4, screen.getHeight() * 0.4);
		errorPopup.setPosition(test.getPrefWidth() * 0.5, test.getPrefHeight() * 0.5);
		savemapPopup = new SavemapPopup(screen.getWidth() * 0.4, screen.getHeight() * 0.4);
		savemapPopup.setPosition(test.getPrefWidth() * 0.5, test.getPrefHeight() * 0.5);
		// %%%%%%%%%%%%%%%%%%%%%%%%%

		this.add(left, 0, 0);
		this.add(right, 1, 0);
		// auto scrollpane event
		this.right.setOnMouseMoved(e -> {
			if (editor.isEnabled()) {

				if (right.getWidth() - e.getX() < 200.0) {
					double hval = right.getHvalue();
					hval += 0.012;
					right.setHvalue(hval);
				}

				if (right.getWidth() - (right.getWidth() - e.getX()) < 200.0) {
					double hval = right.getHvalue();
					hval -= 0.012;
					right.setHvalue(hval);
				}

				if (right.getHeight() - e.getY() < 200.0) {
					double vval = right.getVvalue();
					vval += 0.008;
					right.setVvalue(vval);
				}

				if (right.getHeight() - (right.getHeight() - e.getY()) < 200.0) {
					double vval = right.getVvalue();
					vval -= 0.008;
					right.setVvalue(vval);
				}
			}
		});
	}

	private void setConstraints() {
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setPercentWidth(10);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setPercentWidth(90);
		this.getColumnConstraints().addAll(column1, column2);

		RowConstraints row = new RowConstraints();
		row.setPercentHeight(100);
		this.getRowConstraints().add(row);
	}

	public void addObject(Pane p) {
		if (!this.getChildren().contains(p))
			this.getChildren().add(p);
	}

	protected AbstractObject getChoice() {
		return left.getChoice();
	}

	public void saveMap() {
		// System.out.println("posizioni " + (screen.getWidth() / 2) + " " +
		// (screen.getHeight() / 2));
		// double x = (editor.getWidth() * 0.5 - left.getWidth());
		// double y = (editor.getHeight() * 0.5);
		if (editor.canSave()) {
			if (!test.getChildren().contains(savemapPopup)) {
				// savemapPopup.setPosition(x, y);
				test.getChildren().add((Node) savemapPopup);
				right.setHvalue(0);
				right.setVvalue(0);
			}
			center.getChildren().add(test);
			eventDisabled = true;
		} else {
			/*
			 * if (!editor.getChildren().contains(errorPopup)) { //
			 * getErrorPopup().setLayoutX((editor.getMaxWidth() / 2) - //
			 * (getErrorPopup().getPrefWidth() / 2) - left.getPrefWidth()); //
			 * getErrorPopup().setLayoutY((editor.getMaxHeight() / 2) - //
			 * (getErrorPopup().getPrefHeight() / 2)); errorPopup.setPosition(x,
			 * y); editor.getChildren().add((Node) errorPopup); eventDisabled =
			 * true; }
			 */
			eventDisabled = true;
			if (!test.getChildren().contains(errorPopup)) {
				test.getChildren().add((Node) errorPopup);
				right.setHvalue(0);
				right.setVvalue(0);
			}
			center.getChildren().add(test);
		}
	}

	public void update() {
		if (errorPopup.isDeleted()) {
			center.getChildren().remove(errorPopup);
			errorPopup.restart();
			eventDisabled = false;
			test.getChildren().clear();
			center.getChildren().remove(test);
		}
		if (savemapPopup.isDeleted()) {
			center.getChildren().remove(savemapPopup);
			savemapPopup.restart();
			eventDisabled = false;
			test.getChildren().clear();
			center.getChildren().remove(test);
		}
		editor.update();
	}

	public void clearEditor() {
		editor.clearBlocks();

	}

	public boolean isEventDisabled() {
		return eventDisabled;
	}

	public void setDisableEvent(boolean disableEvent) {
		this.eventDisabled = disableEvent;
	}

	public int[][] getLogicMatrix() {
		return editor.getLogicMatrix();
	}

	public String[][] getColorMatrix() {
		return editor.getColorMatrix();
	}

}
