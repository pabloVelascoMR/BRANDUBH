package brandubh.modelo;
import java.util.Objects;
import brandubh.util.Color;
import brandubh.util.TipoPieza;


/**
 * Piezas del juego brandubh.
 * 
 * @author Pablo_Velasco_Martin
 * 
 * @see brandubh.util
 * @see java.util
 */


public class Pieza{
	
	/* variable TipoPieza que alamcena el tipo de pieza que queremos que sea*/
	private TipoPieza tipoPieza;
	
	
	/**
	 * Constructor crea una pieza para colocar en tablero
	 * 
	 * @param tipoPieza, nos dice de que tipo tiene que ser la pieza que vamos a 
	 * inicializar
	 * 
	 */
	
	public Pieza(TipoPieza tipoPieza) {
		
		this.tipoPieza =tipoPieza;
	}
	
	
	/**
	 * Devuelve el color de la pieza actual
	 * 
	 * @return Color.
	 */
	
	public Color consultarColor() {
		
		return tipoPieza.consultarColor();
		
	}
	
	
	/**
	 * Devuelve el tipo de pieza de la pieza actual
	 * 
	 * @return tipoPieza.
	 */
	
	public TipoPieza consultarTipoPieza() {
		
		return tipoPieza;
	}
	
	
	/**
	 * Devuelve un clon en profundidad de la pieza
	 * 
	 * @return pieza, Clon de pieza.
	 */
	
	public Pieza clonar() {
		
		Pieza pieza = new Pieza(tipoPieza);
		return pieza;

	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(tipoPieza);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pieza other = (Pieza) obj;
		return tipoPieza == other.tipoPieza;
	}

	@Override
	public String toString() {
		return "Pieza [tipoPieza=" + tipoPieza + "]";
	}
	
	
	
}