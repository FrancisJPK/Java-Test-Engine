/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.escenciales;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.function.Supplier;

public class Mouse extends MouseAdapter{
    
    public boolean[] botonesPresionados = new boolean[4];
    int posX=0,posY=0;
    
    @Override
    public void mousePressed(MouseEvent e){//lo mismo que con el teclado
        botonesPresionados[e.getButton()] = true;
        posX=e.getX();
        posY=e.getY();
    }
    @Override
    public void mouseReleased(MouseEvent e){//soltado...
        botonesPresionados[e.getButton()] = false;
    }
    public boolean estaPresionado(int codigoBoton){
        if(botonesPresionados[codigoBoton]){
            return true;
        }
        return false;
    }
    
    public Point posicion(){
        //retorna la ultima posicion donde se clicko
        return new Point(posX,posY);
    }
    
}
