package brandubh.util;

/**
 * Define los cuatro sentidos en los que una pieza puede moverse (N,E,S,O).
 * Asocia a cada sentido un valor de desplazamiento
 * 
 * @author Pablo_Velasco_Martin
 */

public enum Sentido{
	
	VERTICAL_N(-1,+0),
	VERTICAL_S(+1,+0),
	HORIZONTAL_E (+0,+1),
	HORIZONTAL_O(+0,-1);
	
	private int desplazamientoEnFilas;
	private int desplazamientoEnColumnas;
	
	
	/**
	 * Constructor de la clase Sentido
	 * 
	 * @param despF
	 * @param despC
	 */
	
	private Sentido(int despF, int despC) {
		
		this.desplazamientoEnFilas=despF;
		this.desplazamientoEnColumnas= despC;
	}
	
	
	/**
	 * Devuelve el desplazamiento realizado en horizontal
	 * 
	 * @return esplazamientoEnFilas
	 */
	
	public int consultarDesplazamientoEnFilas() {
		
		return this.desplazamientoEnFilas;	
	}
	
	
	/**
	 * Devuelve el desplazamiento realizado en vertical 
	 * 
	 * @return desplazamientoEnColumnas
	 */
	
	public int consultarDesplazamientoEnColumnas() {
		
		return this.desplazamientoEnColumnas;	
	}
	
}

