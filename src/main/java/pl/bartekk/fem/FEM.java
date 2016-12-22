package pl.bartekk.fem;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import pl.bartekk.model.Element;
import pl.bartekk.utility.GlobalData;
import pl.bartekk.utility.Utils;

public class FEM {

	static GlobalData globalData = new GlobalData();
	private static final double EPSILON = 1e-10;

	public static void generateLocalMatrix(List<Element> elements) {

		for (Element element : elements) {

			double[][] localMatrix = new double[2][2];
			double c = element.getSurfaceArea() * element.getConductingRate() / element.getLentgh();

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

		double[][] localMatrix = elements.get(globalData.getNumberOfElements() - 1).getLocalMatrix();
		localMatrix[1][1] += globalData.getAlpha()
				* elements.get(globalData.getNumberOfElements() - 1).getSurfaceArea();

		for (int k = 0; k < globalData.getNumberOfElements(); k++)
			for (int i = 0; i < 2; i++) {
				double[] bm = elements.get(k).getBoundaryMatrix();
				bm[i] = 0;
				elements.get(k).setBoundaryMatrix(bm);
			}

		double[] bm = elements.get(0).getBoundaryMatrix();
		bm[0] = globalData.getQ() * elements.get(0).getSurfaceArea();

		double[] pl = elements.get(globalData.getNumberOfElements() - 1).getBoundaryMatrix();
		pl[1] = -1 * globalData.getAlpha() * globalData.getT()
				* elements.get(globalData.getNumberOfElements() - 1).getSurfaceArea();
	}

	public static void generateBurdenMatrix(List<Element> elements) {
		for (Element e : elements) {
			double[] boundaryMatrix = new double[2];

			if (e.isStreamCondition()) {
				boundaryMatrix[0] = globalData.getQ() * e.getSurfaceArea();
				e.setBoundaryMatrix(boundaryMatrix);
			}
			if (e.isConvevtionCondition()) {
				boundaryMatrix[1] = (-1) * globalData.getAlpha() * globalData.getT() * e.getSurfaceArea();
				e.setBoundaryMatrix(boundaryMatrix);
			}
		}
	}

	public static void generateGlobalMatrix(List<Element> elements) {

		double[][] globalMatrix = globalData.getGlobalMatrix();

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

					globalMatrix[x - 1][y - 1] += localMatrix[i][j];
					globalData.setGlobalMatrix(globalMatrix);
				}
			}
		}
	}

	public static void generateGlobalBoundaryMatrix(List<Element> elements)
			throws FileNotFoundException, UnsupportedEncodingException {
		int c = 0;
		double[] globalBoundaryMatrix = globalData.getGlobalBoundaryMatrix();

		for (int k = 0; k < globalData.getNumberOfElements(); k++) {

			for (int i = 0; i < 2; i++) {
				if (i == 0)
					c = elements.get(k).getFirstNodeId();
				if (i == 1)
					c = elements.get(k).getSecondNodeId();

				double[] pl = elements.get(k).getBoundaryMatrix();
				globalBoundaryMatrix[c - 1] += -pl[i];
				globalData.setGlobalBoundaryMatrix(globalBoundaryMatrix);
			}
		}

		Utils.writeResultToFile(
				gaussianElimination(globalData.getGlobalMatrix(), globalData.getGlobalBoundaryMatrix()));
	}

	private static double[] gaussianElimination(double[][] A, double[] b) {
		int N = b.length;

		for (int p = 0; p < N; p++) {

			// find pivot row and swap
			int max = p;
			for (int i = p + 1; i < N; i++) {
				if (Math.abs(A[i][p]) > Math.abs(A[max][p])) {
					max = i;
				}
			}
			double[] temp = A[p];
			A[p] = A[max];
			A[max] = temp;
			double t = b[p];
			b[p] = b[max];
			b[max] = t;

			// singular or nearly singular
			if (Math.abs(A[p][p]) <= EPSILON) {
				throw new RuntimeException("Matrix is singular or nearly singular");
			}

			// pivot within A and b
			for (int i = p + 1; i < N; i++) {
				double alpha = A[i][p] / A[p][p];
				b[i] -= alpha * b[p];
				for (int j = p; j < N; j++) {
					A[i][j] -= alpha * A[p][j];
				}
			}
		}

		// back substitution
		double[] x = new double[N];
		for (int i = N - 1; i >= 0; i--) {
			double sum = 0.0;
			for (int j = i + 1; j < N; j++) {
				sum += A[i][j] * x[j];
			}
			x[i] = (b[i] - sum) / A[i][i];
		}
		return x;
	}
}