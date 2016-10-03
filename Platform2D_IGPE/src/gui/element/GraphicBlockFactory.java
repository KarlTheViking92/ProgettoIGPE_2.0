package gui.element;

import java.lang.reflect.InvocationTargetException;

import core.element.Position;
import core.element.block.Block;

public class GraphicBlockFactory {

	public GraphicElement makeBlock(Block b) {

		try {
			System.out.println(b.getClass().getSimpleName());
//			System.out.println("gui.element.block."+b.getClass().getSimpleName()+"Graphic");
			Class block = Class.forName("gui.element."+b.getClass().getSimpleName()+"Graphic");
			
			return (GraphicElement) block.getConstructor(Block.class).newInstance(b);

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	
}
