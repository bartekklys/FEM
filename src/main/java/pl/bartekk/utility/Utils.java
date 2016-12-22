package pl.bartekk.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import pl.bartekk.model.Element;

public class Utils {

	private static final String FILE_PATH = "src/main/resources/mes.txt";

	public static Map<String, Double> getGlobalData() throws FileNotFoundException {

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

		List<String> data = new ArrayList<>();
		List<String> listOfElements = new ArrayList<>();
		List<Element> elements = new ArrayList<>();

		while (s.hasNext()) {
			data.add(s.next());
		}
		s.close();


		for (String ss : data) {
			if (ss.startsWith("element"))
				listOfElements.add(ss);
		}

		for (String string : listOfElements) {
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
	
	public static void writeResultToFile(double[] result) throws FileNotFoundException, UnsupportedEncodingException{
		    PrintWriter writer = new PrintWriter("src/main/resources/result.txt");
		    for(int i = 0 ; i < result.length ; i++){
		    	writer.println("t" + i + " = " + result[i]);
		    }
		    writer.close();
	}
}