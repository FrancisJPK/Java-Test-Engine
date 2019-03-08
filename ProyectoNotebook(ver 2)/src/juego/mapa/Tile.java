/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.mapa;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 *
 * @author theylive
 */
public class Tile extends Rectangle{
    
    protected Color color;
    protected String tipo;
    
    public Tile(Color color, String tipo, int posx, int posy, int ancho, int alto){//constructor tile irregular colorido
        this.color = color;
        this.tipo = tipo;
        setBounds(posx, posy, ancho, alto);
    }
    
    public Tile(String tipo, int posx, int posy){//constructor tile irregular textura o invisible
        this.tipo = tipo;
        //setear la hitbox (con setBounds) en el constructor de la clase hija de esta
    }
    
    public Tile(Color color, String tipo, int posx, int posy, int lado){//constructor tile cuadrado colorido
        this.color = color;
        this.tipo = tipo;
        setBounds(posx, posy, lado, lado);
    }
    
    public void renderizar(Graphics2D g,int offsetX,int offsetY){
        g.setColor(color);

        g.fillRect(x-offsetX, y-offsetY, width, height);
        //g.drawRect(x-offsetX, y-offsetY, width, height);
        
        
    }
    
    public boolean colision(Rectangle hitbox){
        
        if(hitbox.intersects(this)){
            return true;
        }else{
           return false;
        }
        
    }
    
    public boolean solido(){
        if(tipo == "solido"){
            return true;
        }
        return false;
    }
    
    public void cambiarColor(Color color){
    	this.color = color;
    }
    
    public Color color(){
    	return color;
    }
    
}
