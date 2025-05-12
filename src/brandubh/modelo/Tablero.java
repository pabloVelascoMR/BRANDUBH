package brandubh.modelo;

import  brandubh.util.Coordenada;
import  brandubh.util.TipoPieza;
import  brandubh.util.TipoCelda;
import java.util.Arrays;


/**
 * Tablero para juegos de mesa. Se iran colocando piezas sobre sus
 * correspondientes celdas de forma que se complete una partida
 * 
 * @author Pablo_Velasco_Martin 
 * 
 * @see brandubh.util
 * @see java.util
 * 
 */

public class Tablero{
	
	/*variable tablero  que es un array de celdas de dos dimensiones */
	private Celda [][] tablero;

	
	/**
	 * Constructor  inicializa el tablero
	 */
	
	public Tablero() {
		
		tablero = new Celda[7][7];
		
		for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
               
            	Coordenada coordenada =new Coordenada(i,j);
                if(i==3&& j==3) {
                	
                	Celda celdaTrono = new Celda( coordenada,TipoCelda.TRONO);
                	tablero[i][j] = celdaTrono;
              
                }else if((i==0 && (j==6 || j==0))||(i==6 &&( j==6 || j==0))){
                	
                	Celda celdaProvincias = new Celda( coordenada,TipoCelda.PROVINCIA);
                	tablero[i][j] = celdaProvincias;
                	
                }else {
                	
                	Celda celdanormal = new Celda( coordenada,TipoCelda.NORMAL);
                	tablero[i][j]= celdanormal;	
                }
            }
        }
	}
	
	
	/**
	 * Metodo que devuelve el estado del tablero con las piezas actualmente 
	 * colocadas en formato
	 * 
	 */
	
	public String aTexto() {
		
		String texto ="";	
		texto+=7;
		
		for (int i=0;i<(tablero.length);i++) {
			for (int j=0;j<(tablero.length);j++) {
				
				Celda celda = tablero[i][j];
			
				
				if(celda.estaVacia()) {
					texto += "-";
				}else {
				
					Pieza pieza= celda.consultarPieza();
					TipoPieza tipoPieza = pieza.consultarTipoPieza();
					char caracter =tipoPieza.toChar();
					texto += caracter;
				}
				
			}
			if(6-i>=1) {
			texto+= (char)6-i;	
			}
			texto +="\n";
			
		}
		for(int q=0;q<7;q++) {
			texto+=(char)('a'+q);
		}
		return texto;	
	}
	

	/**
	 * Devuelve un clon en profundidad del tablero actual.
	 * 
	 */
	
	public Tablero clonar() {
		 
		Tablero tableroClon = new Tablero();
	       
			for (int i = 0; i < 7; i++) {
	            for (int j = 0; j < 7; j++) {
	                tableroClon.tablero[i][j] = this.tablero[i][j].clonar();
	            }
	        }
	        return tableroClon;
	}
	
	
	/**
	 * Metodo que coloca en la coordenada indicada la pieza pasada como argumento.
	 * 
	 * Si las coordenadas no están en el tablero o la pieza valiera nulo,
	 * no se hace nada.
	 * 
	 * @param pieza, pieza a colocar
	 * @param coordenada , coordenada donde se quiere colocar l pieza
	 */
	
	public void colocar(Pieza pieza,Coordenada coordenada) {
		 
		
		if(coordenada != null) {
			int fil =coordenada.fila();
			int col= coordenada.columna();
		
			if(fil >= 0 && fil <7 && col >= 0 && col < 7 && pieza!= null&& pieza.consultarColor()!=null && pieza.consultarTipoPieza()!= null) {
				tablero [fil][col].colocar(pieza) ;
		}
	 }
	} 
	
	
	/**
	 * devuelve un clon en profundidad de la celda con las coordenadas indicadas. 
	 * 
	 * Si las coordenadas no están en el tablero devuelve un valor null.
	 * 
	 * @param coordenada, coordenada de la celda que quiero clonar 
	 * 
	 * @return null, lo devuelve si las coordenadas no están en el tablero
	 * @return la celda clonada 
	 */
	
	public Celda consultarCelda(Coordenada coordenada) {
		 
		 int fila = coordenada.fila();
		 int columna = coordenada.columna();

		    if (fila >= 0 && fila < 7 && columna >= 0 && columna < 7) {
		        return this.tablero[fila][columna].clonar();
		    } else {
		        
		        return null;
		    }
		}
	
	
	/**
	 * devuelve un array de una dimensión, con clones en profundidad 
	 * de todas las celdas del tablero.
	 * 
	 * 
	 * @return celdasClonadas, Array con los clones de las celdas de tablero.
	 */
	
	public Celda[] consultarCeldas() {
		   
		 Celda[] celdasClonadas;
		 celdasClonadas= new Celda[49];
		 int contador = 0;
		    for (int i = 0; i < 7; i++) {
		        for (int j = 0; j < 7; j++) {
		            celdasClonadas[contador] = this.tablero[i][j].clonar();
		            contador++;
		        }
		    }

		    return celdasClonadas;
		}
	
	
	/**
	 * devuelve un array de una dimensión con clones en profundidad,
	 * de todas las celdas contiguas a la coordenada dada.
	 * 
	 * @param coordenada, coordenada de la celda que quiero sacar sus celdas contiguas. 
	 * 
	 * @return CoordenadasContiguas, devuelve las celdas contiguas a la coordenada dada.
	 * 
	 */
	
	public Celda[] consultarCeldasContiguas(Coordenada coordenada) {
		
		int fila= coordenada.fila();
		int columna=coordenada.columna();
		int c=0;
	
		if(fila < 0 || fila >=7 || columna <0 || columna >= 7 ) {
			Celda[]CoordenadasContiguas;
			CoordenadasContiguas = new Celda[0];
			return CoordenadasContiguas;
		}	
		
		if((fila-1)>=0) {
			c++;		
		}
		if((fila+1)<=6) {
			c++;
		}
		if((columna-1)>=0) {
			c++;
		}
		if((columna+1)<=6) {
			c++;
		}
		
		/*Con esto inicializamos el array para que tenga la longitud deseada para cada caso*/
		
		Celda[]CoordenadasContiguas;
		CoordenadasContiguas = new Celda[c];
		c=c-c;
		
		
	
		if((fila-1)>=0) {
			
			CoordenadasContiguas[c] = tablero[fila-1][columna].clonar();
        	c++;
        		
		}
		
		if((fila+1)<=6) {
			
			
			CoordenadasContiguas[c] = tablero[fila+1][columna].clonar();
        	c++;
        	
			
		}
		if((columna-1)>=0) {

			
			CoordenadasContiguas[c] = tablero[fila][columna-1].clonar();
        	c++;
		}
		if((columna+1)<=6) {
			
			
			CoordenadasContiguas[c] = tablero[fila][columna+1].clonar();
        	c++;
		}
		
		return CoordenadasContiguas;
	}	
	
	
	/**
	 * devuelve un array de una dimensión con clones en profundidad,
	 * de todas las celdas contiguas verticales a la coordenada dada.
	 * 
	 * @param coordenada, coordenada de la celda que quiero sacar sus celdas contiguas. 
	 * 
	 * @return CoordenadasContiguas, devuelve las celdas contiguas verticales a la coordenada dada.
	 * 
	 */
    
	public Celda[] consultarCeldasContiguasEnVertical(Coordenada coordenada) {
		if(coordenada!= null) {
		
		int fila= coordenada.fila();
		int columna=coordenada.columna();
		int c=0;
		
		if(fila < 0 || fila >=7 || columna <0 || columna >= 7 ) {
			Celda[]CoordenadasContiguas;
			CoordenadasContiguas = new Celda[0];
			return CoordenadasContiguas;
		}	
		
		if((fila-1)>=0) {
			c++;
		}
		if((fila+1)<=6) {
			c++;
		}
		
		Celda[]CoordenadasContiguasVertical;
		CoordenadasContiguasVertical = new Celda[c];
		c=c-c;
	
	
		if((fila-1)>=0) {

			CoordenadasContiguasVertical[c] = tablero[fila-1][columna].clonar();
        	c++;
        	
		}
		if((fila+1)<=6) {
			
			CoordenadasContiguasVertical[c] = tablero[fila+1][columna].clonar();
        	c++;
      
		}
	
		return CoordenadasContiguasVertical;		
		}
		return new Celda[0];
	}
	
	
	/**
	 * devuelve un array de una dimensión con clones en profundidad,
	 * de todas las celdas contiguas horizontales a la coordenada dada.
	 * 
	 * @param coordenada, coordenada de la celda que quiero sacar sus celdas contiguas. 
	 * 
	 * @return CoordenadasContiguas, devuelve las celdas contiguas horizontales a la coordenada dada.
	 * 
	 */
	
	public Celda[] consultarCeldasContiguasEnHorizontal(Coordenada coordenada) {
		
		int fila= coordenada.fila();
		int columna=coordenada.columna();
		int c=0;
		
		if(fila < 0 || fila >=7 || columna <0 || columna >= 7 ) {
			Celda[]CoordenadasContiguas;
			CoordenadasContiguas = new Celda[0];
			return CoordenadasContiguas;
		}	
		
		
		if((columna-1)>=0) {
			c++;
		}
		if((columna+1)<=6) {
			c++;
		}
		
		Celda[]CoordenadasContiguasHorizontal;
		CoordenadasContiguasHorizontal = new Celda[c];
		c=c-c;
	
	
		if((columna-1)>=0) {

        	CoordenadasContiguasHorizontal[c] = tablero[fila][columna - 1].clonar();
        	c++;
        	
		}
		if((columna+1)<=6) {
			
        	CoordenadasContiguasHorizontal[c] = tablero[fila][columna + 1].clonar();
        	c++;	
		}
		
		return CoordenadasContiguasHorizontal;		
			
		
	}
	
	
	/**
	 * devuelve el numero de piezas que hay en el tablero de un tipo indicado
	 * 
	 * @param tipoPieza, Tipo de piezas ue tenemos que buscar cuantas hay en el tablero. 
	 * 
	 * @return contador, numero de piezas que hay en tablero de ese tipo.
	 * 
	 */
	
	public int consultarNumeroPiezas(TipoPieza tipoPieza) {
	    int contador = 0;
	    for (int i = 0; i < 7; i++) {
	        for (int j = 0; j < 7; j++) {
	            if (!tablero[i][j].estaVacia() && tablero[i][j].consultarPieza().consultarTipoPieza() == tipoPieza) {
	                contador++;
	            }
	        }
	    }

	    return contador;
	}
	
	
	/**
	 * devuelve el numero de columnas que hay en el tablero 
	 * 
	 * @return contador, numero de columnas que hay.
	 * 
	 */
	
	public int consultarNumeroColumnas() {
		 
		int contador=0;
		for(int i=0;i<7;i++){
				if(tablero[0][i]!= null) {
					
					contador++;
			}
		}
		 return contador;
	 }
	
	
	/**
	 * Metodo quedevuelve el numero de filas que hay en el tablero 
	 * 
	 * @return contador, numero de filas que hay.
	 * 
	 */
	
	public int consultarNumeroFilas() {
		int contador=0;
		for(int i=0;i<7;i++){
				if(tablero[i][0]!= null) {
					
					contador++;
			}
		}
		 return contador;
	 }
		
	
	/**
	 * elimina la pieza en la celda con la coordenada indicada.
	 * 
	 * @param coordenada, coordenada en la que esta la pieza que quiero eliminar
	 * 
	 */
	
	public void eliminarPieza(Coordenada coordenada) {
		 
		if(coordenada != null) {
		 	int fil =coordenada.fila();
			int col= coordenada.columna();
			if(fil >= 0 && fil <7 && col >= 0 && col < 7 ) {
				Celda celda = tablero[coordenada.fila()][coordenada.columna()];
				if (celda != null) {
	                celda.eliminarPieza();
	            }
			}
		}
	 }
	
	
	
	/**
	 * Metodo que devuelve la referencia a la celda con la coordenada indicada
	 * 
	 * @param coordenada , coordenada de la celda que quiero obtener
	 * 
	 * @return celda que quiero obtener
	 * @return null,si la coordenada no está en el tablero
	 * 
	 */
	
	public Celda obtenerCelda(Coordenada coordenada) {
		 
		int fil =coordenada.fila();
		int col= coordenada.columna();
		if(fil >= 0 && fil <7 && col >= 0 && col < 7) {
	
			return tablero[fil][col];
		} 
		 
		 return null;
	 }
	 
	
	 
	@Override
	public String toString() {
		return "Tablero [tablero=" + Arrays.toString(tablero) + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(tablero);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tablero other = (Tablero) obj;
		return Arrays.deepEquals(tablero, other.tablero);
	}
	 
	 
	 
	
	
	 
	
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	


	
	 
	 
	 
	 
	 
	 
	
	
	
	
	
}