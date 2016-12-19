package pl.bartekk;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
	
	public List<String> readFromFile() throws FileNotFoundException{
		Scanner s = new Scanner(new File("/home/bartek/mes/src/main/resources/mes.txt"));
		List<String> list = new ArrayList<String>();
		while (s.hasNext()){
		    list.add(s.next());
		}
		s.close();
		
		return list;
	}
}
