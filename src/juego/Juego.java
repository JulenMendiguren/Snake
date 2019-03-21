package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Juego {

	private static Juego miJuego;
	private static Tablero elTablero;
	private boolean izquierda = false;
    private boolean derecha = false;
    private boolean arriba = true;
    private boolean abajo = false;

	public Juego() {
		elTablero = new Tablero(DatosJuego.CASILLAS_ANCHO, DatosJuego.CASILLAS_ALTO);
		//elTablero.generarManzana();
	}

	public static Juego getJuego() {
		if (miJuego == null) {
			miJuego = new Juego();
		}
		return miJuego;
	}
	
	private boolean updateSerpiente() {
		boolean resultado=false;
        if (izquierda) {
        		resultado=elTablero.moverSerpiente("izquierda");
        }
        else if (derecha) {
        		resultado=elTablero.moverSerpiente("derecha");	
        }
        else if (arriba) {
        		resultado=elTablero.moverSerpiente("arriba");
        }
        else if (abajo) {
        		resultado=elTablero.moverSerpiente("abajo");
        }
        return resultado;
    }
	
	public void keyPressed(KeyEvent e) {

        int tecla = e.getKeyCode();

        if ((tecla == KeyEvent.VK_A) && (!derecha)) {
            izquierda = true;
            arriba = false;
            abajo = false;
        }

        if ((tecla == KeyEvent.VK_D) && (!izquierda)) {
            derecha = true;
            arriba = false;
            abajo = false;
        }

        if ((tecla == KeyEvent.VK_W) && (!abajo)) {
            arriba = true;
            derecha = false;
            izquierda = false;
        }

        if ((tecla == KeyEvent.VK_S) && (!arriba)) {
            abajo = true;
            derecha = false;
            izquierda = false;
        }
    }

	public boolean update() {
		boolean bukatuDa = false;
		bukatuDa=updateSerpiente();
		return bukatuDa;
	}

	public void pintar(Graphics g) {
		elTablero.pintarTablero(g);
	}

}
