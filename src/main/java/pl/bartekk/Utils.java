package pl.bartekk;

import java.util.List;

import model.Element;

public class Utils {

	/**
	 * @param elements
	 * 
	 * c = S*K/L
	 */
	public static void generateLocalMatrix(List<Element> elements) {

		double c = 0.0;
		int minus;

		for (Element e : elements) {

			c = e.getSurfaceArea() * e.getConductingRate() / e.getdL();

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
			// a = e.G;

			if (e.isStreamCondition())
				e.setBoundaryMatrix(q * e.getSurfaceArea(), 0);
			if (e.isConvevtionCondition())
				e.setBoundaryMatrix(-1 * alfa * tn * e.getSurfaceArea(), 1);
			System.out.println(e.toString());
		}
	}
}
