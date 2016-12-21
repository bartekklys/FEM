package fem;

import java.io.FileNotFoundException;
import java.util.List;

import model.Element;
import utility.Utils;

public class FiniteElementMethodFacade {
	
	public void start() throws FileNotFoundException{
		List<Element> elements = Utils.getElements();
		FEM.generateLocalMatrix(elements);
		FEM.generateBurdenMatrix(elements);
		FEM.generateGlobalMatrix(elements);
	}
}
