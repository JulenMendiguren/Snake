package juego;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
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


}
