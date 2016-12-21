package fem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Element;

public class FiniteElementMethodApp {

	public static void main(String[] args) throws IOException {

		FiniteElementMethodFacade facade = new FiniteElementMethodFacade();
		facade.start();
	}
}
