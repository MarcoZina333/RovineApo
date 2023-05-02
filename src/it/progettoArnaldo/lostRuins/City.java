package it.progettoArnaldo.lostRuins;

import java.util.ArrayList;
/*
 * classe per la gestione delle singole citt�
 */
public class City {
	
	private String name;
	private String id;
	private Position position= new Position();
	private ArrayList<String> citiesNear=new ArrayList <String>();
	
	/*
	 * costruttore di City
	 * @param _name nome della citt�
	 * @param _id id della citt�
	 * @param _position positione della citt�
	 * @param _cityNear Array di String che contiene gli id della citt� con esiste un colleamento
	 */
	public City(String _name, String _id, Position _position, ArrayList<String> _citiesNear) {
		name=_name;
		id=_id;
		position=_position;
		citiesNear=_citiesNear;
	}
	
	public City() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * metodo che restituisce true se l'id inserito � uguale a questa citt� altrimenti restituisce false
	 * @param id l'id che si vuole controllare
	 * @return find true se � lo stesso id di questa citt� altrimenti false
	 */
	public boolean sameID(String id1) {
		boolean find=false;
		if(id1.equals(this.id))
			find=true;
		return find;
	}
	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public Position getPosition() {
		return position;
	}

	public ArrayList<String> getCitiesNear() {
		return citiesNear;
	}

}
