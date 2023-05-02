package it.progettoArnaldo.lostRuins;

import it.unibs.fp.mylib.InputDati;
/*
 * Classe main
 */
public class LostRuinsMain {

	private static final String FILE_10000 = "./PgAr_Map_10000.xml";
	private static final String FILE_2000 = "./PgAr_Map_2000.xml";
	private static final String FILE_200 = "./PgAr_Map_200.xml";
	private static final String FILE_50 = "./PgAr_Map_50.xml";
	private static final String FILE_12 = "./PgAr_Map_12.xml";
	private static final String FILE_5 = "./PgAr_Map_5.xml";
	private static final String MESSAGE_OPTION_FILE = "Inserire il numero corrispondente al file che vuoi leggere:\n"
			+ "1=PgAr_Map_5.xml\n"
			+ "2=PgAr_Map_12.xml\n"
			+ "3=PgAr_Map_50.xml\n"
			+ "4=PgAr_Map_200.xml\n"
			+ "5=PgAr_Map_2000.xml\n"
			+ "6=PgAr_Map_10000.xml\n";

	public static void main(String[] args) {
		// scelta del file da leggere
		String file = null;
		int choice=InputDati.leggiIntero(MESSAGE_OPTION_FILE , 1, 6);
		switch(choice) {
		case 1:
			file=FILE_5;
			break;
		case 2:
			file=FILE_12;
		break;	
		case 3:
			file=FILE_50;
			break;
		case 4:
			file=FILE_200;
			break;
		case 5:
			file=FILE_2000;
			break;
		case 6:
			file=FILE_10000;
			break;
		}
		Map map=InputXml.readMap(file);// creo la mappa con i file del xml
		City rovinePerdute=map.getCities().get(map.getCities().size()-1);// creo una citt� contenente i dati della citt� di arrivo
		RecordRoad finalRoadT1=map.bestRoadT1();//trovo il percorso migliore per il tema1
		RecordRoad finalRoadT2=map.bestRoadT2();//trovo il percorso migliore per il tema2
		RoutesXml.xmleWrite(finalRoadT1, finalRoadT2, rovinePerdute);// stampo il file xml finale
		
	}

}
