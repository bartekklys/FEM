package pl.bartekk;

public class Element {

	private boolean streamCondition;
	private boolean convevtionCondition;

	private double dL; // dlugosc elementu
	private int firstNodeId;
	private int secondNodeId;
	private double surfaceArea;
	private double conductingRate;
	private double localMatrix[][] = new double[2][2];
	private double boundaryMatrix[] = new double[2];

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
		return "[ " + localMatrix[0][0] + " , " + localMatrix[0][1] + "\n" + " , " + localMatrix[1][0] + " , "
				+ localMatrix[1][1] + " ]" + "\n" +
				+ boundaryMatrix[0] + " , " + boundaryMatrix[1];
	}

}
