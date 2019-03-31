package vistas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import juego.DatosJuego;
import juego.Juego;
import juego.Tablero;

public class JuegoUI extends Canvas implements Runnable, KeyListener {
	private static final long serialVersionUID = 1L;
	private static Thread thread;
	private JFrame frame;
	private static boolean running = false;
	private Color colCabeza;
	private Color colCuerpo;

	public JuegoUI(int skinIndex, boolean[] modificadores) {
		running = true;
		frame = new JFrame();
		Dimension size = new Dimension(DatosJuego.PIXELES_ANCHO, DatosJuego.PIXELES_ALTO);
		setPreferredSize(size);
		setFocusable(true);
		requestFocus();
		setSkin(skinIndex);
		setModificadores(modificadores);
		empezarJuego();
		
//		System.out.println("Constructora JuegoUI");
//		System.out.println("Manzanas Env: " + modificadores[0]);
//		System.out.println("Rápido: " + modificadores[1]);
//		System.out.println("Paredes: " + modificadores[2]);
//		System.out.println("Contrarreloj: " + modificadores[3]);
//		System.out.println("");
	}

	private void setModificadores(boolean[] modificadores) {
		// Manzanas envenenadas
		Juego.getJuego().setModoManzanaEnvenenada(modificadores[0]);
		// Más rápido
		if (modificadores[1]) {
			DatosJuego.setFPS(16);
		} else {
			DatosJuego.setFPS(8);
		}
		// Paredes
		Juego.getJuego().setModoParedes(modificadores[2]);

		// Contrarreloj
		Juego.getJuego().setModoContrarReloj(modificadores[3]);

	}

	private void setSkin(int skinIndex) {
		switch (skinIndex) {
		case 0:
			colCabeza = new Color(146, 247, 0);
			colCuerpo = Color.GREEN;
			break;
		case 1:
			colCabeza = Color.CYAN;
			colCuerpo = Color.BLUE;
			break;
		case 2:
			colCabeza = Color.PINK;
			colCuerpo = Color.MAGENTA;
			break;
		case 3:
			colCabeza = Color.YELLOW;
			colCuerpo = Color.ORANGE;
			break;

		}

	}

	public void empezarJuego() {

		this.frame.setResizable(false);
		this.frame.setTitle("Snake");
		this.frame.add(this);
		this.frame.pack();
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setLocationRelativeTo(null);
		this.frame.setVisible(true);
		this.start();
	}

	@Override
	public void keyPressed(KeyEvent evento) {
		Juego.getJuego().keyPressed(evento);

	}

	@Override
	public void keyReleased(KeyEvent evento) {
		Juego.getJuego().keyPressed(evento);

	}

	@Override
	public void keyTyped(KeyEvent evento) {
		Juego.getJuego().keyPressed(evento);

	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		final double ns = 1000000000.0 / DatosJuego.FPS;
		double delta = 0;
		// Se ejecuta tantas veces por segundo como FPS haya
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				running = !Juego.getJuego().update(delta / DatosJuego.FPS);
				pintarTablero();
				delta--;
			}

		}
		alertaGameOver();
	}

	private void alertaGameOver() {
		Object[] options = { "Menú principal", "Cerrar" };
		int n = JOptionPane.showOptionDialog(frame, "Has perdido.", "Fin de la partida", JOptionPane.DEFAULT_OPTION,
				JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

		if (n == 0) {
			MenuPrincipal.main(null);
			Juego.getJuego().destruirJuego();
		}

		setVisible(false);
		frame.dispose();
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		addKeyListener(this);
	}

	public void pintarTablero() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(DatosJuego.COLOR_FONDO);
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(DatosJuego.COLOR_FONDO);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(0, 0, DatosJuego.PIXELES_ANCHO, DatosJuego.PIXELES_ALTO);

		Tablero tab = Juego.getJuego().getTablero();
		int[][] lasCasillas = tab.getCasillas();
		int lon = DatosJuego.LONGITUD_CASILLA;

		for (int i = 0; i < tab.getWidth(); i++) {
			for (int j = 0; j < tab.getHeight(); j++) {
				if (lasCasillas[i][j] == 1) {
					g.setColor(colCabeza);
					g.fillRect(i * lon, j * lon, lon, lon);
				} else if (lasCasillas[i][j] == 2) {
					g.setColor(colCuerpo);
					g.fillRect(i * lon, j * lon, lon, lon);
				} else if (lasCasillas[i][j] == 3) {
					g.setColor(Color.RED);
					g.fillRect(i * lon, j * lon, lon, lon);
				} else if (lasCasillas[i][j] == 4) {
					g.setColor(new Color(113, 0, 133)); // Morado
					g.fillRect(i * lon, j * lon, lon, lon);
				} else if (lasCasillas[i][j] == 5) {
					g.setColor(Color.BLACK);
					g.fillRect(i * lon, j * lon, lon, lon);
				}
			}
		}
		if (Juego.getJuego().getModoContrarReloj()) {
			double reloj = Juego.getJuego().getReloj();
			g.setFont(new Font("Arial", Font.BOLD, 16));
			
			if(reloj > 5){
				g.setColor(Color.BLACK);
			}else{
				g.setColor(Color.RED);
			}
		
			g.drawString(String.format("%.2f", reloj), DatosJuego.PIXELES_ANCHO / 2 - 20, 20);
		}
		g.dispose();
		bs.show();
	}

}
