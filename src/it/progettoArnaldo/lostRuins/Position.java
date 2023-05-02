package it.progettoArnaldo.lostRuins;
/*
 * Classe per la gestione delle posizioni
 */
public class Position {
	private int x;
	private int y;
	private int h;
	/*
	 * Costruttore di Position
	 * @param _x la ascissa
	 * @param _y l'ordinata y
	 * @param _h l'altitudine
	 */
	public Position(int _x, int _y, int _h ) {
		x=_x;
		y=_y;
		h=_h;
	}
	public Position() {
		
	}
	/*
	 * metodo che trova la distanza con i parametri x e y con la posizione p1
	 * @param p1 la posizione di confronto
	 * @return distance la distanza con la posizione p1
	 */
	public int distanceTo(Position p1) {
		int distance=0;
		distance=(int)Math.round(Math.sqrt(Math.pow((this.x-p1.x), 2)+Math.pow((this.y-p1.y), 2)));
		return distance;
	}
	/*
	 * metodo che trova la differenza di altitudine con la posizione p1
	 * @param p1 la posizione di confronto
	 * @return distance la differenza di altitudine con la posizione p1
	 */
	public int differentHigh(Position p1) {
		int high=0;
		high=Math.abs(this.h-p1.h);
		return high;
	}
	
}
