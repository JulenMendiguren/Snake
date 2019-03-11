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
	public static final int FPS = 60;
	
	
}
