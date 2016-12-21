package pl.bartekk;

import java.io.FileNotFoundException;
import java.util.Map;

import utility.Utils;

public class GlobalData {

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
			Map<String, Double> globalData = Utils.readFromFile();
			this.numberOfElements = globalData.get(NUMBER_OF_ELEMENTS).intValue();
			this.numberOfNodes = globalData.get(NUMBER_OF_NODES).intValue();
			this.T = globalData.get(TEMPERATURE);
			// TODO: remaining fields
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public double[][] getGlobalMatrix() {
		return globalMatrix;
	}



	public void setGlobalMatrix(double[][] globalMatrix) {
		this.globalMatrix = globalMatrix;
	}



	public double[][] getGlobalBoundaryMatrix() {
		return globalBoundaryMatrix;
	}



	public void setGlobalBoundaryMatrix(double[][] globalBoundaryMatrix) {
		this.globalBoundaryMatrix = globalBoundaryMatrix;
	}



	public double getSystemLength() {
		return systemLength;
	}

	public void setSystemLength(double systemLength) {
		this.systemLength = systemLength;
	}

	public double getAlfa() {
		return alfa;
	}

	public void setAlfa(double alfa) {
		this.alfa = alfa;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public void setNumberOfElements(int numberOfElements) {
		this.numberOfElements = numberOfElements;
	}

	public int getNumberOfNodes() {
		return numberOfNodes;
	}

	public void setNumberOfNodes(int numberOfNodes) {
		this.numberOfNodes = numberOfNodes;
	}

	public double getT() {
		return T;
	}

	public void setT(double t) {
		T = t;
	}

	public double getQ() {
		return q;
	}

	public void setQ(double q) {
		this.q = q;
	}

	public static String getTemperature() {
		return TEMPERATURE;
	}
	
	

}
