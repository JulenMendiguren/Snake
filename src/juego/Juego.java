package juego;

import java.awt.Font;
import java.awt.Graphics;




public class Juego {

	private static Juego miJuego;
	
	
	public Juego(){
		//TODO: 
	}
	
	
	public static Juego getJuego() {
		if (miJuego == null) {
			miJuego = new Juego();
		}
		return miJuego;
	}
	
	
	public void pintar(Graphics g) {

		//modificadores.pintar(g);

		g.setFont(new Font("Arial", Font.BOLD, 20));
		//g.drawString(String.valueOf(jugador2.getNombre()), DatosJuego.LIMITE_IZQ + 40, 40);

	}
}
