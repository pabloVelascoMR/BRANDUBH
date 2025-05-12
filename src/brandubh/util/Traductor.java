package brandubh.util;


/**
 * Traduce las coordenadas a texto en notación algebraica y al revés. 
 * 
 * @author Pablo_Velasco_Martin
 */

public class Traductor{
	
	
	/**
	 * Comprueba que el texto pasado sea correcto de lo contrario devuelve null
	 * 
	 * Devuelve la coordenada que le correcponde a una cadena de caracteres de longitud 2
	 * 
	 * @param texto, cadena de caracteres a consultar
	 * @return coordenadaDevolver
	 */
	
	public static Coordenada consultarCoordenadaParaNotacionAlgebraica(String texto) {
        int fila, filaT, columnaT;
        char columnaLetra;

        fila = ((int)texto.charAt(1))-48 ;  
        columnaLetra = texto.charAt(0);
   
      
        if (fila >= 1 && fila <= 7 && columnaLetra >= 'a' && columnaLetra <= 'g') {
        
        	filaT = 7 - fila;
            columnaT = columnaLetra - 'a';
            Coordenada coordenadaDevolver= new Coordenada(filaT, columnaT);
            return coordenadaDevolver;
        	
        }
       
        return null;
    }
	
	
	/**
	 * Comprueba que la coordenada pasada sea correcta, de lo contrario devuelve null
	 * 
	 * Devuelve una la cadena de caracteres que le corresponde a una coordenada
	 * 
	 * @param coordenada, coordenada a consultar
	 * @return devolver
	 */
	
    public static String consultarTextoEnNotacionAlgebraica(Coordenada coordenada) {
       
    	int fila, filaT, columna;
        char columnaT;
        String devolver;

        fila = coordenada.fila();
        columna = coordenada.columna();

        if (fila >= 0 && fila <7 && columna >= 0 && columna <7) {
           
        	filaT = 7 - fila;
        	columnaT = (char) ('a' + columna);
        	devolver= String.valueOf(columnaT)+filaT;
        	
        	return devolver;
            
        }

        return null;
    }
    

    /**
     * Comprueba que la cadena de caracteres introducida por el usuario no sea nula.
     * 
     * @param texto, cadena de caracteres a comprobar
     * @return boolean, true si el texto no es null y false si lo es.
     */
    
    public static boolean esTextoCorrectoParaCoordenada(String texto) {
       
    	if(consultarCoordenadaParaNotacionAlgebraica(texto) != null && texto.length() == 2) {
    		return true;
    	}
    	
    	return false;
    }
	
	
}