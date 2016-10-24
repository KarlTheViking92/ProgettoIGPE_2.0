package core.element.block;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import core.World.World;
import core.element.Position;
import gui.GameProperties;

public class BlockFactory {

	private HashMap<Integer, String> blockSet;
	private GameProperties properties = new GameProperties();
	public BlockFactory() {
		this.blockSet = properties.getBlockTypes();
//		loadTypes("resources/config/blockTypes");

	}

	// load block types from config file
	private void loadTypes(String path) {
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(path));

			String row = buffer.readLine();

			while (row != null) {

				String[] tokens = row.split("\\s+");

				int code = Integer.parseInt(tokens[0]);

				String type = tokens[1];

				blockSet.put(code, type);

				row = buffer.readLine();
			}
			buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		System.out.println(" ho caricato i tipi di blocco " + blockSet.size());
	}

	// factory method reflection 
	public Block makeBlock(World w,int code, Position pos) {

		try {
//			System.out.println(code);
//			System.out.println("core.element.block." + blockSet.get(code));
//			System.out.println("logic Block: "+blockSet.get(code));
			Class block = Class.forName("core.element.block." + blockSet.get(code));
			
			return (Block) block.getConstructor(World.class,Position.class).newInstance(w,pos);

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
