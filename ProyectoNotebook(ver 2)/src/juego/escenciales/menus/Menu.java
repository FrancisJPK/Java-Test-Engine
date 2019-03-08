/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package juego.escenciales.menus;

import java.awt.Graphics2D;
import juego.escenciales.Mouse;
import juego.escenciales.Teclado;

/**
 *
 * @author theylive
 */
public abstract class Menu {
    
    protected Teclado teclado;
    protected Mouse mouse;
    
    public Menu(Teclado teclado,Mouse mouse){
        this.teclado = teclado;
        this.mouse = mouse;
    }
    
    public abstract void renderizar(Graphics2D g);
    public abstract void actualizar();
}
