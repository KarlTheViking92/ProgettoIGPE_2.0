package gui.element.block;

import java.lang.reflect.InvocationTargetException;

import core.element.Position;
import core.element.block.Block;

public class GraphicBlockFactory {

	public GraphicBlock makeBlock(Block b) {

		try {
//			System.out.println("gui.element.block."+b.getClass().getSimpleName()+"Graphic");
			Class block = Class.forName("gui.element.block."+b.getClass().getSimpleName()+"Graphic");
			
			return (GraphicBlock) block.getConstructor(Block.class).newInstance(b);

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