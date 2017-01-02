package pl.bartekk.fem;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import pl.bartekk.model.Element;
import pl.bartekk.utility.GlobalData;
import pl.bartekk.utility.Utils;

/**
 * @author bartek
 *
 *         Klasa zawierająca główną logikę. Generuje macierz lokalną oraz
 *         macierz warunków brzegowych dla wszystkich elementów, a następnie
 *         tworzy macierz globalną oraz metodą eliminacji Gaussa Jordana zwraca
 *         wynik programu.
 */
class FEM {

	private static GlobalData globalData = new GlobalData();
	private static int numberOfElements = globalData.getNumberOfElements();

	/**
	 * @param elements
	 *            - lista elementów w rozpatrywanym układzie
	 * 
	 *            Generuję macierz lokalną oraz lokalny wektor obciążeń
	 *            uwzględniając strumień ciepła oraz warunek konwekcji dla
	 *            wszystkich elementów.
	 */
	static void generateLocalMatrix(List<Element> elements) {

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

		double[][] localMatrix = elements.get(numberOfElements - 1).getLocalMatrix();
		localMatrix[1][1] += globalData.getAlpha() * elements.get(numberOfElements - 1).getSurfaceArea();

		for (int k = 0; k < numberOfElements; k++)
			for (int i = 0; i < 2; i++) {
				double[] boundaryMatrix = elements.get(k).getBoundaryMatrix();
				boundaryMatrix[i] = 0;
				elements.get(k).setBoundaryMatrix(boundaryMatrix);
			}

		double[] firstBoundaryMatrix = elements.get(0).getBoundaryMatrix();
		firstBoundaryMatrix[0] = globalData.getQ() * elements.get(0).getSurfaceArea();

		double[] lastBoundaryMatrix = elements.get(numberOfElements - 1).getBoundaryMatrix();
		lastBoundaryMatrix[1] = -1 * globalData.getAlpha() * globalData.getT()
				* elements.get(numberOfElements - 1).getSurfaceArea();
	}

	/**
	 * @param elements
	 *            - lista elementów w rozpatrywanym układzie
	 * 
	 *            Na podstawie lokalnych macierzy generuję macierz globalną.
	 */
	static void generateGlobalMatrix(List<Element> elements) {

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

	/**
	 * @param elements
	 *            - lista elementów w rozpatrywanym układzie
	 * @throws FileNotFoundException
	 * @throws UnsupportedEncodingException
	 * 
	 *             Na podstawie lokalnych maciery wartości brzegowych eneruję
	 *             globalną macierz obciażeń.
	 */
	static void generateGlobalBoundaryMatrix(List<Element> elements)
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

	/**
	 * @param A
	 *            - macierz globalna
	 * @param b
	 *            - wektor obciążeń
	 * @return zwraca tablicę wynikową
	 * 
	 *         Metoda eliminacji Gaussa Jordana, zwraca tablicę z szukanymi
	 *         temperaturami.
	 */
	private static double[] gaussianElimination(double[][] A, double[] b) {
		int N = b.length;

		for (int p = 0; p < N; p++) {

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

			for (int i = p + 1; i < N; i++) {
				double alpha = A[i][p] / A[p][p];
				b[i] -= alpha * b[p];
				for (int j = p; j < N; j++) {
					A[i][j] -= alpha * A[p][j];
				}
			}
		}
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