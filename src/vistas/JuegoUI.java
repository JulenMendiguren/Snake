package vistas;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
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
		empezarJuego();
	}

	private void setSkin(int skinIndex) {
		switch (skinIndex) {
		case 0:
			colCabeza = Color.BLACK;
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
				running = !Juego.getJuego().update();
				pintarTablero();
				delta--;
			}

		}
		alertaGameOver();
		System.out.println("Me he muerto.");
	}

	private void alertaGameOver() {
		Object[] options = { "Menu principal", "Cerrar" };
		int n = JOptionPane.showOptionDialog(frame, "Te has muerto", "Game Over", JOptionPane.DEFAULT_OPTION,
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

	public synchronized static void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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

		g.setFont(new Font("Arial", Font.BOLD, 20));
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
				}
			}
		}

		g.dispose();
		bs.show();
	}

}
