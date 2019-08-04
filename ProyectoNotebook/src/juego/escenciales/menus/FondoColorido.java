
package juego.escenciales.menus;

import java.awt.Color;
import java.util.Random;
import juego.escenciales.Administrador;
import juego.mapa.Mapa;
import juego.mapa.Tile;


public class FondoColorido extends Mapa{
	
	//esta clase no pretende ser un mapa jugable, sino que se utilizo el
	//formato de mapa para hacer un fondo colorido para el menu de inicio

    public FondoColorido(int tamX, int tamY, int tamTiles, String nombre) {
        super(tamX, tamY, tamTiles, nombre);
        cargar();
    }

    @Override
    protected void cargar() {
        for(int x = 0;x < tiles.length;x++){
            for(int y = 0; y < tiles[0].length;y++){
                tiles[x][y] = new Tile(Color.black, "colorido", x*tamTiles, y*tamTiles, tamTiles);
            }
        }
    }
    
    public void cambiarColorTiles(){
    	Random r = new Random();
    	int random;
        for(int x = 0;x < tiles.length;x++){
            for(int y = 0; y < tiles[0].length;y++){
            	random = r.nextInt(180);
            	if(random == 0){
            		tiles[x][y].cambiarColor(new Color(r.nextInt(120),r.nextInt(120),r.nextInt(15)));
            	}
            }
        }
    }
    
}
