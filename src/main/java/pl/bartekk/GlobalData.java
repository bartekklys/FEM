package pl.bartekk;

import java.io.FileNotFoundException;
import java.util.Map;

import org.apache.log4j.Logger;

import utility.FileReader;

public class GlobalData {

	private static Logger logger = Logger.getLogger(GlobalData.class);

	private static final String NUMBER_OF_ELEMENTS = "numberOfElements";
	private static final String NUMBER_OF_NODES = "numberOfNodes";
	private static final String TEMPERATURE = "temperature";
	private double systemLength; // dlugosc ukladu
	private double alfa; // wspolczynnik konwekcji wymiany ciepla
	private int numberOfElements; // ilosc elementow
	private int numberOfNodes; // ilosc wezlow
	private double[][] globalMatrix; // macierz globalna
	private double[][] globalBoundaryMatrix; // globalny wektor warunkow brzgowych
	private double T; // temperatura konwekcji
	private double q; // strumien ciepla

	public GlobalData() {
		try {
			Map<String, Double> globalData = FileReader.readFromFile();
			this.numberOfElements = globalData.get(NUMBER_OF_ELEMENTS).intValue();
			this.numberOfNodes = globalData.get(NUMBER_OF_NODES).intValue();
			this.T = globalData.get(TEMPERATURE);
			// TODO: remaining fields
		} catch (FileNotFoundException e) {
			logger.info("no data " + e);
			e.printStackTrace();
		}
	}

}
