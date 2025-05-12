package brandubh.util;

/**
 * Enumerado que define el color de las piezas.
 * 
 * @author Pablo_Velasco_Martin
 * 
 */

public enum Color{
	
	/*enumerado para pieza blanca*/
	BLANCO('B'),
	/*enumerado para pieza negra */
	NEGRO('N');
	
	/*Variable de tipo caracter letra para definir que color es si B o N*/
	private char letra;
	
	
	/**
	 * Constructor le da el valor a la variable letra atraves de lo que recibe.
	 * 
	 * @param letra, Va a indicar que color es si es B o N
	 * @return letra, ya con el color inicializado
	 * 
	 */
	
	private Color (char letra) {
		
		this.letra=letra;
	}
	
	
	/**
	 * devuelve la letra actual.
	 * 
	 * @return letra
	 * 
	 */
	
	public char toChar() {
		
		return letra;
	}

	
	/**
	 * devuelve el color contrario al actual
	 * 
	 * @return NEGRO, si el color actual es blanco
	 * @return Blanco, si el color actual es negro
	 * 
	 */
	
	public Color consultarContrario() {
		
		if(this ==Color.BLANCO) {
			return NEGRO;
		}
		return BLANCO;
	}
	
	
	
}