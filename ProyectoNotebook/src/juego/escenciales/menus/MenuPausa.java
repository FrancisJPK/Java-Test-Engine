package juego.escenciales.menus;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import juego.escenciales.Juego;
import juego.escenciales.Mouse;
import juego.escenciales.Teclado;
import juego.estado.AdminEstados.ESTADO;

public class MenuPausa extends Menu{
	
	private int alpha = 0;
	private Rectangle botonMenu;
	private Rectangle botonReanudar;
	
	private boolean volver,salir;

	public MenuPausa(Teclado teclado, Mouse mouse) {
		super(teclado, mouse);
		
		botonReanudar = new Rectangle(20, Juego.ALTO-200, 150, 60);
		botonMenu = new Rectangle(20, Juego.ALTO-100, 150, 60);
		
	}

	@Override
	public void renderizar(Graphics2D g) {
		g.setColor(new Color(1,1,1,alpha));
		g.fillRect(0, 0, Juego.ANCHO, Juego.ALTO);
		g.setColor(Color.white);
		g.drawString("P A U S A", 20, 40);
		g.setColor(new Color(100,100,20));
		g.fill(botonReanudar);
		g.fill(botonMenu);
		g.setColor(Color.white);
		g.drawString("R E A N U D A R", botonReanudar.x+5, botonReanudar.y+25);
		g.drawString("S A L I R", botonMenu.x+5, botonMenu.y+25);
	}

	@Override
	public void actualizar() {
		if(alpha < 150){
			alpha++;
		}
		if(teclado.teclaPresionada(KeyEvent.VK_ESCAPE)){
			alpha = 0;
		}
		if(mouse.estaPresionado(MouseEvent.BUTTON1)){
			if(botonReanudar.contains(mouse.posicion())){
				volver = true;
			}
			if(botonMenu.contains(mouse.posicion())){
				salir = true;
			}
		}else{
			volver = false;
			salir = false;
		}
	}
	
	public boolean reanudar(){
		if(volver){
			return true;
		}else{
			return false;
		}
	}
	
	public boolean salir(){
		if(salir){
			return true;
		}else{
			return false;
		}
	}
}
