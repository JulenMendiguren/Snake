package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Serpiente {

	private ArrayList<Integer> x = new ArrayList<Integer>();
	private ArrayList<Integer> y = new ArrayList<Integer>();

	public Serpiente() {
		for (int i = 15; i > 9; i--) {
			x.add(i);
			y.add(15);
		}
	}

	public int[] getCoordenadas(int pos) {
		int[] coords = new int[2];
		coords[0] = x.get(pos);
		coords[1] = y.get(pos);
		return coords;
	}

	public int getLongitud() {
		return x.size();
	}

	public int[] borrarCola() {
		int[] cola = new int[2];
		cola[0]=x.remove(x.size()-1);
		cola[1]=y.remove(y.size()-1);
		return cola;	
	}

	public void añadirCabeza(int cabezaX, int cabezaY) {
		x.add(cabezaX);
		y.add(cabezaY);
	}

}
