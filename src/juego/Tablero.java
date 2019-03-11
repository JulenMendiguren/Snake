package juego;

public class Tablero {
	
private int[][] lasCasillas;
private int width;
private int height;

	public Tablero(int dimensionesX, int dimensionesY){
		width=dimensionesX;
		height=dimensionesY;
		lasCasillas = new int[width][height];
		crearTablero();
	}
	
	private void crearTablero(){
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				lasCasillas[i][j] = 0;
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	public int getCasilla(int x, int y){
		return lasCasillas[x][y];
	}
}
