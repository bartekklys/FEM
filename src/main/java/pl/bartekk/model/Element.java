package pl.bartekk.model;

/**
 * @author bartek
 *
 * Element skończony, który uzyskujemy po dyskretyzacji dziedziny.
 */
public class Element {

	private double length; // dlugosc elementu
	private int firstNodeId; // id pierwszego wezla
	private int secondNodeId; // id drugiego wezla
	private double surfaceArea; // pole powierzchni
	private double conductingRate; // wspolczynnik przenikania
	private double[][] localMatrix = new double[2][2]; // macierz lokalna 2x2
	private double[] boundaryMatrix = new double[2]; // lokalna macierz warunkow
														// 1x2

	public double[] getBoundaryMatrix() {
		return boundaryMatrix;
	}

	public void setBoundaryMatrix(double[] boundaryMatrix) {
		this.boundaryMatrix = boundaryMatrix;
	}

	public double getSurfaceArea() {
		return surfaceArea;
	}

	public void setSurfaceArea(double surfaceArea) {
		this.surfaceArea = surfaceArea;
	}

	public double getConductingRate() {
		return conductingRate;
	}

	public void setConductingRate(double conductingRate) {
		this.conductingRate = conductingRate;
	}

	public double getLentgh() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public int getFirstNodeId() {
		return firstNodeId;
	}

	public void setFirstNodeId(int firstNodeId) {
		this.firstNodeId = firstNodeId;
	}

	public int getSecondNodeId() {
		return secondNodeId;
	}

	public void setSecondNodeId(int secondNodeId) {
		this.secondNodeId = secondNodeId;
	}

	public double[][] getLocalMatrix() {
		return localMatrix;
	}

	public void setLocalMatrix(double[][] localMatrix) {
		this.localMatrix = localMatrix;
	}
}