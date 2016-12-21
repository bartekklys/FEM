package fem;

import java.util.List;

import model.Element;
import pl.bartekk.GlobalData;

public class FEM {

	// TODO: zmienic minus

	static GlobalData globalData = new GlobalData();

	/**
	 * @param elements
	 * 
	 *            c = S*K/L
	 */
	public static void generateLocalMatrix(List<Element> elements) {

		double c = 0.0;
		int minus;

		for (Element e : elements) {

			c = e.getSurfaceArea() * e.getConductingRate() / e.getLentgh();

			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					if ((j == 1 && i == 0) || (i == 1 && j == 0))
						minus = -1;
					else
						minus = 1;
					e.setLocalMatrix(c * minus, i, j);
				}
			}
		}
	}

	public static void generateBurdenMatrix(List<Element> elements) {
		// TODO: zmienne z pliku
		int q = 300; // strumien ciepla
		double alfa = 58; // wspolczynnik konwekcji wymiany ciepla
		int tn = 25; // temperatura konwekcji (otoczenia?)

		for (Element e : elements) {

			if (e.isStreamCondition())
				e.setBoundaryMatrix(q * e.getSurfaceArea(), 0);
			if (e.isConvevtionCondition())
				e.setBoundaryMatrix(-1 * alfa * tn * e.getSurfaceArea(), 1);
		}
	}

	public static void generateGlobalMatrix(List<Element> elements) {
		int globalMatrixSize = elements.size() + 3;
		double[][] globalMatrix = new double[globalMatrixSize][globalMatrixSize];
		for (int k = 0; k < globalData.getNumberOfElements(); k++) {
			double[][] localMatrix = elements.get(k).getLocalMatrix();
			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {
					int x = 0;
					int y = 0;
					if (i == 0)
						x = elements.get(k).getFirstNodeId();
					if (j == 0)
						y = elements.get(k).getFirstNodeId();
					if (i == 1)
						x = elements.get(k).getSecondNodeId();
					if (j == 1)
						y = elements.get(k).getSecondNodeId();

					globalMatrix[x][y] = globalMatrix[x][y] + localMatrix[i][j];
					globalData.setGlobalMatrix(globalMatrix);

				}
			}
		}
		for(int i = 0 ; i < globalMatrixSize ; i++){
			for(int j = 0 ; j < globalMatrixSize ; j++){
			
			System.out.print(globalMatrix[i][j]);
			}
			System.out.println("\n");
		}
	}
}
