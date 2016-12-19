package pl.bartekk;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	
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
		
	}
}
