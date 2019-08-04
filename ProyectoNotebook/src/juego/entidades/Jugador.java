package juego.entidades;

import java.awt.Color;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import juego.escenciales.*;

public class Jugador extends Objeto {//el jugador es una subclase de Objeto
    //osea lo que es para mi una ENTIDAD

    public int velX = 0, velY = 0;
    private Color color;

    public Jugador(int posx, int posy, String ID, Administrador admin) {//recibe el
        //administrador en su constructor
        
    	super(posx, posy);//la superclase objeto ya tiene los parametros de posicion
    	
        this.admin = admin;//el objeto jugador nesesita comunicarse con el admin para detectar colisiones
        
        super.alto = 60;//esto es el tamaño del jugador obviamente
        super.ancho = 60;//estos son usados de la clase padre "objeto"
        
        super.ID = "jugador";//id del objeto para identificarlo cuando sea nesesario
        
        super.hitbox = new Rectangle((int) posX, (int) posY, ancho, alto);
        //la hitbox la hacemos en forma de un objeto Rectangle, que nos ofrece
        //herramientas ya hechas para tratar colisiones, etc, pa no reinventar la rueda vite
        
        color = Color.blue;//el color del jugador, no tiene mucha ciencia, lo pasamos como parametro al renderizar
    }
    
    public void actualizar() {//cada tick...
        
        moverNormal();//ver funcion...
        
        if(admin.teclaPresionada(KeyEvent.VK_CONTROL)){//control es el boton pa' correr
            moverRapido();//ver funcion...
        }else{
            if(admin.teclaPresionada(KeyEvent.VK_SHIFT))//shift pa' caminar lento
                moverLento();//ver funcion...
        }
        
        /*mientras estemos ante una colision inminente iremos reduciendo la velocidad
        hasta suprimir la colision inminente
        Esto lo hacemos de esta manera para que el jugador quede al raz de la pared donde
        colisionara, y para evitar errores al intentar moverse una vez al raz de una pared*/
            while(colisionFutura(velX,0)){
                velX/=1.5;
            }
            while(colisionFutura(0,velY)){
                velY/=1.5;
            }
            
            while(colisionFutura(velX,velY)){
                velY/=1.5;
                velX/=1.5;
            }
            
            posX += velX;
            posY += velY;
        
        hitbox.setLocation((int) posX, (int) posY);//reposicionamos la hitbox
        
        System.out.println("velocidad X:"+velX+" - velocidad Y:"+velY);//impresion de la velocidad
    }

    public void renderizar(Graphics2D g,int offsetX,int offsetY) {
        g.setColor(color);//seteamos el color del jugador 
        g.fillOval((int) posX-offsetX, (int) posY-offsetY, ancho, alto);//y renderizamos una figura geometrica
        //cortesia de la clase Graphics2D, el la ver2 del juego habra texturas
    }
    
    public boolean colision(){//pregunta al admin si colisionamos con alguien
        
        if(admin.mapaPrueba.colisionConTile(hitbox)){
                return true;
        }
        return false;
    }
    
    private void moverLento(){//nop hay mucha ciencia
        
        if(admin.teclaPresionada(KeyEvent.VK_W)){//para detectar botones del teclado usamos el admin
            velY = -1;
        }else if(admin.teclaPresionada(KeyEvent.VK_S)){
            velY = 1;
        }else{
            velY = 0;
        }
        if(admin.teclaPresionada(KeyEvent.VK_A)){
            velX = -1;
        }else if(admin.teclaPresionada(KeyEvent.VK_D)){
            velX = 1;
        }else{
            velX = 0;
        }
    }
    
    private void moverNormal(){
        
        if(admin.teclaPresionada(KeyEvent.VK_W)){
            if(velY > -5){
                velY -= 1;
            }
        }else if(admin.teclaPresionada(KeyEvent.VK_S)){
            if(velY < 5){
                velY += 1;
            }
        }else{
            velY /= 2;
        }
        if(admin.teclaPresionada(KeyEvent.VK_A)){
            if(velX > -5){
                velX-= 1;
            }
        }else if(admin.teclaPresionada(KeyEvent.VK_D)){
            if(velX < 5){
                velX+= 1;
            }
        }else{
            velX /= 2;
        }
    }
    
    private void moverRapido(){
        
        if(admin.teclaPresionada(KeyEvent.VK_W)){
            if(velY > -15){
                velY -= 1;
            }
        }else if(admin.teclaPresionada(KeyEvent.VK_S)){
            if(velY < 15){
                velY += 1;
            }
        }else{
            velY /= 2;
        }
        if(admin.teclaPresionada(KeyEvent.VK_A)){
            if(velX > -15){
                velX-= 1;
            }
        }else if(admin.teclaPresionada(KeyEvent.VK_D)){
            if(velX < 15){
                velX+= 1;
            }
        }else{
            velX /= 2;
        }
    }
}
