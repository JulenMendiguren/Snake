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

}
