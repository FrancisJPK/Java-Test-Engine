
package juego.escenciales;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author theylive
 */
public class Camara{
    
    private int posX,posY,tamX,tamY;//tenemos posicion en el espacio y tamaño
    //(el tamaño no es realmente nesesario, es para temas de centrado)
    
    private String ID;
    
    public int offsetX,offsetY;//muy importante, esto es lo que posibilita correr lo que yo veo
    //respecto a mi posicion, posibilitando el movimiento del mapa
    
    private Spawner spawner;//nesesitamos el spawner del juego para obtener datos del jugador
    
    private Administrador admin;
    
    public Camara( String ID, Spawner spawner,Administrador admin) {
        this.spawner = spawner;
        this.posX = (int)spawner.jugador.posX;
        this.posY = (int)spawner.jugador.posY;
        offsetX = posX;
        offsetY = posY;
        tamX = Juego.ANCHO;
        tamY = Juego.ALTO;
        
        posX = (int)spawner.jugador.posX-tamX/2;
        posY = (int)spawner.jugador.posY-tamY/2;
        
        this.admin = admin;
    }
    
    public void actualizar(){
    	//auxiliares por comodidad
        int posXjug = (int)spawner.jugador.posX;
        int posYjug = (int)spawner.jugador.posY;
        int tamXjug = (int)spawner.jugador.ancho;
        int tamYjug = (int)spawner.jugador.alto;
        int velXjug = (int)spawner.jugador.velX;
        int velYjug = (int)spawner.jugador.velY;

        //centrado de camara
        posX = (int)posXjug+tamXjug/2-tamX/2;
        posY = (int)posYjug+tamYjug/2-tamY/2;

        //actualizacion del offset
        if(!admin.teclaPresionada(KeyEvent.VK_C)){// la tecla c inmoviliza la camara
        offsetX = posX;
        offsetY = posY;
        }
    }
    
}
