package brandubh.util;

/**
 * Define los tipos de pieza que hay en la partida y los asocia a un color (blanco o negro)
 * 
 * @author Pablo_Velasco_Martin
 */

public enum TipoPieza{
	
	
	DEFENSOR('D', Color.BLANCO),
	ATACANTE('A',Color.NEGRO),
	REY('R',Color.BLANCO);
	
	private char caracter;
	private Color color;
	
	
	/**
	 * Constructor de la clase TipoPieza. 
	 * 
	 * @param caracter
	 * @param color
	 */
	
	private TipoPieza(char caracter,Color color) {
		
		this.caracter=caracter;
		this.color=color;
	}
	
	
	/**
	 * Devuelve el caracter asociado a cada tipo de pieza: Rey(R), Defensor(D), Atacante(A)
	 * 
	 * @return caracter
	 */
	
	public char toChar() {
		
		return caracter;
		
	}
	
	/**
	 * Devuelve el color asociado a cada tipo de pieza: Rey(Blanco), Defensor(Blanco), Atacante(Negro)
	 * 
	 * @return color
	 */
	
	public Color consultarColor() {
		
		return color;
	}
	
	
	
	
	
	
	
	
}