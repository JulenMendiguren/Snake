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
    private String ultimaDireccion = "arriba";

	public Juego() {
		elTablero = new Tablero(DatosJuego.CASILLAS_ANCHO, DatosJuego.CASILLAS_ALTO);
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

        if ((tecla == KeyEvent.VK_A) && (!derecha) && ultimaDireccion!="derecha") {
            izquierda = true;
            arriba = false;
            abajo = false;
        }

        if ((tecla == KeyEvent.VK_D) && (!izquierda)&& ultimaDireccion!="izquierda") {
            derecha = true;
            arriba = false;
            abajo = false;
        }

        if ((tecla == KeyEvent.VK_W) && (!abajo) && ultimaDireccion!="abajo") {
            arriba = true;
            derecha = false;
            izquierda = false;
        }

        if ((tecla == KeyEvent.VK_S) && (!arriba) && ultimaDireccion!="arriba") {
            abajo = true;
            derecha = false;
            izquierda = false;
        }
    }

	public boolean update() {		
		boolean bukatuDa = false;
		actualizarUltimaDireccion();
		bukatuDa=updateSerpiente();
		return bukatuDa;
	}
	
	private void actualizarUltimaDireccion(){
		if(izquierda){
			ultimaDireccion = "izquierda";
		}else if (derecha){
			ultimaDireccion = "derecha";
		}else if(arriba){
			ultimaDireccion = "arriba";
		}else if (abajo){
			ultimaDireccion = "abajo";
		}
	}


	public Tablero getTablero(){
		return elTablero;
	}
}
