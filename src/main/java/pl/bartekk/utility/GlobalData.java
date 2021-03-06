package pl.bartekk.utility;

import java.io.FileNotFoundException;
import java.util.Map;

/**
 * @author bartek
 *
 * Klasa przechowująca dane globalne.
 */
public class GlobalData {

	private static final String ALPHA = "alpha";
	private static final String NUMBER_OF_ELEMENTS = "numberOfElements";
	private static final String TEMPERATURE = "temperature";
	private static final String Q_STREAM = "q";

	private double alpha; // wspolczynnik konwekcji wymiany ciepla
	private int numberOfElements; // ilosc elementow
	private int numberOfNodes; // ilosc wezlow
	private double[][] globalMatrix; // macierz globalna
	private double[] globalBoundaryMatrix; // globalny wektor warunkow brzgowych
	private double T; // temperatura konwekcji
	private double q; // strumien ciepla

	public GlobalData() {
		Map<String, Double> globalData;
		try {
			globalData = Utils.getGlobalData();
			this.alpha = globalData.get(ALPHA).intValue();
			this.numberOfElements = globalData.get(NUMBER_OF_ELEMENTS).intValue();
			this.numberOfNodes = this.numberOfElements + 1;
			this.globalMatrix = generateGlobalMatrix();
			this.globalBoundaryMatrix = generateGlobalBoundaryMatrix();
			this.T = globalData.get(TEMPERATURE);
			this.q = globalData.get(Q_STREAM).doubleValue();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private double[][] generateGlobalMatrix() {
		return new double[numberOfElements + 1][numberOfElements + 1];
	}

	private double[] generateGlobalBoundaryMatrix() {
		return new double[numberOfElements + 1];
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public double[][] getGlobalMatrix() {
		return globalMatrix;
	}

	public void setGlobalMatrix(double[][] globalMatrix) {
		this.globalMatrix = globalMatrix;
	}

	public double[] getGlobalBoundaryMatrix() {
		return globalBoundaryMatrix;
	}

	public void setGlobalBoundaryMatrix(double[] globalBoundaryMatrix) {
		this.globalBoundaryMatrix = globalBoundaryMatrix;
	}

	public double getAlpha() {
		return alpha;
	}

	public double getT() {
		return T;
	}

	public double getQ() {
		return q;
	}

}