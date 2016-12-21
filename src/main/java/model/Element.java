package model;

public class Element {

	private double dL; // dlugosc elementu
	private int firstNodeId; // id pierwszego wezla
	private int secondNodeId; // id drugiego wezla
	private double surfaceArea; // pole powierchni
	private double conductingRate; // wspolczynnik przenikania
	private boolean streamCondition;
	private boolean convevtionCondition;
	private double[][] localMatrix = new double[2][2]; // macierz lokalna 2x2
	private double[] boundaryMatrix = new double[2]; // lokalna macierz warunkow
														// brzegowych 1x2

	public Element() {
	}

	public Element(double dL, int firstNodeId, int secondNodeId, double surfaceArea, double conductingRate,
			boolean streamCondition, boolean convevtionCondition) {
		super();
		this.dL = dL;
		this.firstNodeId = firstNodeId;
		this.secondNodeId = secondNodeId;
		this.surfaceArea = surfaceArea;
		this.conductingRate = conductingRate;
		this.streamCondition = streamCondition;
		this.convevtionCondition = convevtionCondition;
	}

	public double[] getBoundaryMatrix() {
		return boundaryMatrix;
	}

	public void setBoundaryMatrix(double d, int i) {
		this.boundaryMatrix[i] = d;
	}

	public boolean isStreamCondition() {
		return streamCondition;
	}

	public void setStreamCondition(boolean streamCondition) {
		this.streamCondition = streamCondition;
	}

	public boolean isConvevtionCondition() {
		return convevtionCondition;
	}

	public void setConvevtionCondition(boolean convevtionCondition) {
		this.convevtionCondition = convevtionCondition;
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

	public double getdL() {
		return dL;
	}

	public void setdL(double dL) {
		this.dL = dL;
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

	public void setLocalMatrix(double d, int i, int j) {
		this.localMatrix[i][j] = d;
	}

	@Override
	public String toString() {
		return "Element [dL=" + dL + ", firstNodeId=" + firstNodeId + ", secondNodeId=" + secondNodeId
				+ ", surfaceArea=" + surfaceArea + ", conductingRate=" + conductingRate + ", streamCondition="
				+ streamCondition + ", convevtionCondition=" + convevtionCondition + "]";
	}

}
