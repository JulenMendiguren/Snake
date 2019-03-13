package juego;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.concurrent.ThreadLocalRandom;

public class Tablero {

	private int[][] lasCasillas;
	private int width;
	private int height;
	private static Serpiente laSerpiente;

	public Tablero(int dimensionesX, int dimensionesY) {
		width = dimensionesX;
		height = dimensionesY;
		lasCasillas = new int[width][height];
		crearTablero();
		laSerpiente = new Serpiente();
		asignarSerpiente();
		imprimirTablero();
	}

	private void crearTablero() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				lasCasillas[i][j] = 0;
			}
		}
	}

	public void asignarSerpiente() {
		int[] coords = new int[2];
		coords = laSerpiente.getCoordenadas(0);
		lasCasillas[coords[0]][coords[1]] = 1;
		for (int i = 1; i < laSerpiente.getLongitud(); i++) {
			coords = laSerpiente.getCoordenadas(i);
			lasCasillas[coords[0]][coords[1]] = 2;
		}
	}

	public void generarManzana() {
		int x = ThreadLocalRandom.current().nextInt(0, DatosJuego.CASILLAS_ANCHO);
		int y = ThreadLocalRandom.current().nextInt(0, DatosJuego.CASILLAS_ALTO);

		if (lasCasillas[x][y] == 0) {
			lasCasillas[x][y] = 3;
		} else {
			generarManzana();
		}
	}

	public void pintarTablero(Graphics g) {
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(DatosJuego.COLOR_FONDO);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, DatosJuego.PIXELES_ANCHO, DatosJuego.PIXELES_ALTO);
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				if (lasCasillas[i][j] == 1) {
					g.setColor(Color.BLACK);
					g.fillRect(i * DatosJuego.LONGITUD_CASILLA, j * DatosJuego.LONGITUD_CASILLA,
							DatosJuego.LONGITUD_CASILLA, DatosJuego.LONGITUD_CASILLA);
				} else if (lasCasillas[i][j] == 2) {
					g.setColor(Color.GREEN);
					g.fillRect(i * DatosJuego.LONGITUD_CASILLA, j * DatosJuego.LONGITUD_CASILLA,
							DatosJuego.LONGITUD_CASILLA, DatosJuego.LONGITUD_CASILLA);
				} else if (lasCasillas[i][j] == 3) {
					g.setColor(Color.RED);
					g.fillRect(i * DatosJuego.LONGITUD_CASILLA, j * DatosJuego.LONGITUD_CASILLA,
							DatosJuego.LONGITUD_CASILLA, DatosJuego.LONGITUD_CASILLA);
				}
			}
		}
	}

	public void imprimirTablero() {
		String tableroString = "";
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				tableroString = tableroString + " " + lasCasillas[j][i];
			}
			tableroString = tableroString + "\n";
		}
		System.out.println(tableroString);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getCasilla(int x, int y) {
		return lasCasillas[x][y];
	}

}
