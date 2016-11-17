package gui.panel.singlePlayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import core.element.Item;
import core.element.block.Block;
import core.element.character.Character;
import core.element.character.MeleeEnemy;
import core.element.character.Player;
import core.gameManagers.PlayManager;
import gui.ImageProvider;
import gui.drawer.Drawer;
import gui.drawer.MeleeEnemyDrawer;
import gui.drawer.PlayerDrawer;
import gui.drawer.RangedEnemyDrawer;
import gui.element.BulletPlayerGraphic;
import gui.element.GraphicBlockFactory;
import gui.element.GraphicElement;
import gui.element.GraphicGem;
import gui.event.KeyboardPressedEvent;
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

public class GameField extends SubScene {

	private static Pane root = new Pane();
	private Camera camera = new PerspectiveCamera();

	private PlayManager manager;
	private SinglePlayerPane gamePanel;
	private List<Drawer> drawers = new ArrayList<>();
	private List<GraphicElement> imgs = new ArrayList<>();
	private List<GraphicElement> bulletImgs = new ArrayList<>();
	private GraphicBlockFactory factory = new GraphicBlockFactory();
	private Group group = new Group();
	private Rectangle2D screen = Screen.getPrimary().getBounds();
	private Point2D widthLimit;
	private Point2D heightLimit;

	public GameField(SinglePlayerPane game, double w, double h) {
		super(root, w, h);
		manager = PlayManager.getInstance();
		gamePanel = game;
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
			if ((drawer instanceof PlayerDrawer) && manager.getPlayer().isRespawned()) {
				initCamera();
				manager.getPlayer().notRespawned();
			}
			drawer.draw();
		}
		drawBullets();
		updateBullet();
		removeBullets();
		removeEnemy();
	}

	private void drawBullets() {
		for (Item bullet : manager.getBullets()) {
			if (!bullet.isCreated()) {
				GraphicElement img = new BulletPlayerGraphic(bullet);
				bullet.create();
				bulletImgs.add(img);
				this.group.getChildren().add((Node) (img));
			}
		}
	}

	private void removeBullets() {
		Iterator i = bulletImgs.iterator();
		while (i.hasNext()) {
			GraphicElement bullet = (GraphicElement) i.next();
			if (bullet.destroy()) {
				i.remove();
				this.group.getChildren().remove(bullet);
			}
		}
	}

	public void removeEnemy() {
		Iterator i = drawers.iterator();
		while (i.hasNext()) {
			Drawer enemy = (Drawer) i.next();
			if (enemy.toDestroy()) {
				i.remove();
				this.group.getChildren().remove(enemy);
			}
		}
	}

	public void updateBullet() {
		for (GraphicElement i : bulletImgs) {
			i.update();
		}
	}

	public void drawWorld() {
		widthLimit = new Point2D(manager.getLevelWidth() * 0.24, manager.getLevelWidth() * 0.75);
		heightLimit = new Point2D(manager.getLevelHeight() * 0.15, manager.getLevelHeight() * 0.86);

		drawers.add(new PlayerDrawer(manager.getPlayer()));
		for (Character enemy : manager.getEnemies()) {
			if (enemy instanceof MeleeEnemy)
				drawers.add(new MeleeEnemyDrawer(enemy));
			else
				drawers.add(new RangedEnemyDrawer(enemy));
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
