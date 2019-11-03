package behaviorLogic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class CSV extends Record {
	
	/**
	 * Constructor method
	 */
	public CSV() {}
	
	/**
	 * Constructor method with action attribute
	 * @param action action object
	 */
	public CSV(Action action) {
		this.action = action;
		this.action.subscribe(this);
	}
	
	/**
	 * method that records current action information in a csv file
	 */
	@Override
	public void register() {
		//Delimiter used in CSV file
	    String commaDelimiter = ",";
	    String newLineSeparator = "\n";
                 
        try {
        	FileWriter fileWriter = new FileWriter("./recordCSV.csv", true);
             
            //Write a new student object list to the CSV file
        	fileWriter.append(action.getUserId());
            fileWriter.append(commaDelimiter);
            fileWriter.append(action.getAction());
            fileWriter.append(commaDelimiter);
            fileWriter.append(action.getDate());
            fileWriter.append(commaDelimiter);
            fileWriter.append(action.getTime());
            fileWriter.append(newLineSeparator);
             
            fileWriter.flush();
            fileWriter.close();
             
        } catch (Exception e) {
            System.out.println("Error in CsvFileWriter !!!");
            e.printStackTrace();
        }    
	}
	
	/**
	 * method that reads information from a csv file
	 * 
	 * @return a String array with the file information
	 */
	public String[] read() {
	    //Delimiter used in CSV file
	    String commaDelimiter = ",";
	    String newLineSeparator = "\n";
	     
	    //Student attributes index
	    int userId= 0;
	    int actionMade = 1;
	    int date = 2;
	    int time = 3; 
		
	    
	    File fileName = new File("./recordCSV.csv"); 
		BufferedReader fileReader = null;
	      
        try {
            String line = "";
             
            //Create the file reader
            fileReader = new BufferedReader(new FileReader(fileName));
             
            //Read the CSV file header to skip it
            fileReader.readLine();
            
            String data = new String();
            //Read the file line by line 
            while ((line = fileReader.readLine()) != null) {
                //Get all tokens available in line
                String[] tokens = line.split(commaDelimiter);
                data += tokens[userId]+commaDelimiter+tokens[actionMade]+commaDelimiter+tokens[date]+commaDelimiter+tokens[time]+newLineSeparator;
            }
             
            String[] result = data.split("\n");
            fileReader.close();
            return result;
        } 
        catch (Exception e) {
            System.out.println("Error in CsvFileReader !!!");
            e.printStackTrace();
            return null;
        } 
	}
	
	
	
	

}
