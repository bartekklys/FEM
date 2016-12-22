package pl.bartekk.fem;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import pl.bartekk.model.Element;
import pl.bartekk.utility.Utils;

public class FiniteElementMethodFacade {
	
	public void start() throws FileNotFoundException, UnsupportedEncodingException{
		List<Element> elements = Utils.getElements();
		FEM.generateLocalMatrix(elements);
		FEM.generateBurdenMatrix(elements);
		FEM.generateGlobalMatrix(elements);
		FEM.generateGlobalBoundaryMatrix(elements);
	}
}