package gui.multiplayerPanel;

import java.util.ArrayList;
import java.util.List;

import core.element.Item;
import core.element.block.Block;
import core.element.character.Character;
import core.element.character.Player;
import core.gameManagers.MultiplayerManager;
import core.gameManagers.PlayManager;
import gui.drawer.Drawer;
import gui.drawer.MeleeEnemyDrawer;
import gui.drawer.PlayerDrawer;
import gui.element.GraphicBlockFactory;
import gui.element.GraphicElement;
import gui.element.GraphicGem;
import gui.panel.singlePlayer.SinglePlayerPane;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Screen;

public class GameFieldMultiplayer extends SubScene {

	private static Pane root = new Pane();
	private Camera camera = new PerspectiveCamera();

	private MultiplayerManager manager = MultiplayerManager.getInstance();
	private MultiplayerPane game;

	private List<Drawer> drawers = new ArrayList<>();
	private List<GraphicElement> imgs = new ArrayList<>();
	private GraphicBlockFactory factory = new GraphicBlockFactory();
	private Group group = new Group();
	private Rectangle2D screen = Screen.getPrimary().getBounds();
	private Point2D widthLimit;
	private Point2D heightLimit;

	public GameFieldMultiplayer(MultiplayerPane game, double w, double h) {
		super(root, w, h);
		this.game = game;
		this.camera.setScaleX(0.5);
		this.camera.setScaleY(0.5);

		this.setCamera(camera);

		root.setStyle("-fx-background: null; -fx-background-color: null; ");
		root.getChildren().add(group);
	}

	private void initCamera() {
		double x = manager.getPlayer().getX();
		double y = manager.getPlayer().getY();

		if ((x > widthLimit.getX() && x < widthLimit.getY()) && (y > heightLimit.getX() && y < heightLimit.getY())) {
			this.camera.setTranslateX(manager.getPlayer().getX() - (screen.getWidth() / 4));
			this.camera.setTranslateY(manager.getPlayer().getY() - (screen.getHeight() / 4));
		}

		if (x < widthLimit.getX()) {
			camera.setTranslateX(widthLimit.getX() - (screen.getWidth() / 4));
		} else if (x > widthLimit.getY()) {
			camera.setTranslateX(widthLimit.getY() - (screen.getWidth() / 4));
		}

		if (y < heightLimit.getX()) {
			camera.setTranslateY(heightLimit.getX() - (screen.getHeight() / 4));
		} else if (y > heightLimit.getY()) {
			camera.setTranslateY(heightLimit.getY() - (screen.getHeight() / 4));
		}
	}

	public void update() {
		updateCamera();
		updateBlocks();
		for (Drawer drawer : drawers) {
			drawer.draw();
		}
	}

	public void drawWorld() {
		widthLimit = new Point2D(manager.getLevelWidth() * 0.24, manager.getLevelWidth() * 0.75);
		heightLimit = new Point2D(manager.getLevelHeight() * 0.15, manager.getLevelHeight() * 0.86);

		drawers.add(new PlayerDrawer(manager.getPlayer()));
		for (Player p : manager.getPlayers()) {
			if (!p.getName().equals(manager.getPlayer().getName()))
				drawers.add(new PlayerDrawer(p));
		}
		for (Character meleeEnemy : manager.getMeleeEnemy()) {
			drawers.add(new MeleeEnemyDrawer(meleeEnemy));
		}
		initCamera();

		for (int y = manager.getBlocksMatrix().length - 1; y >= 0; y--) {
			for (int x = 0; x < manager.getBlocksMatrix()[y].length; x++) {
				Block b = manager.getBlocksMatrix()[y][x];
				if (b != null) {

					GraphicElement img = factory.makeBlock(b);
					imgs.add(img);
					this.group.getChildren().add((ImageView) img);
				}
			}
		}
		for (Item gem : manager.getGemList()) {
			GraphicElement img = new GraphicGem(gem);
			this.imgs.add(img);
			this.group.getChildren().add((ImageView) img);
		}

		for (Drawer drawer : drawers) {
			this.group.getChildren().add((Node) drawer);
		}

	}

	private void updateBlocks() {
		for (GraphicElement block : imgs) {
			block.update();
		}
	}

	private void updateCamera() {
		double x = manager.getPlayer().getX();
		double y = manager.getPlayer().getY();
		if (x > widthLimit.getX() && x < widthLimit.getY()) {
			this.camera.setTranslateX(manager.getPlayer().getX() - (screen.getWidth() / 4));
		}
		if (y > heightLimit.getX() && y < heightLimit.getY()) {
			this.camera.setTranslateY(manager.getPlayer().getY() - (screen.getHeight() / 4));
		}
	}

	public void clearSubscene() {
		this.group.getChildren().clear();
		this.drawers.clear();
		this.imgs.clear();
	}

}
