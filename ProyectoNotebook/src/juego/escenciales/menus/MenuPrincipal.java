
package juego.escenciales.menus;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.event.MouseEvent;
import java.security.AllPermission;

import javax.swing.border.StrokeBorder;

import juego.escenciales.Juego;
import juego.escenciales.Mouse;
import juego.escenciales.Teclado;
import juego.mapa.Mapa;

public class MenuPrincipal extends Menu{
    
    private FondoColorido fondo;
    public String titulo = "J U E G O";
    private Color colorFuente;
    private Color colorOtros;
    private Rectangle botonJugar;
    private Rectangle botonSalir;
    
    //estas dos variables me fueron nesesarias para este menu
    //por temas de que en otros menus que derivan a este, hay
    //botones que se superponen con los de este, y se presionan
    //automaticamente. para evitarlo derivo la tarea de los botones
    //al administrador de estados
    public boolean jugar = false;
    public boolean salir = false;

    public MenuPrincipal(Teclado teclado, Mouse mouse) {
        super(teclado, mouse);
        fondo = new FondoColorido(Juego.ANCHO, Juego.ALTO, 20, "fondo");
        botonJugar = new Rectangle(new Point(100,Juego.ALTO-200), new Dimension(200,90));
        botonSalir = new Rectangle(new Point(100,Juego.ALTO-100), new Dimension(200,90));
    }

    @Override
    public void renderizar(Graphics2D g) {
        fondo.renderizar(g, 0, 0);
        colorFuente = new Color(250,250,200);
        g.setFont(new Font(Font.DIALOG, 1, 45));
        g.setColor(colorOtros);
        
        colorOtros = new Color(100,100,20,170);
        g.fillRoundRect(botonJugar.x,botonJugar.y,botonJugar.width,botonJugar.height,20,20);
        g.fillRoundRect(botonSalir.x,botonSalir.y,botonSalir.width,botonSalir.height,20,20);
        
        g.setColor(colorFuente);
        g.drawString(titulo, Juego.ANCHO/2-65, 200);
        g.drawString("JUGAR", 110, botonJugar.y+50);
        g.drawString("SALIR", 110, botonSalir.y+50);
    }

    @Override
    public void actualizar() {
    	
    	fondo.cambiarColorTiles();
        if(mouse.estaPresionado(MouseEvent.BUTTON1)){
            if(botonJugar.contains(mouse.posicion())){
                jugar = true;
                fondo = new FondoColorido(Juego.ANCHO, Juego.ALTO, 20, "fondo");
            }else{
            	jugar = false;
            }
            if(botonSalir.contains(mouse.posicion())){
                //System.exit(0);
            	salir = true;
            }
        }
    }
    
}
