package pl.bartekk;

import java.util.List;

public class FiniteElementMethodFacade {
	
	public void start(List<Element> elements){
		Utils.generateLocalMatrix(elements);
		Utils.generateBurdenMatrix(elements);
	}
}
