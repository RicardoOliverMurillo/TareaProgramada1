package observerLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XML extends Record{

	public XML() {}
	
	public XML(Action action) {
		this.action = action;
		this.action.subscribe(this);
	}
	
	//Lógica para escribir en archivo
		@Override
		public void register() {
			try { 
			ObjectMapper mapper = new XmlMapper();
			FileWriter fr = new FileWriter("./recordXML.xml", true);
			mapper.writeValue(fr, action);
			fr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public String[] read() {
			File file = new File("./recordXML.xml"); 
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
