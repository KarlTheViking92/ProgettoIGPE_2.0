package mapEditor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class LoadObject {
	private HashMap<Integer, String> blockSet;

	public LoadObject() {
		this.blockSet = new HashMap<>();
		loadTypes("resources/config/blockTypes");

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
	}
}
