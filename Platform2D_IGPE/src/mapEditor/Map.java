package mapEditor;

import java.util.ArrayList;
import java.util.List;

import gui.panel.UpdatablePane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
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
	private String cssFile = "file:resources/styleFiles/editorpane.css";
	private Rectangle2D screen = Screen.getPrimary().getBounds();
	private PopupError errorPopup = new PopupError((int) screen.getWidth() / 2, (int) screen.getHeight() / 2);
	private PopupName namePopup = new PopupName((int) screen.getWidth() / 2, (int) screen.getHeight() / 2);
	private String nameLevel = new String();

	public Map(int r, int c, Scene s) {
		this.right = new ScrollPane();
		this.getStylesheets().add(cssFile);
		this.setPrefWidth(screen.getWidth());
		this.setPrefHeight(screen.getHeight());
		this.listImageView = new ArrayList<>();
		this.left = new ChoicePane(listImageView, this, s);
		this.editor = new EditorPane(r, c, this);
		setConstraints();
		left.setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY, Insets.EMPTY)));
		right.setFitToWidth(true);
		right.setFitToHeight(true);
		StackPane center = new StackPane();
		center.setAlignment(Pos.TOP_CENTER);
		center.getChildren().add(editor);
		right.setId("rightpane");
		right.setContent(center);

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
		if (!editor.getChildren().contains(namePopup)) {
			if (editor.canSave()) {
				getNamePopup().setTranslateY((editor.getMaxHeight() / 2) - (getErrorPopup().getPrefHeight() / 2));
				getNamePopup().setTranslateX((editor.getMaxWidth() / 2) - (getErrorPopup().getPrefWidth() / 2));
				editor.getChildren().add(getNamePopup());
				eventDisabled = true;
			}
		} else {
			if (!editor.getChildren().contains(errorPopup)) {
				getErrorPopup().setTranslateY((editor.getMaxHeight() / 2) - (getErrorPopup().getPrefHeight() / 2));
				getErrorPopup().setTranslateX((editor.getMaxWidth() / 2) - (getErrorPopup().getPrefWidth() / 2));
				editor.getChildren().add(getErrorPopup());
				eventDisabled = true;
			}
		}
	}

	public void update() {
		if (getErrorPopup().isClicked()) {
			getErrorPopup().setExit(false);
			editor.getChildren().remove(getErrorPopup());
			eventDisabled = false;
		}
		if (getNamePopup().isSave()) {
			nameLevel = getNamePopup().getTextField().getText();
			getNamePopup().setSave(false);
			eventDisabled = false;
			if (nameLevel.length() > 0) {
				editor.saveMap("resources/levels/" + nameLevel, "resources/levels/" + nameLevel + "_color");
				getNamePopup().setSave(false);
				editor.getChildren().remove(getNamePopup());
				eventDisabled = false;
			}
		}
		if (getNamePopup().isCanc()) {
			getNamePopup().setCanc(false);
			editor.getChildren().remove(getNamePopup());
			eventDisabled = false;
		}
		editor.update();
	}

	public void clearEditor() {
		editor.clearBlocks();

	}

	public PopupError getErrorPopup() {
		return errorPopup;
	}

	public PopupName getNamePopup() {
		return namePopup;
	}

	public boolean isEventDisabled() {
		return eventDisabled;
	}

	public void setDisableEvent(boolean disableEvent) {
		this.eventDisabled = disableEvent;
	}

}
