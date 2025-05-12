package brandubh.modelo;
import brandubh.util.Sentido;
import brandubh.util.Coordenada;

/**
 * jugadas del juego brandubh.
 * 
 * @author Pablo_Velasco_Martin
 * 
 * @see brandubh.util
 */


public record Jugada(Celda origen,Celda destino){

	
	/**
	 * Constructor crea una jugada con un origen y un destino.
	 * 
	 * @param origen, Es la celda en la que esta la pieza que se va a mover
	 * @param destino, Es la celda a la que la pieza se va a mover
	 * 
	 */
	
	public Jugada(Celda origen, Celda destino){
		
		this.origen=origen;
		this.destino=destino;
	}
	
	
	/**
	 * Devuelve el sentido correspondiente a sus coordenadas origen y destino.
	 * 
	 * @return Sentido.VERTICAL_N,Sentido.VERTICAL_S,Sentido.HORIZONTAL_O,Sentido.HORIZONTAL_E,
	 *  el setido de la jugada
	 *  @return null si el sentido no es ninguno de los cuatro permitido
	 */
	
	public Sentido consultarSentido() {
	
	Coordenada Co,Cd;
	int filO,filD,colO,colD;
	
	Co =origen.consultarCoordenada();
	Cd = destino.consultarCoordenada();
	
	filO = Co.fila();
	filD = Cd.fila();
	colO = Co.columna();
	colD = Cd.columna();
	


	if(filO > filD && colO == colD ) {
		
		return Sentido.VERTICAL_N;
	}
	
	if(filO < filD && colO == colD ) {
		
		return Sentido.VERTICAL_S;
	}
	
	if(filO == filD  && colO < colD ) {
		
		return Sentido.HORIZONTAL_E;
	}
	
	if(filO == filD  && colO > colD ) {
		
		return Sentido.HORIZONTAL_O;
	}
	
	return null;
	
	}
	
	
	/**
	 * Devuelve true si el movimiento es en horizontal o vertical y false en caso contrario
	 * 
	 * @return  true 
	 * @return false
	 */
	
	public boolean esMovimientoHorizontalOVertical() {
		if(consultarSentido() == null) {
			return false;
		}else
			
		return true;
	}
	
	
	/**
	 * Devuelve la celda de origen
	 * 
	 * @return origen.
	 */
	
	public Celda consultarOrigen() {
		
		return origen;
	}
	
	
	/**
	 * Devuelve la celda de destino
	 * 
	 * @return destino.
	 */
	
	public Celda consultarDestino() {
		
		return destino;
	}
	
}