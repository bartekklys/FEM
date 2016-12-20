package pl.bartekk;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Utilities;

public class FiniteElementMethod {
	
	private FileReader fr;

	private String iloscWezlow;
	private String iloscElementow;
	
	public void init() throws FileNotFoundException{
		fr = new FileReader();
		iloscWezlow = fr.readFromFile().get(1);
		iloscElementow = fr.readFromFile().get(5);
		
		// and so on
	}

	public static void main(String[] args) throws IOException{
		Element e1 = new Element();
		e1.setdL(2);
		e1.setConductingRate(0.5);
		e1.setFirstNodeId(1);
		e1.setSecondNodeId(2);
		e1.setSurfaceArea(1);
		
		Element e2 = new Element();
		e2.setdL(2);
		e2.setConductingRate(0.5);
		e2.setFirstNodeId(2);
		e2.setSecondNodeId(3);
		e2.setSurfaceArea(1);
		
		List<Element> elements = new ArrayList<Element>();
		elements.add(e1);
		elements.add(e2);
		
		FiniteElementMethodFacade facade = new FiniteElementMethodFacade();
		facade.start(elements);
	}
}
