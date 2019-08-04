//no me da ganas de documentar esta clase, es lo mismo que el jugador
//pero con otro comportamiento, fin


package juego.entidades.dummies;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import juego.escenciales.Administrador;
import juego.escenciales.Objeto;
import juego.escenciales.Spawner;

public class DummyChiquito extends Objeto{

    public int velX = 0, velY = 0;
    private Color color;
    private Spawner spawner;

    public DummyChiquito(int posx, int posy, Color color, Spawner spawner, Administrador admin) {
    	//recibe el administrador en su constructor
        super(posx, posy);
        this.admin = admin;
        super.alto = 40;
        super.ancho = 40;
        super.ID = "dummy";
        super.hitbox = new Rectangle((int) posX, (int) posY, ancho, alto);
        color = Color.red;
        this.spawner = spawner;
    }
    
    public void actualizar() {
        seguir(spawner.jugador);
        if(!colision()){
            if(!colisionFutura(velX,0)){
                posX += velX;
            }
            if(!colisionFutura(0,velY)){
                posY += velY;
            }
            if(!colisionFutura(velX,velY)){
                posX += velX;
                posY += velY;
            }
        }
        hitbox.setLocation((int)posX,(int) posY);
    }

    public void renderizar(Graphics2D g,int offsetX,int offsetY) {
        g.setColor(color);
        g.fillOval((int) posX-offsetX, (int) posY-offsetY, ancho, alto);
        
    }
    
    public boolean colision(){
        
        if(admin.mapaPrueba.colisionConTile(hitbox)){
                return true;
            }
        return false;
    }
    
    public void seguir(Objeto objeto){
        if(objeto.posicionX()+objeto.tamaX()/2 > posX+(ancho/2)){
            velX = 1;
        }else{
            velX = -1;
        }
        if(objeto.posicionY()+tamaY()/2 > posY+(alto/2)){
            velY = 1;
        }else{
            velY = -1;
        }
    }
    
}
