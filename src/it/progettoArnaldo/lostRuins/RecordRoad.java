package it.progettoArnaldo.lostRuins;
/*
 * classe per la gestione delle strade fatte
 */
import java.util.ArrayList;

public class RecordRoad {
	private Integer distanceFromOrigin;
	private ArrayList<City> cityTouched=new ArrayList<City>();
	
	public RecordRoad() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * costruttore di RecordRoad
	 * @param _cityTouched ArrayList di City che rappresenta le città da cui si passati
	 * @param d Integer che rappreenta la distanza dall'origine che in questo programma è campo base
	 */
	public RecordRoad(ArrayList<City> _cityTouched, Integer d) {
		cityTouched.addAll(_cityTouched);
		distanceFromOrigin=d;
	}
	public Integer getDistanceFromOrigin() {
		return distanceFromOrigin;
	}
	public void setDistanceFromOrigin(Integer distanceFromOrigin) {
		this.distanceFromOrigin = distanceFromOrigin;
	}
	public ArrayList<City> getCityTouched() {
		return cityTouched;
	}
	

}
