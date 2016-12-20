package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class FileReader {

	private static final String FILE_PATH = "src/main/resources/mes.txt";
	private static final int ZERO = 0;

	public static Map<String, Double> readFromFile() throws FileNotFoundException {

		File file = new File(FILE_PATH);
		String absolutePath = file.getAbsolutePath();
		Scanner s = new Scanner(new File(absolutePath));
		List<String> list = new ArrayList<String>();
		while (s.hasNext()) {
			list.add(s.next());
		}
		s.close();

		Map<String, Double> map = new HashMap<>();
		for (String string : list) {
			String[] pair = string.split("=");
			map.put(pair[ZERO], Double.parseDouble(pair[ZERO + 1]));
		}

		return map;
	}
}
