package it.progettoArnaldo.lostRuins;

import java.io.FileInputStream;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
/*
 * Classe per gestire la lettura dei file xml
 */
public class InputXml {
	
/*
 * metodo per leggere il file xml scelto per creare un oggetto di tipo Map con i dati di tale file
 * @param filoToRead il nome del file xml da leggere
 * @return totalMap l'oggetto di tipo Map contente i dati del file xml
 */
	public static Map readMap(String fileToRead) {
		City cityToAdd;
		ArrayList <City> cities=new ArrayList<City>();
		
		String link=null;
		String name=null;
		String id=null;
		int x=0;
		int y=0;
		int h=0;
		
		XMLInputFactory xmlif = null;
		XMLStreamReader xmlr = null;
		try {
		xmlif = XMLInputFactory.newInstance();
		xmlr = xmlif.createXMLStreamReader(fileToRead, new FileInputStream(fileToRead));
		while (xmlr.hasNext()) { // continua a leggere finché ha eventi a disposizione
			switch (xmlr.getEventType()) { // switch sul tipo di evento
			case XMLStreamConstants.START_DOCUMENT:
				break;
			case XMLStreamConstants.START_ELEMENT: 
			if(xmlr.getLocalName().equals("city")) {
				ArrayList<String> citiesNear=new ArrayList();
				for (int i = 0; i < xmlr.getAttributeCount(); i++) {
					
					switch(i) {
					case 0:
						id=xmlr.getAttributeValue(i);
						break;
					case 1:
						name=xmlr.getAttributeValue(i);
						break;
					case 2:
						x=Integer.valueOf(xmlr.getAttributeValue(i));
						break;
					case 3:
						y=Integer.valueOf(xmlr.getAttributeValue(i));
						break;
					case 4:
						h=Integer.valueOf(xmlr.getAttributeValue(i));
						break;
					}
				}
				xmlr.next();
				boolean endCicle=false;
				while(!endCicle) {
					switch(xmlr.getEventType()) {
					case XMLStreamConstants.START_ELEMENT:
						for(int j=0;j<xmlr.getAttributeCount();j++) {
							link=xmlr.getAttributeValue(j);
							citiesNear.add(link);
						}
						break;
					case XMLStreamConstants.END_ELEMENT:
						if(xmlr.getLocalName().equals("city"))
							endCicle=true;
						break;
					}
					xmlr.next();
				}
				Position position=new Position(x, y, h);
				cityToAdd=new City(name, id, position, citiesNear);
				cities.add(cityToAdd);
			}
			break;
			}
			xmlr.next();
			}
		} catch (Exception e) {
		System.out.println("Errore nell'inizializzazione del reader:");
		System.out.println(e.getMessage());
		}
		Map totalMap=new Map(cities);
		return totalMap;
	}
	

}
