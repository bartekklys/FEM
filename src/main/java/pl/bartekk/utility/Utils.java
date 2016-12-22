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

/**
 * @author bartek
 *
 * Narzędzia służące do zapisu wyniku oraz odczytu z pliku danych globalnych
 * oraz informacji o elementach.
 * 
 */
public class Utils {

	private static final String FILE_PATH = "src/main/resources/mes.txt";

	/**
	 * @return mapa z danymi globalnymi
	 * @throws FileNotFoundException
	 * 
	 * Zwraca zmapowane wartośći z pliku mes.txt.
	 */
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

	/**
	 * @return lista elementów
	 * @throws FileNotFoundException
	 * 
	 * Zwraca listę elementów popranych z pliku mes.txt.
	 */
	public static List<Element> getElements() throws FileNotFoundException {
		Scanner s = new Scanner(new File(getFilePath()));

		List<String> data = new ArrayList<>();
		List<String> listOfElements = new ArrayList<>();
		List<Element> elements = new ArrayList<>();

		while (s.hasNext()) {
			data.add(s.next());
		}
		s.close();


		for (String element : data) {
			if (element.startsWith("element"))
				listOfElements.add(element);
		}

		for (String string : listOfElements) {
			int equalSign = string.indexOf('=');
			String e = string.substring(equalSign + 1);

			String[] elementData = e.split(",");

			Element element = new Element();
			element.setLength(Double.parseDouble(elementData[0]));
			element.setFirstNodeId(Integer.parseInt(elementData[1]));
			element.setSecondNodeId(Integer.parseInt(elementData[2]));
			element.setSurfaceArea(Double.parseDouble(elementData[3]));
			element.setConductingRate(Double.parseDouble(elementData[4]));
			elements.add(element);
		}
		return elements;
	}

	/**
	 * @return
	 * 
	 * Prywatna metodą zwracająca ścieżkę do pliku mes.txt.
	 */
	private static String getFilePath() {
		File file = new File(FILE_PATH);
		return file.getAbsolutePath();
	}
	
	/**
	 * @param result
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * 
	 * Zapis wyników do pliku w postaci np. "t1 = 100" itd.
	 */
	public static void writeResultToFile(double[] result) throws FileNotFoundException, UnsupportedEncodingException{
		    PrintWriter writer = new PrintWriter("src/main/resources/result.txt");
		    for(int i = 0 ; i < result.length ; i++){
		    	writer.println("t" + i + " = " + result[i]);
		    }
		    writer.close();
	}
}