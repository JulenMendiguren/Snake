package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;




public class Juego {

	private static Juego miJuego;
	private static Tablero elTablero;
	
	public Juego(){
		elTablero= new Tablero(DatosJuego.CASILLAS_ANCHO,DatosJuego.CASILLAS_ALTO);
	}
	
	
	public static Juego getJuego() {
		if (miJuego == null) {
			miJuego = new Juego();
		}
		return miJuego;
	}
	public void update(){
		// Aqui se hacen todos los cambios de casillas, snake, manzanas...
	}
	
	
	public void pintar(Graphics g) {		
		//modificadores.pintar(g);
		
		g.setFont(new Font("Arial", Font.BOLD, 20));
		//g.drawString(String.valueOf(jugador2.getNombre()), DatosJuego.LIMITE_IZQ + 40, 40);
		g.setColor(DatosJuego.COLOR_FONDO);
		for(int i=0;i<elTablero.getWidth();i++){
			for(int j=0;j<elTablero.getHeight();j++){
				g.setColor(new Color(ThreadLocalRandom.current().nextInt(0, 16777215 + 1)));
				g.fillRect(i*DatosJuego.LONGITUD_CASILLA, j*DatosJuego.LONGITUD_CASILLA, 
						DatosJuego.LONGITUD_CASILLA, DatosJuego.LONGITUD_CASILLA);
			}
		}
		
	}
}
