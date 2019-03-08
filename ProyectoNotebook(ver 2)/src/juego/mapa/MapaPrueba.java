/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mapa;

import java.awt.Color;
import juego.escenciales.Administrador;
import static juego.escenciales.Juego.mapear;

/**
 *
 * @author theylive
 */
public class MapaPrueba extends Mapa{

    public MapaPrueba(int tamX, int tamY, int tamTiles, String nombre, Administrador admin) {
        super(tamX, tamY, tamTiles, nombre, admin);
        cargar();
    }

    @Override
    protected void cargar() {
        int r=50,g=50,b=50;
        for(int x = 0; x < tiles.length; x++){
            //r = (int)mapear(x,0,tiles.length,0,256);
            g = (int)mapear(x,0,tiles.length,0,256);
            b = (int)mapear(x,0,tiles.length,0,256);
            for(int y = 0; y < tiles[0].length; y++){
                if( y % 4 == 0 && x % 4 == 0){
                    //tiles[x][y] = new Tile(new Color(r,g,b), "solido", x*tamTiles, y*tamTiles, tamTiles);
                }
                if(x % 8 == 4 && y % 8 == 4){
                    tiles[x][y-1] = new Tile(new Color(r,g,b), "solido", x*tamTiles, (y-1)*tamTiles, tamTiles);
                    tiles[x-1][y] = new Tile(new Color(r,g,b), "solido", (x-1)*tamTiles, y*tamTiles, tamTiles);
                    tiles[x][y+1] = new Tile(new Color(r,g,b), "solido", x*tamTiles, (y+1)*tamTiles, tamTiles);
                    tiles[x+1][y] = new Tile(new Color(r,g,b), "solido", (x+1)*tamTiles, y*tamTiles, tamTiles);
                }
            }
        }
        tiles[0][0] = new Tile(Color.darkGray, "vacio", 0, 0, tamTiles*(tamX/tamTiles), tamTiles*(tamY/tamTiles));
        
    }
    
    
    
}
