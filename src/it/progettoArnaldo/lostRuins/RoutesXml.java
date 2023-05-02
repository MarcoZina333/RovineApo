package it.progettoArnaldo.lostRuins;
/*
 * Classe per la gestione della scrittura del file xml contenente i dati dei due team e i due relativi percorsi migliori per arrivare a rovine perdute
 */
import java.io.FileOutputStream;

import javax.xml.stream.XMLOutputFactory;

import javax.xml.stream.XMLStreamWriter;



/**  Scritture del file XML   */



public class RoutesXml {



	private static final String ERROR_MESSAGE = "Errore nell'inizializzazione del writer.";
	private static final String VERSION = "version 1.0";
	private static final String UTF_8 = "utf-8";
	private static final String NAME_FILE_XML = "Routes.xml";
	private static final String TEAM_2 = "Metztli";
	private static final String NOME = "nome";
	private static final String ID = "id";
	private static final String CITY = "city";
	private static final String CITIES = "cities";
	private static final String COST = "cost";
	private static final String TEAM_1 = "Tonatiuh";
	private static final String TEAM = "team";
	private static final String ROUTE = "route";
	private static final String ROUTES = "routes";

	public static void xmleWrite(RecordRoad mapTeam1, RecordRoad mapTeam2, City rovinePerdute)

	{

		XMLOutputFactory xmlof = null;

		XMLStreamWriter xmlw = null;

		

		try 

		{

			xmlof = XMLOutputFactory.newInstance();

			xmlw = xmlof.createXMLStreamWriter(new FileOutputStream(NAME_FILE_XML), UTF_8);

			xmlw.writeStartDocument(UTF_8, VERSION);

			xmlw.writeStartElement(ROUTES);

			// TEAM TONATIUH
			

			xmlw.writeStartElement(ROUTE);

			xmlw.writeAttribute(TEAM, TEAM_1);

			xmlw.writeAttribute(COST, Integer.toString(mapTeam1.getDistanceFromOrigin())); 

			xmlw.writeAttribute(CITIES, Integer.toString(mapTeam1.getCityTouched().size()+1) );

		

			// ELENCO DELLE CITTA'

			for(int i = 0; i < mapTeam1.getCityTouched().size(); i++) 

			{

				xmlw.writeStartElement(CITY);

				xmlw.writeAttribute(ID, mapTeam1.getCityTouched().get(i).getId());

				xmlw.writeAttribute(NOME, mapTeam1.getCityTouched().get(i).getName());

				xmlw.writeEndElement();

			}
			xmlw.writeStartElement(CITY);

			xmlw.writeAttribute(ID, rovinePerdute.getId());

			xmlw.writeAttribute(NOME, rovinePerdute.getName());

			xmlw.writeEndElement();
			
			xmlw.writeEndElement(); // chiudo tag route

			// TEAM METZTLI

			xmlw.writeStartElement(ROUTE);

			xmlw.writeAttribute(TEAM, TEAM_2);

			xmlw.writeAttribute(COST, Integer.toString(mapTeam2.getDistanceFromOrigin())); // capire dove trovo valore carburante

			xmlw.writeAttribute(CITIES, Integer.toString(mapTeam2.getCityTouched().size()+1) );

			

			// ELENCO DELLE CITTA'

			for(int i = 0; i < mapTeam2.getCityTouched().size(); i++) 

			{

				xmlw.writeStartElement(CITY);

				xmlw.writeAttribute(ID, mapTeam2.getCityTouched().get(i).getId());

				xmlw.writeAttribute(NOME, mapTeam1.getCityTouched().get(i).getName());

				xmlw.writeEndElement();

			}

			xmlw.writeStartElement(CITY);

			xmlw.writeAttribute(ID, rovinePerdute.getId());

			xmlw.writeAttribute(NOME, rovinePerdute.getName());

			xmlw.writeEndElement();			

			xmlw.writeEndElement(); // chiudo tag route

			xmlw.writeEndElement(); // chiudo tag routes

			xmlw.writeEndDocument(); 

			

			xmlw.flush();

			xmlw.close();

		

		} 

		catch (Exception e) 

		{

			System.out.println(ERROR_MESSAGE);

			System.out.println(e.getMessage());

		}

	}

}
