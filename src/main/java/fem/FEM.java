package fem;

import java.util.List;

import model.Element;
import utility.GlobalData;

public class FEM {

	static GlobalData globalData = new GlobalData();

	public static void generateLocalMatrix(List<Element> elements) {

		double c;

		for (Element element : elements) {

			double[][] localMatrix = new double[2][2];
			c = element.getSurfaceArea() * element.getConductingRate() / element.getLentgh();

			for (int i = 0; i < 2; i++) {
				for (int j = 0; j < 2; j++) {

					if ((j == 1 && i == 0) || (i == 1 && j == 0))
						localMatrix[i][j] = c * (-1);
					else
						localMatrix[i][j] = c;
				}
			}
			element.setLocalMatrix(localMatrix);
		}
	}

	public static void generateBurdenMatrix(List<Element> elements) {
		double q = globalData.getQ();
		double alpha = globalData.getAlpha();
		double tn = globalData.getT();

		for (Element e : elements) {

			if (e.isStreamCondition())
				e.setBoundaryMatrix(q * e.getSurfaceArea(), 0);
			if (e.isConvevtionCondition())
				e.setBoundaryMatrix(-1 * alpha * tn * e.getSurfaceArea(), 1);
		}
	}

	public static void generateGlobalMatrix(List<Element> elements) {
		
		int globalMatrixSize = elements.size() + 2;
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
	}
}
