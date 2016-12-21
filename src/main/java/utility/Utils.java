package utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Element;

public class Utils {

	private static final String FILE_PATH = "src/main/resources/mes.txt";

	public static Map<String, Double> readFromFile() throws FileNotFoundException {

		Scanner s = new Scanner(new File(getFilePath()));
		List<String> list = new ArrayList<>();
		while (s.hasNext()) {
			list.add(s.next());
		}
		s.close();

		Map<String, Double> map = new HashMap<>();
		for (String string : list) {
			String[] pair = string.split("=");
			if (!pair[0].startsWith("element"))
				map.put(pair[0], Double.parseDouble(pair[1]));
		}
		return map;
	}

	public static List<Element> getElements() throws FileNotFoundException {
		Scanner s = new Scanner(new File(getFilePath()));

		List<String> list = new ArrayList<>();
		List<Element> elements = new ArrayList<>();

		while (s.hasNext()) {
			list.add(s.next());
		}
		s.close();

		List<String> nowa = new ArrayList<>();

		for (String ss : list) {
			if (ss.startsWith("element"))
				nowa.add(ss);
		}

		for (String string : nowa) {
			int equalSign = string.indexOf('=');
			String e = string.substring(equalSign + 1);

			String[] elementData = e.split("_");

			Element element = new Element();
			element.setLength(Double.parseDouble(elementData[0]));
			element.setFirstNodeId(Integer.parseInt(elementData[1]));
			element.setSecondNodeId(Integer.parseInt(elementData[2]));
			element.setSurfaceArea(Double.parseDouble(elementData[3]));
			element.setConductingRate(Double.parseDouble(elementData[4]));
			element.setStreamCondition(Boolean.parseBoolean(elementData[5]));
			element.setConvevtionCondition(Boolean.parseBoolean(elementData[6]));
			elements.add(element);
		}
		return elements;
	}

	private static String getFilePath() {
		File file = new File(FILE_PATH);
		return file.getAbsolutePath();
	}
}
