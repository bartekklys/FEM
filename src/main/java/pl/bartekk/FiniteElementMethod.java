package pl.bartekk;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Element;

public class FiniteElementMethod {

	public static void main(String[] args) throws IOException {
		Element e1 = new Element();
		e1.setdL(10);
		e1.setStreamCondition(false);
		e1.setConductingRate(58);
		e1.setFirstNodeId(1);
		e1.setSecondNodeId(2);
		e1.setSurfaceArea(75);

		Element e2 = new Element();
		e2.setConvevtionCondition(false);
		e2.setdL(10);
		e2.setConductingRate(58);
		e2.setFirstNodeId(2);
		e2.setSecondNodeId(3);
		e2.setSurfaceArea(75);

		List<Element> elements = new ArrayList<Element>();
		elements.add(e1);
		elements.add(e2);

		FiniteElementMethodFacade facade = new FiniteElementMethodFacade();
		facade.start(elements);
	}
}
