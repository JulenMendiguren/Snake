	package juego;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;




public class JuegoUI extends Canvas implements Runnable, KeyListener {
	private static final long serialVersionUID = 1L;
	private static Thread thread;
	private JFrame frame;
	private static boolean running = false;
	
	public JuegoUI (){
		frame = new JFrame();
		Dimension size = new Dimension(DatosJuego.PIXELES_ANCHO, DatosJuego.PIXELES_ALTO);
		setPreferredSize(size);
		setFocusable(true);
		requestFocus();
	}
	
	//TODO: Cambiar nombre a empezarJuego
	public static void main(String[] args) {
		JuegoUI juego = new JuegoUI();
		juego.frame.setResizable(false);
		//juego.frame.setTitle(DatosJuego.TITULO);
		juego.frame.add(juego);
		juego.frame.pack();
		juego.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		juego.frame.setLocationRelativeTo(null);
		juego.frame.setVisible(true);
		juego.start();
	}
	
	@Override
	public void keyPressed(KeyEvent evento) {
		// TODO Auto-generated method stub
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
		//Se ejecuta 60 veces por segundo
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				running=!Juego.getJuego().update();
				pintar();
				delta--;
			}
			
		}System.out.println("Me he muerto.");
	}
	
	public synchronized void start() {
		running = true;
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
	public void pintar() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(DatosJuego.COLOR_FONDO);
		g.fillRect(0, 0, getWidth(), getHeight());
		Juego.getJuego().pintar(g);
		g.dispose();
		bs.show();
	}

}
