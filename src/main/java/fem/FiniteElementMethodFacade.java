package fem;

import java.util.List;

import model.Element;

public class FiniteElementMethodFacade {
	
	public void start(List<Element> elements){
		FEM.generateLocalMatrix(elements);
		FEM.generateBurdenMatrix(elements);
	}
}
