package pl.bartekk;

import java.util.List;

public class Utils {

	public static void generateLocalMatrix(List<Element> elements) {

		double c = 0.0;
		int minus;

		for (Element e : elements) {

			c = e.getSurfaceArea() * e.getConductingRate() * 1 / e.getdL();

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
		int q = 5; // strumien ciepla
		double alfa = 0.5; // wspolczynnik konwekcji wymiany ciepla
		int tn = 30; // temperatura konwekcji (otoczenia?)

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
