
package juego.mapa;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import juego.escenciales.Administrador;

/**
 *
 * @author theylive
 */
public abstract class Mapa {
    
    protected Tile[][] tiles;
    protected int tamX,tamY;
    protected int tamTiles;
    protected String nombre;
    protected Administrador admin;
  
    public Mapa(int tamX,int tamY, int tamTiles, String nombre, Administrador admin){
        this.tamX = tamX;
        this.tamY = tamY;
        this.tamTiles = tamTiles;
        this.nombre = nombre;
        this.admin = admin;
        tiles = new Tile[tamX/tamTiles][tamY/tamTiles];
    }
    public Mapa(int tamX,int tamY, String nombre, Administrador admin){
        this.tamX = tamX;
        this.tamY = tamY;
        this.nombre = nombre;
        this.admin = admin;
        tiles = new Tile[tamX][tamY];
    }
    
    public Mapa(int tamX,int tamY, int tamTiles, String nombre){//constructor sobrecargado
        this.tamX = tamX;
        this.tamY = tamY;
        this.tamTiles = tamTiles;
        this.nombre = nombre;
        this.admin = null;
        tiles = new Tile[tamX/tamTiles][tamY/tamTiles];
    }
    
    protected abstract void cargar();
    
    public void renderizar(Graphics2D g,int offsetX,int offsetY){
        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles[0].length; y++){
                Tile temp = tiles[x][y];
                if(temp != null ){
                    temp.renderizar(g,offsetX,offsetY);
                }
            }
        }
    }
    
    public boolean colisionConTile(Rectangle hitbox){
        //(*)cuestiones de optimizacion
        int posXAprox = hitbox.x/tamTiles;//*
        int posYAprox = hitbox.y/tamTiles;//*
        if(!(posXAprox < 0 || posYAprox < 0 || posXAprox > tiles.length || posYAprox > tiles[0].length)){//*
        for(int x = posXAprox; x < tiles.length; x++){//*(posXAprox)
            for(int y = posYAprox; y < tiles[0].length; y++){//*(posYAprox)
                if(tiles[x][y] != null){
                    if(tiles[x][y].intersects(hitbox) && tiles[x][y].solido()){
                        return true;
                    }
                }
            }
        }
        }
        
        return false;
    }
    
    public Tile colisionConTileRetornar(Rectangle hitbox){
        //(*)cuestiones de optimizacion
        int posXAprox = hitbox.x/tamTiles;//*
        int posYAprox = hitbox.y/tamTiles;//*
        if(!(posXAprox < 0 || posYAprox < 0 || posXAprox > tiles.length || posYAprox > tiles[0].length)){//*
        for(int x = posXAprox; x < tiles.length; x++){//*(posXAprox)
            for(int y = posYAprox; y < tiles[0].length; y++){//*(posYAprox)
                if(tiles[x][y] != null){
                    if(tiles[x][y].intersects(hitbox) && tiles[x][y].solido()){
                        return tiles[x][y];
                    }
                }
            }
        }
        }
        
        return null;
    }
    
    //esta funcion es para detectar colision con un mapa de tiles irregulares
    //(que no sean cuadrados, por ejemplo)
    public boolean colisionConTileIrregular(Rectangle hitbox){
    	
    	for (int i = 0; i < tiles.length; i++) {
			for (int j = 0; j < tiles[0].length; j++) {
				if(tiles[i][j].colision(hitbox)){
					return true;
				}
			}
		}
    	
    	return false;
    }
        
}
