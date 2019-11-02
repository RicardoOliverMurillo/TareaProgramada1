package observerLogic;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XML extends Record{

	public XML() {}
	
	public XML(Action action) {
		this.action = action;
		this.action.subscribe(this);
	}
	
	//Lógica para escribir en archivo
	//Lógica para escribir en archivo
		@Override
		public void register() {
			File inputFile = new File("./recordXML.xml");
			try {
				 if (inputFile.exists() == false) {
			         DocumentBuilder dBuilder = buildDocument();
			         Document doc = dBuilder.newDocument();
			         Element rootElement = doc.createElement("Actions");
			         doc.appendChild(rootElement);
			         
			         rootElement.appendChild(createAction(doc));
	
			         writeXmlFile(doc);
				 } else {
					 DocumentBuilder dBuilder = buildDocument();
				     Document doc = dBuilder.parse(inputFile);
				     Element root = doc.getDocumentElement();
			         
			         root.appendChild(createAction(doc));
			         
			         DOMSource source = new DOMSource(doc);

			         TransformerFactory transformerFactory = TransformerFactory.newInstance();
			         Transformer transformer = transformerFactory.newTransformer();
			         StreamResult result = new StreamResult(inputFile);
			         transformer.transform(source, result);
			         
				 }
				 
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
		}

		@Override
		public String[] read() {
			String data = "";
			try {
				File inputFile = new File("./recordXML.xml");
				DocumentBuilder dBuilder = buildDocument();
		        Document doc = dBuilder.parse(inputFile);
		        doc.getDocumentElement().normalize();
		        NodeList nList = doc.getElementsByTagName("Action"); 
		         
		        for (int temp = 0; temp < nList.getLength(); temp++) {
		           Node nNode = nList.item(temp);
		           
		           if (nNode.getNodeType() == Node.ELEMENT_NODE) {
		              Element eElement = (Element) nNode;
		              data+= xmlParse(eElement);
		           }
		        }
		      } catch (Exception e) {
		         e.printStackTrace();
		      }
			
			String[] result = data.split("\n");
			return result;
		}
		
		private Element createAction(Document doc) {
			
			Element rootAction = doc.createElement("Action");
			
			Element user = doc.createElement("User");
			user.appendChild(doc.createTextNode(action.getUserId()));

			Element tempAction = doc.createElement("Activity");
			tempAction.appendChild(doc.createTextNode(action.getAction()));

			Element date = doc.createElement("Date");
			date.appendChild(doc.createTextNode(action.getDate()));
			
			Element time = doc.createElement("Time");
			time.appendChild(doc.createTextNode(action.getTime()));
			
			rootAction.appendChild(user);
			rootAction.appendChild(tempAction);
			rootAction.appendChild(date);
			rootAction.appendChild(time);
			
			return rootAction;
		}
		
		private void writeXmlFile(Document doc) {
			try { 
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
		        Transformer transformer = transformerFactory.newTransformer();
		        DOMSource source = new DOMSource(doc);
		        StreamResult result = new StreamResult(new File("./recordXML.xml"));
		        transformer.transform(source, result);
		        
		        StreamResult consoleResult = new StreamResult(System.out);
		        transformer.transform(source, consoleResult);
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		private DocumentBuilder buildDocument() {
			DocumentBuilder dBuilder = null;
			try {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		        dBuilder = dbFactory.newDocumentBuilder();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        return dBuilder;
		}
		
		private String xmlParse (Element eElement) {
			String data = "";
			data+="< Actions >\n" + "< User > " + eElement.getElementsByTagName("User").item(0).getTextContent() + "< /User >\n";
            data += "< Action > " + eElement.getElementsByTagName("Activity").item(0).getTextContent() +"< /Action >\n" ;
            data += "< Date > " + eElement.getElementsByTagName("Date").item(0).getTextContent() +"< /Date >\n" ;
            data += "< Time > " + eElement.getElementsByTagName("Time").item(0).getTextContent() +"< /Time >\n" ;
            return data;
		}

}
