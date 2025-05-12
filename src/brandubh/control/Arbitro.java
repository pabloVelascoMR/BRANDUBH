package brandubh.control;
import brandubh.modelo.Tablero;
import brandubh.util.TipoPieza;
import brandubh.util.Color;
import brandubh.modelo.Celda;
import brandubh.modelo.Jugada;
import brandubh.util.Coordenada;
import brandubh.util.Sentido;
import brandubh.util.TipoCelda;
import brandubh.modelo.Pieza;


/**
 * Arbitro para la partida de Brandubh.
 * 
 * @author Pablo_Velasco_Martin
 * @see brandubh.modelo
 * @see brandubh.control
 * @see brandubh.util
 */

public class Arbitro{
	
	/*variable Tablero almacena una referencia al tablero del juego*/
	private Tablero tablero;
	/*vaiable Jugada alamcena el numero de jugadas de la partida */
	private int Jugada;
	/*vaiable ultimoMovimetno alamacena la ultima jugada que a reliazado el usuario */
	private Jugada ultimoMovimiento;
	/*vaiable Turno alamcena el turno en el que se llega la partida */
	private Color Turno;
	
	
	/**
	 * Constructor que recibe un tablero para inicializar el arbitro
	 * 
	 * @param Tablero,
	 */
	
	public Arbitro(Tablero tablero) {
		this.tablero= tablero;
		Jugada=0;
	}
	
	
	/**
	 * Cambia el turno de la partida a blanco(def) o a nergo (ata)
	 */
	
	public void cambiarTurno() {
		
		if(Turno==Color.BLANCO) {
			Turno= Color.NEGRO;
		}else {
			Turno= Color.BLANCO;
		}
		Jugada++;
	}
	
	
	/**
	 * Coloca piezas piezas en el tablero.
	 * 
	 * Recibe un array con coordenadas y otro array con el tipo de piezas 
	 * que quiero colocar.
	 * 
	 * Coloca una pieza del tipo correcto en su coordenadada
	 * 
	 * @param tipo, Array que contiene los tipos de las piezas a colocar.
	 * @param coordenadas, Array de dos dimensiones con las coordenadas donde se quiere colocar las piezas
	 * @param turonoActual,Color para saber que turno quieres inicializar
	 */
	
	public void colocarPiezas(TipoPieza[] tipo, int[][] coordenadas,Color turnoActual) {
		for (int i = 0; i < tipo.length; i++) {
			
			int fil =coordenadas[i][0];
			int col= coordenadas[i][1];
			Coordenada coordenada= new Coordenada(fil,col);
			Pieza pieza = new Pieza(tipo[i]);
			tablero.colocar(pieza,coordenada);
			System.out.print("colocoPieza "+i);
		}	
		Turno= turnoActual;
		}
	
	
	/**
	 * Coloca las piezas para empezar la partida.
	 * 
	 * Tambien inicializa el turno a negro ya que siempre empiezan las piezas negras.
	 * 
	 */
	
	public void colocarPiezasConfiguracionInicial() {
		
		
		
		tablero.colocar(new Pieza(TipoPieza.REY), new Coordenada(3, 3));

	    // Posicionar los defensores alrededor del rey
	    tablero.colocar(new Pieza(TipoPieza.DEFENSOR),new Coordenada( 3, 2));
	    tablero.colocar(new Pieza(TipoPieza.DEFENSOR),new Coordenada( 3, 4));
	    tablero.colocar(new Pieza(TipoPieza.DEFENSOR),new Coordenada( 2, 3));
	    tablero.colocar(new Pieza(TipoPieza.DEFENSOR),new Coordenada( 4, 3));

	    // Posicionar los atacantes en las posiciones específicas
	    tablero.colocar(new Pieza(TipoPieza.ATACANTE),new Coordenada( 0, 3));
	    tablero.colocar(new Pieza(TipoPieza.ATACANTE),new Coordenada( 1, 3));
	    tablero.colocar(new Pieza(TipoPieza.ATACANTE),new Coordenada( 5, 3));
	    tablero.colocar(new Pieza(TipoPieza.ATACANTE),new Coordenada( 6, 3));
	    tablero.colocar(new Pieza(TipoPieza.ATACANTE),new Coordenada( 3, 6));
	    tablero.colocar(new Pieza(TipoPieza.ATACANTE),new Coordenada( 3, 5));
	    tablero.colocar(new Pieza(TipoPieza.ATACANTE),new Coordenada( 3, 1));
	    tablero.colocar(new Pieza(TipoPieza.ATACANTE),new Coordenada( 3, 0));

	    // Establecer el turno para el atacante (piezas negras)
	    Turno = Color.NEGRO;
	}
		
	
	/**
	 * Devuelve el numero de jugada en el que esta la partida
	 * 	 
	 * @return Jugada
	 * 
	 */
	public int consultarNumeroJugada() {	
		return Jugada;
	}
	
	
	/**
	 * Devuelve un clon profundo del tablero
	 * 
	 * @return tableroClon, Clon de tablero
	 * 
	 */
	
	public Tablero consultarTablero() {
		Tablero tableroClon= tablero.clonar();
		return tableroClon;
	}
	
	
	/**
	 * Devuelve el turno en el que esta la partida
	 * 
	 * @return Turno
	 */
	
	public Color consultarTurno() {
		return Turno;
	}
	
	
	/**
	 * Comprueba si una jugada realizada por el usuario es legal.
	 * 
	 * Esta función llama a otras 6 funciones privadas.
	 * 
	 * y comprueba que las llamadas a esas funciones sean true o flase
	 * 
	 * si cumple las 6 condiciones podemos decir que el mvimento es legal si falla
	 * alguna el movimento es ilegal
	 * 
	 * @param jugada, Jugada a comprobar con la función
	 * 
	 * @return boolean, true si el movimiento es legal y false si no lo es.
	 */
	
	public boolean esMovimientoLegal(Jugada jugada) {
		
        Celda origen = jugada.consultarOrigen();
        Celda destino = jugada.consultarDestino();
        Coordenada Co= origen.consultarCoordenada();
        Coordenada Cd= destino.consultarCoordenada();
        
        
     
        boolean movLegal=true;
        
       
        if(celdasValidas(origen,destino,Cd)== false) {	
        	movLegal=false;
        }
        if(origenConPieza(Co)== false) {
        	
        	movLegal=false;
        }
        if(destinoConPieza(Cd)== false) {
        	
        	movLegal=false;
        }

        if (Co.fila() != Cd.fila() && Co.columna() != Cd.columna()) { // movimiento es horizontal o vertical
            movLegal =false;
        }
        
        if(noEsProvincia(destino,origen)== false) {
        	movLegal= false;
        }
        
        if(CaminoLibre(origen,destino,Co,Cd)== false) {
        	
        	movLegal=false;
        }
        
       if(PiezaATrono(Cd,origen)== false) {
    	   
    	   movLegal=false;
       }
    	return movLegal;
	}
	
	
	/**
	 * Comprueba si las coordenadas de las celdas son validas
	 * 	
	 * @param origen, destino, celdas de origen y destino
	 * @param cd, coordenada de destino
	 * 
	 * @return boolean, true si estan dentro del tablero y no son null
	 * @return boolean, false si no lo cumplen 
	 * 
	 */
	private boolean celdasValidas(Celda origen,Celda destino,Coordenada Cd) {
		
		if (origen == null || destino == null) {	// coordenadas son válidas
            return false;
        }
        if (Cd.fila() < 0 || Cd.fila() > 7  || Cd.columna() < 0 || Cd.columna() > 7) {	 //  celda de destino está dentro de los límites del tablero
            return false;
        }
		
        return true;	
	}
	
	
	/**
	 * Comprueba si en la celda origen hay una pieza
	 * 	
	 * @param coordenada, coordenadad de la celda origen
	 *
	 * @return boolean, true si la celda origen tiene pieza
	 * @return boolean, false si no lo cumple
	 * 
	 */
	
	private boolean origenConPieza(Coordenada coordenada) {
		
		Pieza piezaOrigen = tablero.obtenerCelda(coordenada).consultarPieza();
        if (piezaOrigen == null || piezaOrigen.consultarColor() != Turno) { //  la celda de origen contiene una pieza del jugador actual
             return false;
        }
		return true;
	}
	
	/**
	 * Comprueba si en la celda destino esta vacia
	 * 	
	 * @param coordenada, coordenadad de la celda destino
	 *
	 * @return boolean, true si la celda destino no tiene pieza
	 * @return boolean, false si no lo cumple
	 * 
	 */
	
	private boolean destinoConPieza(Coordenada coordenada) {
		Pieza piezaDestino = tablero.obtenerCelda(coordenada).consultarPieza();
        if (piezaDestino != null) {	//  celda de destino está vacía
            return false;
        }
		return true;
	}
	
	
	/**
	 * Comprueba que el destino no es una provincia
	 * 
	 * 	
	 * @param celda, celda destino
	 * @param origen, celda origen
	 *
	 * @return boolean, true si la celda destino no es provincia
	 * @return boolean, false si no lo cumple
	 * 
	 */
	
	private boolean noEsProvincia( Celda celda,Celda origen) {

	    Coordenada Cprovincia1 = new Coordenada(0, 0);
	    Celda provincia1 = new Celda(Cprovincia1,TipoCelda.PROVINCIA);
	    Coordenada Cprovincia2 = new Coordenada(6, 0);
	    Celda provincia2 = new Celda(Cprovincia2,TipoCelda.PROVINCIA);
	    Coordenada Cprovincia3 = new Coordenada(0, 6);
	    Celda provincia3 = new Celda(Cprovincia3,TipoCelda.PROVINCIA);
	    Coordenada Cprovincia4 = new Coordenada(6, 6);
	    Celda provincia4 = new Celda(Cprovincia4,TipoCelda.PROVINCIA);

	   
	    if (celda.equals(provincia1) || celda.equals(provincia2) || celda.equals(provincia3) || celda.equals(provincia4)) {
	        
	    	if(origen.consultarPieza().consultarTipoPieza()!= TipoPieza.REY) {
	    		return false;
	    	}
	    	
	    }

	    return true;
	}
	
	/**
	 * Comprueba que entre el origen y el destino no haya ninguna piezo
	 * 
	 * 	
	 * @param o,d, celda origen y celda destino
	 * @param Corigen,Cdestino, coordenada origen y coordenada destino
	 *
	 * @return boolean, true si no hay ningua pieza entremedias
	 * @return boolean, false si no lo cumple
	 * 
	 */
	private boolean CaminoLibre(Celda o,Celda d,Coordenada Corigen ,Coordenada Cdestino) {
		
		
		Jugada Camino= new Jugada(o,d);
		int nCeldasDeDistanciaV=Math.abs((Corigen.fila() - Cdestino.fila()));
		int nCeldasDeDistanciaH=Math.abs((Corigen.columna() - Cdestino.columna()));
		
		if(Camino.consultarSentido()== Sentido.VERTICAL_N) {
			int c=1;
			for(int i=0;i<nCeldasDeDistanciaV;i++) {
				Coordenada CoordenadaCamino= new Coordenada((Corigen.fila()-c),Corigen.columna());
				Celda celdaCamino= tablero.consultarCelda(CoordenadaCamino);
				c++;
				if(celdaCamino.consultarPieza()!= null) {
					if((celdaCamino.consultarPieza().consultarTipoPieza()== TipoPieza.ATACANTE)||(celdaCamino.consultarPieza().consultarTipoPieza()== TipoPieza.DEFENSOR)) {
						return false;
					}
					
				}
			}
			
		}
		
		if(Camino.consultarSentido()== Sentido.VERTICAL_S) {
			int c=1;
			for(int i=0;i<nCeldasDeDistanciaV;i++) {
				Coordenada CoordenadaCamino= new Coordenada((Corigen.fila()+c),Corigen.columna());
				Celda celdaCamino= tablero.consultarCelda(CoordenadaCamino);
				c++;
				if(celdaCamino.consultarPieza()!= null) {
					if((celdaCamino.consultarPieza().consultarTipoPieza()== TipoPieza.ATACANTE)|| (celdaCamino.consultarPieza().consultarTipoPieza()== TipoPieza.DEFENSOR)) {
						return false;
					}
				}
			}
			
		}
		if(Camino.consultarSentido()== Sentido.HORIZONTAL_E) {
			int c=1;
			for(int i=0;i<nCeldasDeDistanciaH;i++) {
				Coordenada CoordenadaCamino= new Coordenada((Corigen.fila()),Corigen.columna()+c);
				Celda celdaCamino= tablero.consultarCelda(CoordenadaCamino);
				c++;
				if(celdaCamino.consultarPieza()!= null) {
					if((celdaCamino.consultarPieza().consultarTipoPieza()== TipoPieza.ATACANTE)|| (celdaCamino.consultarPieza().consultarTipoPieza()== TipoPieza.DEFENSOR)) {
						return false;
					}
				}
			}
			
			
		}
		if(Camino.consultarSentido()== Sentido.HORIZONTAL_O) {
			int c=1;
			for(int i=0;i<nCeldasDeDistanciaH;i++) {
				Coordenada CoordenadaCamino= new Coordenada(Corigen.fila(),Corigen.columna()-c);
				Celda celdaCamino= tablero.consultarCelda(CoordenadaCamino);
				c++;
				if(celdaCamino.consultarPieza()!= null) {
					if((celdaCamino.consultarPieza().consultarTipoPieza()== TipoPieza.ATACANTE)|| (celdaCamino.consultarPieza().consultarTipoPieza()== TipoPieza.DEFENSOR)) {
						return false;
					}
				}
			}
			
			
		}
		return true;
}	
		
	/**
	 * Comprueba que un defensor o atacante  no pueda entrar en el trono
	 * 
	 * 	
	 * @param destino, celda destino
	 * @param origen, celda origen
	 *
	 * @return boolean, true si no lo cumple
	 * @return boolean, false si el destino de una pieza atacante o defensor es el trono
	 * 
	 */
	
	private boolean PiezaATrono(Coordenada destino, Celda origen) {
		
		if(destino.columna()== 3 && destino.fila()==3) {
			if(origen.consultarPieza()!= null) {
				if(origen.consultarPieza().consultarTipoPieza()== TipoPieza.DEFENSOR|| origen.consultarPieza().consultarTipoPieza()== TipoPieza.ATACANTE) {
					return false;
				}
			}	
		}
		return true;
	}
	
	
	/**
	 * Comprueba si con el último movimiento se acaba la partida con victoria para los atacantes.
	 * Para esto el rey debe ser capturado
	 * 
	 * Esta función llama a dos funciones privadas
	 * 
	 * @return boolean, true si gana el atacante.
	 */
		
	
	public boolean haGanadoAtacante() {
		

		Celda celdaUltimoMovimiento = ultimoMovimiento.consultarDestino();
		
		Pieza piezaMovida= ultimoMovimiento.consultarOrigen().consultarPieza();
		
		if(piezaMovida==null) {
			piezaMovida=ultimoMovimiento.consultarDestino().consultarPieza();
		}
		
		Celda [] contiguasUltimoMovimiento= tablero.consultarCeldasContiguas(celdaUltimoMovimiento.consultarCoordenada());
		
		int n = contiguasUltimoMovimiento.length;

		for( int i=0;i<n;i++) {
			
			if(contiguasUltimoMovimiento[i].consultarPieza()!= null ) {
				if(contiguasUltimoMovimiento[i].consultarPieza().consultarTipoPieza()!= piezaMovida.consultarTipoPieza()) {
					
					Celda celdaPosibleCaptura = contiguasUltimoMovimiento[i];
					
					Celda[] contiguasPosibleCapturaH = tablero.consultarCeldasContiguasEnHorizontal(celdaPosibleCaptura.consultarCoordenada());
					Celda[] contiguasPosibleCapturaV = tablero.consultarCeldasContiguasEnVertical(celdaPosibleCaptura.consultarCoordenada());
					
					int nH = contiguasPosibleCapturaH.length;
					int nV = contiguasPosibleCapturaV.length;
				
					
					if(ganadoConDosPiezas(nV,nH,contiguasPosibleCapturaH,contiguasPosibleCapturaV,celdaPosibleCaptura,piezaMovida)) {
						return true;
					}
					
					if(ganadoConPiezaProvincia(nV,nH,contiguasPosibleCapturaH,contiguasPosibleCapturaV,celdaPosibleCaptura,piezaMovida)) {
						return true;
					}
					
					
					
				}
			}	
		}
		return false;
	}
	
	/**
	 * Comprueba si con el último movimiento se captura al rey con dos piezas.
	 * 
	 * Para esto el rey debe estar entre dos piezas negras
	 *
	 * @param nH,nV numero de celdas contiguas que tiene la celda donde se puede hacer la captura
	 * @param contiguasPosibleCapturaH,contiguasPosibleCapturaV arrays con las celdas contiguas en horizontal y vertical
	 * @param celdaPosibleCaptura celda en la que se esta comprobando si es puede comer
	 * @param piezaMovida pieza que se ha movido en el ultimo movimento
	 * 
	 * @return boolean, true si se puede comer el rey.
	 * @return boolean, false si no se puede comer el rey.
	 */
	
	private boolean ganadoConDosPiezas(int nV, int nH, Celda[] contiguasPosibleCapturaH, Celda[] contiguasPosibleCapturaV,Celda celdaPosibleCaptura, Pieza piezaMovida) {
			
		if(nV==2) {
				
			int eliminarV=0;
						
			for(int v=0;v<nV;v++) {
							
				if(contiguasPosibleCapturaV[v].consultarPieza()!= null) {
					if(contiguasPosibleCapturaV[v].consultarPieza().consultarTipoPieza()== piezaMovida.consultarTipoPieza()) {
							
							eliminarV++;
					}
				}
			}
			if(eliminarV==2) {
				if(celdaPosibleCaptura.consultarPieza().consultarTipoPieza()==TipoPieza.REY) {
					return true;
				}
			}
						
		}
			
					
		if(nH==2) {
				
			int eliminarH=0;
						
			for(int h=0;h<nH;h++) {
							
				if(contiguasPosibleCapturaH[h].consultarPieza()!= null) {
					if(contiguasPosibleCapturaH[h].consultarPieza().consultarTipoPieza()== piezaMovida.consultarTipoPieza()) {
								
							eliminarH++;
					}
				}
			}
				
			if(eliminarH==2) {
				if(celdaPosibleCaptura.consultarPieza().consultarTipoPieza()==TipoPieza.REY) {
					return true;
				}
					
					
			}
						
		}
		return false;
	}
		
		
	/**
	 * Comprueba si con el último movimiento se captura al rey con pieza y provincia.
	 * 
	 * Para esto el rey debe estar entre dos piezas negras
	 *
	 * @param nH,nV numero de celdas contiguas que tiene la celda donde se puede hacer la captura
	 * @param contiguasPosibleCapturaH,contiguasPosibleCapturaV arrays con las celdas contiguas en horizontal y vertical
	 * @param celdaPosibleCaptura celda en la que se esta comprobando si es puede comer
	 * @param piezaMovida pieza que se ha movido en el ultimo movimento
	 * 
	 * @return boolean, true si se puede comer el rey.
	 * @return boolean, false si no se puede comer el rey.
	 */	
	
	private boolean ganadoConPiezaProvincia(int nV, int nH,Celda[] contiguasPosibleCapturaH, Celda[] contiguasPosibleCapturaV,Celda celdaPosibleCaptura,Pieza pieza) {
			
			Boolean  ComerPiezaH = false;
			Boolean ComerPiezaV = false;
			int CeldaConProvinciaHPosicion=0;
			int CeldaConProvinciaVPosicion=0;
			
			
			
			for(int i=0;i<nH;i++) {
				
				if(contiguasPosibleCapturaH[i].consultarTipoCelda()==TipoCelda.PROVINCIA) {
					
					ComerPiezaH= true;
					CeldaConProvinciaHPosicion=i;
					
					}	
			}
			
			if(CeldaConProvinciaHPosicion==0 && ComerPiezaH== true) {
				if(contiguasPosibleCapturaH[1].consultarPieza()!= null) {
					if(contiguasPosibleCapturaH[1].consultarPieza().consultarTipoPieza()== pieza.consultarTipoPieza()) {
						
						if(celdaPosibleCaptura.consultarPieza().consultarTipoPieza()==TipoPieza.REY) {
							return true;
						}
						
						}
				}
			}
			
			if(CeldaConProvinciaHPosicion==1 && ComerPiezaH== true) {
				
				if(contiguasPosibleCapturaH[0].consultarPieza()!= null) {
					if(contiguasPosibleCapturaH[0].consultarPieza().consultarTipoPieza()== pieza.consultarTipoPieza()) {
						if(celdaPosibleCaptura.consultarPieza().consultarTipoPieza()==TipoPieza.REY) {
							return true;
						}	
						}
				}
				
			}
			
				
				
			for(int j=0;j<nV;j++) {
				
				if(contiguasPosibleCapturaV[j].consultarTipoCelda()==TipoCelda.PROVINCIA) {
					
					ComerPiezaV= true;
					CeldaConProvinciaVPosicion=j;
				
					}	

			}
			
			if(CeldaConProvinciaVPosicion==0 && ComerPiezaV== true) {
				if(contiguasPosibleCapturaV[1].consultarPieza()!= null) {
					if(contiguasPosibleCapturaV[1].consultarPieza().consultarTipoPieza()== pieza.consultarTipoPieza()) {
						
						if(celdaPosibleCaptura.consultarPieza().consultarTipoPieza()==TipoPieza.REY) {
							return true;
						}
					}
				}
			}
			
			if(CeldaConProvinciaVPosicion==1 && ComerPiezaV== true) {
				
				if(contiguasPosibleCapturaV[0].consultarPieza()!= null) {
					if(contiguasPosibleCapturaV[0].consultarPieza().consultarTipoPieza()== pieza.consultarTipoPieza()) {
							
						if(celdaPosibleCaptura.consultarPieza().consultarTipoPieza()==TipoPieza.REY) {
							return true;
						}
					}
				}
				
			}
			
			
			return false;
	}
	

	/**
	 * Comprueba que el rey haya llegado a una de las provincias 
	 * y por lo tanto los defensores hayan ganado la partida
	 * 
	 * @return boolean, true si ganan los defensores.
	 * @return boolean , false si no ganan los defensores
	 */		
					
			
	public boolean haGanadoRey() {
				
		for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
            	
            	Coordenada coordenada = new Coordenada(i,j);
            	Celda celda =tablero.obtenerCelda(coordenada);
            	
            	
            	if (celda.consultarTipoCelda()==TipoCelda.PROVINCIA) {
            		if(celda.consultarPieza()!= null) {
            			if(celda.consultarPieza().consultarTipoPieza()== TipoPieza.REY) {
            				return true;
            			}
            		}  	
            	}
            }} 	
		return false;
	}
	
	
	/**
	 * Mueve una pieza de su celda origen a la de destino.
	 * 
	 * Almacena el movimiento y lo guarda como último movimiento realizado para luego poder consultarlo
	 * 
	 * @param jugada, jugada a realizar con esta función
	 */
	
	public void mover (Jugada jugada) {
		
		Coordenada coordenadaOrigen = new Coordenada(jugada.consultarOrigen().consultarCoordenada().fila(),jugada.consultarOrigen().consultarCoordenada().columna());
		Celda celdaOrigen = tablero.obtenerCelda(coordenadaOrigen);
		
		Coordenada coordenadaDestino = new Coordenada(jugada.consultarDestino().consultarCoordenada().fila(),jugada.consultarDestino().consultarCoordenada().columna());
		Celda celdaDestino = tablero.obtenerCelda(coordenadaDestino);
		
		
		Pieza piezaJugada= new Pieza(celdaOrigen.consultarPieza().consultarTipoPieza());
		
		tablero.eliminarPieza(celdaOrigen.consultarCoordenada());
		tablero.colocar(piezaJugada,celdaDestino.consultarCoordenada());
		
		this.ultimoMovimiento = jugada;
		Jugada++;
		
		
	}
	
	
	/**
	 * Comprueba si el ultimo movieento se ha capturado una pieza.
	 * 
	 * Esta función llama a dosfunciones privadas
	 * 
	 */
	
	public void realizarCapturasTrasMover() {
		
		Celda celdaUltimoMovimiento = ultimoMovimiento.consultarDestino();
		Pieza piezaMovida= ultimoMovimiento.consultarOrigen().consultarPieza();
		if(piezaMovida==null) {
			piezaMovida=ultimoMovimiento.consultarDestino().consultarPieza();
		}
		Celda [] contiguasUltimoMovimiento= tablero.consultarCeldasContiguas(celdaUltimoMovimiento.consultarCoordenada());
		
		int n = contiguasUltimoMovimiento.length;

		for( int i=0;i<n;i++) {
			
			if(contiguasUltimoMovimiento[i].consultarPieza()!= null )  {
				if(contiguasUltimoMovimiento[i].consultarPieza().consultarTipoPieza()!= piezaMovida.consultarTipoPieza()) {
					
					Celda celdaPosibleCaptura = contiguasUltimoMovimiento[i];
					
					Celda[] contiguasPosibleCapturaH = tablero.consultarCeldasContiguasEnHorizontal(celdaPosibleCaptura.consultarCoordenada());
					Celda[] contiguasPosibleCapturaV = tablero.consultarCeldasContiguasEnVertical(celdaPosibleCaptura.consultarCoordenada());
					
					int nH = contiguasPosibleCapturaH.length;
					int nV = contiguasPosibleCapturaV.length;
					
					CapturaConDosPiezas(nV,nH,contiguasPosibleCapturaH,contiguasPosibleCapturaV,celdaPosibleCaptura,piezaMovida);
					
					CapturaConPiezaProvincia(nV,nH,contiguasPosibleCapturaH,contiguasPosibleCapturaV,celdaPosibleCaptura,piezaMovida);
					
					
				}
			}	
		}	
	}	
	
	
	/**
	 * Comprueba si con el último movimiento se captura a una pieza 
	 * con dos piezas contrarias.
	 * 
	 * si se da el caso que se captura unna pieza  se elimina de tablero esa pieza
	 *
	 * @param nH,nV numero de celdas contiguas que tiene la celda donde se puede hacer la captura
	 * @param contiguasPosibleCapturaH,contiguasPosibleCapturaV arrays con las celdas contiguas en horizontal y vertical
	 * @param celdaPosibleCaptura celda en la que se esta comprobando si es puede comer
	 * @param piezaMovida pieza que se ha movido en el ultimo movimento
	 * 
	 * 
	 * 
	 */
	
		private void CapturaConDosPiezas(int nV, int nH, Celda[] contiguasPosibleCapturaH, Celda[] contiguasPosibleCapturaV,Celda celdaPosibleCaptura, Pieza piezaMovida) {
			
			if(nV==2) {
				
				int eliminarV=0;
						
				for(int v=0;v<nV;v++) {
							
					if(contiguasPosibleCapturaV[v].consultarPieza()!= null) {
						if(contiguasPosibleCapturaV[v].consultarPieza().consultarTipoPieza()== piezaMovida.consultarTipoPieza()) {
							
								eliminarV++;
						}
					}
				}
						if(eliminarV==2) {
							if(celdaPosibleCaptura.consultarPieza().consultarTipoPieza()!=TipoPieza.REY) {
								tablero.eliminarPieza(celdaPosibleCaptura.consultarCoordenada());
							}
						}
						
			}
			
			
					
			if(nH==2) {
				
				int eliminarH=0;
						
				for(int h=0;h<nH;h++) {
							
					if(contiguasPosibleCapturaH[h].consultarPieza()!= null) {
						if(contiguasPosibleCapturaH[h].consultarPieza().consultarTipoPieza()== piezaMovida.consultarTipoPieza()) {
								
								eliminarH++;
							}
					}
				}
				
				if(eliminarH==2) {
					if(celdaPosibleCaptura.consultarPieza().consultarTipoPieza()!=TipoPieza.REY) {
						tablero.eliminarPieza(celdaPosibleCaptura.consultarCoordenada());
					}
					
					
				}
						
			}
		}
		
		
		/**
		 * Comprueba si con el último movimiento se captura a una pieza 
		 * con una pieza contraeia y una provincia.
		 * 
		 * si se da el caso que se captura una pieza se elimina de tablero esa pieza
		 *
		 * @param nH,nV numero de celdas contiguas que tiene la celda donde se puede hacer la captura
		 * @param contiguasPosibleCapturaH,contiguasPosibleCapturaV arrays con las celdas contiguas en horizontal y vertical
		 * @param celdaPosibleCaptura celda en la que se esta comprobando si es puede comer
		 * @param piezaMovida pieza que se ha movido en el ultimo movimento
		 * 
		 * 
		 */
		
		private void CapturaConPiezaProvincia(int nV, int nH,Celda[] contiguasPosibleCapturaH, Celda[] contiguasPosibleCapturaV,Celda celdaPosibleCaptura,Pieza pieza) {
			
			Boolean  ComerPiezaH = false;
			Boolean ComerPiezaV = false;
			int CeldaConProvinciaHPosicion=0;
			int CeldaConProvinciaVPosicion=0;
			
			
			
			for(int i=0;i<nH;i++) {
				
				if(contiguasPosibleCapturaH[i].consultarTipoCelda()==TipoCelda.PROVINCIA) {
					
					ComerPiezaH= true;
					CeldaConProvinciaHPosicion=i;
					
					}	
			}
			
			if(CeldaConProvinciaHPosicion==0 && ComerPiezaH== true) {
				if(contiguasPosibleCapturaH[1].consultarPieza()!= null) {
					if(contiguasPosibleCapturaH[1].consultarPieza().consultarTipoPieza()== pieza.consultarTipoPieza()) {
						
						if(celdaPosibleCaptura.consultarPieza().consultarTipoPieza()!=TipoPieza.REY) {
							tablero.eliminarPieza(celdaPosibleCaptura.consultarCoordenada());
						}
						
						}
				}
			}
			
			if(CeldaConProvinciaHPosicion==1 && ComerPiezaH== true) {
				
				if(contiguasPosibleCapturaH[0].consultarPieza()!= null) {
					if(contiguasPosibleCapturaH[0].consultarPieza().consultarTipoPieza()== pieza.consultarTipoPieza()) {
						if(celdaPosibleCaptura.consultarPieza().consultarTipoPieza()!=TipoPieza.REY) {
							tablero.eliminarPieza(celdaPosibleCaptura.consultarCoordenada());
						}	
						}
				}
				
			}
			
				
				
			for(int j=0;j<nV;j++) {
				
				if(contiguasPosibleCapturaV[j].consultarTipoCelda()==TipoCelda.PROVINCIA) {
					
					ComerPiezaV= true;
					CeldaConProvinciaVPosicion=j;
				
					}	

			}
			
			if(CeldaConProvinciaVPosicion==0 && ComerPiezaV== true) {
				if(contiguasPosibleCapturaV[1].consultarPieza()!= null) {
					if(contiguasPosibleCapturaV[1].consultarPieza().consultarTipoPieza()== pieza.consultarTipoPieza()) {
						
						if(celdaPosibleCaptura.consultarPieza().consultarTipoPieza()!=TipoPieza.REY) {
							tablero.eliminarPieza(celdaPosibleCaptura.consultarCoordenada());
						}
					}
				}
			}
			
			if(CeldaConProvinciaVPosicion==1 && ComerPiezaV== true) {
				
				if(contiguasPosibleCapturaV[0].consultarPieza()!= null) {
					if(contiguasPosibleCapturaV[0].consultarPieza().consultarTipoPieza()== pieza.consultarTipoPieza()) {
							
						if(celdaPosibleCaptura.consultarPieza().consultarTipoPieza()!=TipoPieza.REY) {
							tablero.eliminarPieza(celdaPosibleCaptura.consultarCoordenada());
						}
					}
				}
				
			}
			
			
			
		}
		
			
				
		}	
				
			
				
			
				
				
				
				
			

	

