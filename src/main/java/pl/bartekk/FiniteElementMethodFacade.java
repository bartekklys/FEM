package pl.bartekk;

import java.util.List;

import model.Element;

public class FiniteElementMethodFacade {
	
	public void start(List<Element> elements){
		Utils.generateLocalMatrix(elements);
		Utils.generateBurdenMatrix(elements);
	}
}
