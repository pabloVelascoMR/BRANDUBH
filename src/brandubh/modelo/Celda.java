package brandubh.modelo;

import  brandubh.util.Coordenada;
import  brandubh.util.TipoCelda;
import java.util.Objects;
import  brandubh.util.Color;


/**
 * Celda para tablero del juego brandubh.
 * 
 * @author Pablo_Velasco_Martin
 * 
 * @see brandubh.util
 */

public class Celda{
	
	/* variable coordenda que va a almacenar la coordenada de la celda*/
	private Coordenada coordenada;
	/* variable tipoCelda que determnira el tipo de celda que es*/
	private TipoCelda tipoCelda;
	/*variable pieza*/
	private Pieza pieza;
	
	

	/**
	 * Constructor crea una celda de tipo NORMAL.
	 * 
	 * @param coordenada, Es la coordenada de la celda que queremos crear.
	 */
	
	public Celda(Coordenada coordenada) {	
		this.coordenada=coordenada;
		this.tipoCelda = TipoCelda.NORMAL;
	}
	
	
	/**
	 * Constructor crea una celda pero se puede personalizar el tipo de celda.
	 * 
	 * @param coordenada, Es la coordenada de la celda que queremos creear.
	 * @param tipoCelda, contiene el tipo de celda que queremos crear
	 */
	
	public Celda(Coordenada coordenada,TipoCelda tipoCelda) {
		this.coordenada = coordenada;
		this.tipoCelda = tipoCelda;
	}
	
	
	/**
	 * Devuelve un clon en profundidad de la celda actual.
	 * 
	 * @return celdaClonada, Es el Clon de la celda actual.
	 */
	
	public Celda clonar() { 		//devolver un clon profundo de la celda actual
        Celda celdaClonada = new Celda(this.coordenada, this.tipoCelda);

        if (this.pieza != null) {
            celdaClonada.pieza=(this.pieza.clonar());
        }

        return celdaClonada;
    }
	
	
	/**
	 * Situa una pieza en la celda actual.
	 * 
	 * @param pieza, Es la pieza que quremos colocar.
	 */
	
	public void colocar(Pieza pieza) {
		this.pieza=pieza;					
	}
	
	
	/**
	 * Elimina la pieza de la celda actual.
	 * 
	 */
	
	public void eliminarPieza() {
		this.pieza =null;
	}
	
	
	/**
	 * Comprueba que la celda actual este sin pieza.
	 * 
	 */
	
	public boolean estaVacia() {
		if(this.pieza !=null) {				
			
			return false;
		}
		return true;
	}
	
	
	/**
	 * Consulta de que color es una pieza
	 * 
	 * @return El color de la pieza
	 */
	
	public Color consultarColorDePieza() {	
		
		if(estaVacia()){
			return null;
		}	
			return pieza.consultarColor();
	}
	
	
	/**
	 * Devuelve la coordenada de la celda actual
	 * 
	 * @return coordenada.
	 */
	
	public Coordenada consultarCoordenada() {
		return coordenada;
	}
	
	
	/**
	 * Devuelve la pieza que esta en la celda actual
	 * 
	 * @return pieza.
	 */
	
	public Pieza consultarPieza() {
		return pieza;
	}
	
	
	/**
	 * Devuelve el tipo de celda que es la celda actual
	 * 
	 * @return tipoCelda.
	 */
	
	public TipoCelda consultarTipoCelda() {	
		return this.tipoCelda;
		
	}

	
	
	@Override
	public String toString() {
		return "Celda [coordenada=" + coordenada + ", tipoCelda=" + tipoCelda + ", pieza=" + pieza + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(coordenada, pieza, tipoCelda);
	}

	@Override
	public  boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Celda other = (Celda) obj;
		return Objects.equals(coordenada, other.coordenada) && Objects.equals(pieza, other.pieza)
				&& tipoCelda == other.tipoCelda;
	}

	
	
}