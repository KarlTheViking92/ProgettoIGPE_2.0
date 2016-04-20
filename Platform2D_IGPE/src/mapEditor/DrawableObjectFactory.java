package mapEditor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class DrawableObjectFactory {

	private HashMap<Integer, String> map;

	public DrawableObjectFactory() {
		map = new HashMap<>();
		loadImagePaths("resources/config/imagePaths");
	}

	private void loadImagePaths(String path) {
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(path));

			String row = buffer.readLine();

			while (row != null) {

				String[] tokens = row.split("\\s+");

				int code = Integer.parseInt(tokens[0]);

				String type = tokens[1];

				map.put(code, type);

				row = buffer.readLine();
			}
			buffer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getImageByCode(int code) {
		
		if (map.containsKey(code))
			return map.get(code);

		return null;
	}

}
