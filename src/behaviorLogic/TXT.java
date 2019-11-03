package behaviorLogic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class TXT extends Record{
	
	/**
	 * Constructor method
	 */
	public TXT() {}
	
	/**
	 * Constructor method with action attribute
	 * @param action action object
	 */
	public TXT(Action action) {
		this.action = action;
		this.action.subscribe(this);
	}

	/**
	 * method that records current action information in a txt file
	 */
	@Override
	public void register() {
		try {
			FileWriter fr = new FileWriter("./recordTXT.txt", true);
			String line = convertInfo(action.getUserId(),11)+
					convertInfo(action.getAction(),25)+
					convertInfo(action.getDate(),9)+
					convertInfo(action.getTime(),9)+
					"\n";
			fr.write(line);
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * method that reads information from a txt file
	 * 
	 * @return a String array with the file information
	 */
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

	/**
	 * method that standardizes the size of the strings
	 * @param info the action information
	 * @param length max size
	 * @return String with the correct size
	 */
	private String convertInfo(String info, int length) {
		int size = length-info.length();
		for(int i = 0; i < size; i++) {
			info += "X";
		}
		return info;
	}
}
