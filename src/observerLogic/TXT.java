package observerLogic;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class TXT extends Record{
	
	public TXT() {}
	
	public TXT(Action action) {
		this.action = action;
		this.action.subscribe(this);
	}

	//Lógica para escribir en archivo
	@Override
	public void register() {
		try {
			FileWriter fr = new FileWriter("./recordTXT.txt", true);
			String line = action.getUserId()+","+action.getAction()+","+action.getDate()+","+action.getTime()+"\n";
			fr.write(line);
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String[] read() {
		File file = new File("./recordTXT.txt"); 
		Scanner sc;
		String data = new String();
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				data += sc.nextLine() + "\n"; 
			} 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		String[] result = data.split("\n");
		return result;
	}

}
