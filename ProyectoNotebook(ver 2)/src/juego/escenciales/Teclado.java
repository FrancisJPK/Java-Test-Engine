package juego.escenciales;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Teclado extends KeyAdapter {//clase que se encargara de brindar las
    //teclas que esten presionadas a aquel objeto que lo requiera

    private boolean[] estadosDeTecla = new boolean[256];//array auxiliar para 
    //guardar el estado de teclas por su codigo especifico.

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        estadosDeTecla[e.getKeyCode()] = true;//cambiamos el estado de la tecla
        if(teclaPresionada(KeyEvent.VK_ESCAPE) && teclaPresionada(KeyEvent.VK_F1)){
        	System.exit(0);
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        estadosDeTecla[e.getKeyCode()] = false;
    }

    public boolean teclaPresionada(int codigoTecla) {
        return estadosDeTecla[codigoTecla];//retornamos si determinada tecla esta
        //presionada actualmente
    }
    
}
