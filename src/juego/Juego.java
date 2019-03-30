package juego;

import java.awt.event.KeyEvent;
import java.util.concurrent.ThreadLocalRandom;

public class Juego {

	private static Juego elJuego;
	private static Tablero elTablero;
	private boolean izquierda = false;
	private boolean derecha = false;
	private boolean arriba = true;
	private boolean abajo = false;
	private String ultimaDireccion = "arriba";
	private boolean modoManzanaEnvenenada = false;
	private boolean modoContrarReloj = false;
	private double reloj = DatosJuego.TIEMPO_RELOJ;

	public Juego() {
		elTablero = new Tablero(DatosJuego.CASILLAS_ANCHO, DatosJuego.CASILLAS_ALTO);
	}

	public static Juego getJuego() {
		if (elJuego == null) {
			elJuego = new Juego();
		}
		return elJuego;
	}

	private boolean updateSerpiente() {
		boolean resultado = false;
		if (izquierda) {
			resultado = elTablero.moverSerpiente("izquierda");
		} else if (derecha) {
			resultado = elTablero.moverSerpiente("derecha");
		} else if (arriba) {
			resultado = elTablero.moverSerpiente("arriba");
		} else if (abajo) {
			resultado = elTablero.moverSerpiente("abajo");
		}
		return resultado;
	}

	public void keyPressed(KeyEvent e) {

		int tecla = e.getKeyCode();

		if ((tecla == KeyEvent.VK_A) && (!derecha) && ultimaDireccion != "derecha") {
			izquierda = true;
			arriba = false;
			abajo = false;
		}

		if ((tecla == KeyEvent.VK_D) && (!izquierda) && ultimaDireccion != "izquierda") {
			derecha = true;
			arriba = false;
			abajo = false;
		}

		if ((tecla == KeyEvent.VK_W) && (!abajo) && ultimaDireccion != "abajo") {
			arriba = true;
			derecha = false;
			izquierda = false;
		}

		if ((tecla == KeyEvent.VK_S) && (!arriba) && ultimaDireccion != "arriba") {
			abajo = true;
			derecha = false;
			izquierda = false;
		}
	}

	public boolean update(double deltaTime) {
		boolean terminado = false;
		if (modoContrarReloj) {
			reloj -= deltaTime;
			if (reloj < 0) {
				reloj = DatosJuego.TIEMPO_RELOJ;
				if (elTablero.borrarCola()) {
					return true;
				}

			}
		}

		actualizarUltimaDireccion();

		if (modoManzanaEnvenenada)

		{
			int randomNum = ThreadLocalRandom.current().nextInt(0, 80);
			if (randomNum == 7) {
				elTablero.generarManzanaEnvenenada();
			}
		}

		terminado = updateSerpiente();
		return terminado;

	}

	private void actualizarUltimaDireccion() {
		if (izquierda) {
			ultimaDireccion = "izquierda";
		} else if (derecha) {
			ultimaDireccion = "derecha";
		} else if (arriba) {
			ultimaDireccion = "arriba";
		} else if (abajo) {
			ultimaDireccion = "abajo";
		}
	}

	public Tablero getTablero() {
		return elTablero;
	}

	public void destruirJuego() {
		elJuego = null;
	}

	public void setModoManzanaEnvenenada(boolean activado) {
		modoManzanaEnvenenada = activado;
	}

	public void setModoContrarReloj(boolean activado) {
		modoContrarReloj = activado;
	}

	public boolean getModoContrarReloj() {
		return modoContrarReloj;
	}

	public void setModoParedes(boolean activado) {
		if (activado) {
			for (int x = 0; x < elTablero.getWidth(); x++) {
				for (int y = 0; y < elTablero.getHeight(); y++) {

					// Horizontales
					if (x > 4 && x < 10 || x > 20 && x < 26) {
						if (y == 5 || y == 25) {
							elTablero.setCasilla(x, y, 5);
						}
					}

					// Verticales
					if (y > 5 && y < 10 || y > 20 && y < 25) {
						if (x == 5 || x == 25) {
							elTablero.setCasilla(x, y, 5);
						}
					}

				}
			}
		}
	}

	public double getReloj() {
		return reloj;
	}
}
