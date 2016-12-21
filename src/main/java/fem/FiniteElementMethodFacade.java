package fem;

import java.io.FileNotFoundException;
import java.util.List;

import model.Element;
import utility.Utils;

public class FiniteElementMethodFacade {
	
	public void start(List<Element> elements) throws FileNotFoundException{
		FEM.generateLocalMatrix(elements);
		FEM.generateBurdenMatrix(elements);
		FEM.generateGlobalMatrix(elements);
	}
}
