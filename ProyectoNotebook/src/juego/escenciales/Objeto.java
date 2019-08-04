package juego.escenciales;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public abstract class Objeto {

    protected float posX, posY;
    protected int ancho, alto;
    protected String ID;
    protected Rectangle hitbox;
    protected Administrador admin;

    public Objeto(int posx, int posy) {
        posX = posx;
        posY = posy;
    }

    public abstract void actualizar();

    public abstract void renderizar(Graphics2D g, int offsetX,int offsetY);

    public float posicionX() {
        return posX;
    }

    public float posicionY() {
        return posY;
    }
    
    public int tamaX(){
        return ancho;
    }
    
    public int tamaY(){
        return alto;
    }

    public boolean colisionFutura(int velX,int velY){
        boolean colision = false;
        
            int posXfutura = (int)posX + velX;
            int posYfutura = (int)posY + velY;
            /*
            if(admin.colisionando(new Rectangle(posXfutura,posYfutura,ancho,alto))){
                colision = true;
            }
            */
            
            if(admin.mapaPrueba.colisionConTile(new Rectangle((int)posXfutura,(int)posYfutura,ancho,alto))){
                colision = true;
            }
            
        
        return colision;
    }
    
    public boolean estaColisionando(Rectangle hitbox) {
        if (this.hitbox.intersects(hitbox)) {
            return true;
        } else {
            return false;
        }
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
