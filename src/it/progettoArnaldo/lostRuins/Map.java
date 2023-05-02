package it.progettoArnaldo.lostRuins;

import java.util.ArrayList;
import java.util.*;
/*
 * Classe per la gestione della mappa
 */
public class Map {
	private HashMap <City, RecordRoad> mapOfRoad=new HashMap<City, RecordRoad>();
	private ArrayList<City> cities=new ArrayList<City>();
	/*
	 * costruttore di Map
	 * @param _cities ArrayList di tutte le città presenti
	 */
	public Map(ArrayList <City> _cities) {
		cities=_cities;
	}
	/*
	 * metodo che restituisce la città corrispondente all'id inserito
	 * @param id1 id della città che si  vuole trovare
	 * @return city la città corrispondente all'id inserito
	 */
	public City findCity(String id1) {
		City city=new City(null, "-1", null, null);	
		for(int i=0;i<this.cities.size();i++) {
			if(this.cities.get(i).sameID(id1)) {			
				city=this.cities.get(i);
			}
		}
		return city;
	}
	
	
	public ArrayList<City> getCities() {
		return cities;
	}
	/*
	 * metodo che setta la mappa con: tutte le città presenti nella mappa come key e come 
	 * value un oggetto RecordRoad che distanceFromOrigin=0 per campo base e uguale a "infinito" per tutte le altre
	 * e come cityTouched un arrayList vuoto
	 */
	public void setInitialRecordRoad() {
		RecordRoad r;
		RecordRoad r0;
		for(int i=0; i<this.cities.size();i++) {
			if(i==0) {
				r0=new RecordRoad(new ArrayList<City>(), 0);
				this.mapOfRoad.put(this.cities.get(i), r0);
			}
			else {
				r=new RecordRoad(new ArrayList<City>(), Integer.MAX_VALUE);
				this.mapOfRoad.put(this.cities.get(i), r);
			}
		}
	}
	/*
	 * Metodo che per ogni città va a modificare in mapOfRoad l'oggetto recordRoad della città a cui closest è collegata se
	 * la strada passante per closest è minore della distanza dall'origine (che ha quella città a cui closest è collegata) e
	 * sostituisce l'arraylist cityTouched di recordRoad(che è il value di mapOfRoad) con l'arraylist cityTouched di closest più closest
	 * ovvero tutte le città da cui si è passati.
	 * per le distanze si basa sulla x e sulla y dell'oggetto Position di ogni città
	 */
	public void setRecordRoadT1( City closest) {
		City c=new City();
		ArrayList<City> temp=new ArrayList<City>();
		temp.addAll(this.mapOfRoad.get(closest).getCityTouched());
		temp.add(closest);
		for(int i=0;i<closest.getCitiesNear().size();i++) {
			c=this.findCity(closest.getCitiesNear().get(i));
			if(closest.getCitiesNear().contains(c.getId())) {
				if(closest.getPosition().distanceTo(c.getPosition())+this.mapOfRoad.get(closest).getDistanceFromOrigin()<this.mapOfRoad.get(c).getDistanceFromOrigin()) {
					RecordRoad r=new RecordRoad(temp, this.mapOfRoad.get(closest).getDistanceFromOrigin()+closest.getPosition().distanceTo(c.getPosition()));
					this.mapOfRoad.replace(c, r);
				}
				else if(closest.getPosition().distanceTo(c.getPosition())+this.mapOfRoad.get(closest).getDistanceFromOrigin()==this.mapOfRoad.get(c).getDistanceFromOrigin() && this.mapOfRoad.get(c).getDistanceFromOrigin()!=Integer.MAX_VALUE){
					if(Map.compareRoads(this.mapOfRoad.get(closest).getCityTouched(), this.mapOfRoad.get(c).getCityTouched())==1) {
						temp.clear();
						temp=this.mapOfRoad.get(closest).getCityTouched();
						RecordRoad r=new RecordRoad();
						temp.add(closest);
						r=new RecordRoad(temp, this.mapOfRoad.get(closest).getDistanceFromOrigin()+closest.getPosition().distanceTo(c.getPosition()));
						this.mapOfRoad.replace(c, r);
					}
				}
			}
			
		}
	}
	/*
	 * funziona come setRecordRoadT1 con la differenza che
	 * invece di usare la x e la y di Position usa l'h che rappresenta la'ltitudine
	 */
	public void setRecordRoadT2( City closest) {
		City c=new City();
		ArrayList<City> temp=new ArrayList<City>();
		temp.addAll(this.mapOfRoad.get(closest).getCityTouched());
		temp.add(closest);
		for(int i=0;i<closest.getCitiesNear().size();i++) {
			c=this.findCity(closest.getCitiesNear().get(i));
			if(closest.getCitiesNear().contains(c.getId())) {
				if(closest.getPosition().differentHigh(c.getPosition())+this.mapOfRoad.get(closest).getDistanceFromOrigin()<this.mapOfRoad.get(c).getDistanceFromOrigin()) {
					RecordRoad r=new RecordRoad(temp, this.mapOfRoad.get(closest).getDistanceFromOrigin()+closest.getPosition().differentHigh(c.getPosition()));
					this.mapOfRoad.replace(c, r);
				}
				else if(closest.getPosition().differentHigh(c.getPosition())+this.mapOfRoad.get(closest).getDistanceFromOrigin()==this.mapOfRoad.get(c).getDistanceFromOrigin() && this.mapOfRoad.get(c).getDistanceFromOrigin()!=Integer.MAX_VALUE){
					if(Map.compareRoads(this.mapOfRoad.get(closest).getCityTouched(), this.mapOfRoad.get(c).getCityTouched())==1) {
						temp.clear();
						temp=this.mapOfRoad.get(closest).getCityTouched();
						RecordRoad r=new RecordRoad();
						temp.add(closest);
						r=new RecordRoad(temp, this.mapOfRoad.get(closest).getDistanceFromOrigin()+closest.getPosition().differentHigh(c.getPosition()));
						this.mapOfRoad.replace(c, r);
					}
				}
			}
			
		}
	}
	/*
	 * Metodo che inserendo un array list di città trova quale città è più vicina all'origine ovvero a campo base
	 * @param cities ArrayList delle città tra cui si vuole trovare la più vicina a campo base
	 * @c la città più vicina a campo base
	 */
	public City closestCityFromOrigin(ArrayList<City> cities) {
		City c=new City();
		int distance=Integer.MAX_VALUE;
		for(int i=0; i<cities.size();i++) {
			if(this.mapOfRoad.get(cities.get(i)).getDistanceFromOrigin()<distance) {
				distance=this.mapOfRoad.get(cities.get(i)).getDistanceFromOrigin();
				c=cities.get(i);
			}
		}
		return c;
	}
	/*
	 * metodo che confronta due ArrayList di città che rappresentano due percorsi possibili diverso 
	 * che restituisce 1 se si da precendenza al primo percorso, restituisce 2 se di precedenza al secondo percorso
	 * @param c1 primo percorso
	 * @param c2 secondo percorso
	 * @return choise che è 1 se dia precendeza c1, è 2 se si da precedenza a c2
	 */
	public static int compareRoads(ArrayList<City> c1, ArrayList<City> c2) {
		int choise=0;
		int countc1=0;
		int countc2=0;
		int countId1=0;
		int countId2=0;
		for(int i=0;i<c1.size();i++) {
			countId1=countId1+Integer.valueOf(c1.get(i).getId());
			countc1 ++;
		}
		for(int i=0;i<c2.size();i++) {
			countId2=countId2+Integer.valueOf(c2.get(i).getId());
			countc2 ++;
		}
		if(countc1<countc2) {
			choise=1;
			return choise;
		}
		else if(countc1>countc2) {
			choise=2;
			return choise;
		}
		else if(countId1>countId2) {
			choise=1;
			return choise;
		}
		else
			choise=2;
		return choise;
	}
 	
	/*
	 * metodo che va a trovare tutte le strade con distanza minore dall'origine per il primo team
	 * @return best1 che è la value per la key=Rovine perdute, ovvero il percorso migliore per arrivare a rovine perdute con il suo relativo costo di carburante
	 */
	public RecordRoad bestRoadT1() {
		City c= new City();
		City c1=new City();
		City rovinePerdute= new City();
		ArrayList<City> temp=new ArrayList<City> ();
		temp.addAll(this.cities);
		int i=0;
		while(temp.size()>0) {
			if(temp.get(i).getName().equals("campo base")) {// per campo base vado a settare inizialmente  mapOfRoad
				this.setInitialRecordRoad();
				this.setRecordRoadT1(temp.get(i));//setto le possibili nuove strade migliori sfruttando i collegamenti di questa città
				c1=temp.get(i);
				temp.remove(c1);
				c=this.closestCityFromOrigin(temp);
				i=temp.indexOf(c);
			}
			else if(temp.get(i).getName().equals("Rovine Perdute")){// per rovine Perdute non ho interesse a vedere suoi collegamenti perchè se ne vanno da rovine perdute, ma io devo arrivarci non andarmene			
				c1=temp.get(i);
				rovinePerdute=temp.get(i);// copio l'oggetto rappresentate rovine perdute per usarlo alla riga 208 come key
				temp.remove(c1);
				c=this.closestCityFromOrigin(temp);
				i=temp.indexOf(c);
			}
			else {
				this.setRecordRoadT1(temp.get(i));	//setto le possibili nuove strade migliori sfruttando i collegamenti di questa città			
				c1=temp.get(i);
				temp.remove(c1);
				c=this.closestCityFromOrigin(temp);
				i=temp.indexOf(c);
			}
		}
		RecordRoad best1=new RecordRoad(this.mapOfRoad.get(rovinePerdute).getCityTouched(), this.mapOfRoad.get(rovinePerdute).getDistanceFromOrigin());
		
		return best1;
	}
	
	/*
	 * funziona come il metodo bestRoadT1 solo che si basa sull'altitudine
	 */
	public RecordRoad bestRoadT2() {
		this.mapOfRoad.clear();
		City c= new City();
		City c1=new City();
		City rovinePerdute= new City();
		ArrayList<City> temp=new ArrayList<City> ();
		temp.addAll(this.cities);
		int i=0;
		while(temp.size()>0) {
			if(temp.get(i).getName().equals("campo base")) {
				this.setInitialRecordRoad();
				this.setRecordRoadT2(temp.get(i));
				c1=temp.get(i);
				temp.remove(c1);
				c=this.closestCityFromOrigin(temp);
				i=temp.indexOf(c);
			}
			else if(temp.get(i).getName().equals("Rovine Perdute")){				
				c1=temp.get(i);
				rovinePerdute=temp.get(i);
				temp.remove(c1);
				c=this.closestCityFromOrigin(temp);
				i=temp.indexOf(c);
			}
			else {
				this.setRecordRoadT2(temp.get(i));				
				c1=temp.get(i);
				temp.remove(c1);
				c=this.closestCityFromOrigin(temp);
				i=temp.indexOf(c);
			}
		}
		RecordRoad best1=new RecordRoad(this.mapOfRoad.get(rovinePerdute).getCityTouched(), this.mapOfRoad.get(rovinePerdute).getDistanceFromOrigin());
		
		return best1;
	}

}
