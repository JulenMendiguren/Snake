package juego;

import java.awt.Color;

public class DatosJuego {
	
	//Parametros de la ventana
	public static final int PIXELES_ALTO = 600;
	public static final int PIXELES_ANCHO = 600;
	public static final Color COLOR_FONDO = Color.GRAY;
	
	//Dimensiones de Cada casilla
	public static final int LONGITUD_CASILLA = 20;
	
	public static final int CASILLAS_ALTO = PIXELES_ALTO/LONGITUD_CASILLA;
	public static final int CASILLAS_ANCHO = PIXELES_ANCHO/LONGITUD_CASILLA;
	
	//Fps
	public static int FPS = 8;
	
	//
	public static int TIEMPO_RELOJ = 20;
	
	
	/*	VALORES DE CASILLAS
	 * 
	 * 0 = vacía
	 * 
	 * 1 = cabeza de la serpiente
	 * 
	 * 2 = cuerpo de la serpiente
	 * 
	 * 3 = manzana
	 * 
	 * 4 = manzana envenenada
	 * 
	 * 5 = Pared
	 * 
	 */
	
	public static void setFPS(int fps){
		FPS = fps;
	}
	
	
}
