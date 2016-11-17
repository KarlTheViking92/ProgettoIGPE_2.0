package gui.element;

import java.lang.reflect.InvocationTargetException;

import core.element.block.Block;

public class GraphicBlockFactory {

	public GraphicElement makeBlock(Block b) {

		try {
			Class block = Class.forName("gui.element." + b.getClass().getSimpleName() + "Graphic");

			return (GraphicElement) block.getConstructor(Block.class).newInstance(b);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}

		return null;

	}

}
