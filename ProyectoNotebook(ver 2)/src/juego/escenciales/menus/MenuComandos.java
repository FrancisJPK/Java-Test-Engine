package juego.escenciales.menus;

import java.awt.Color;
import java.awt.Graphics2D;

import juego.escenciales.Juego;
import juego.escenciales.Mouse;
import juego.escenciales.Teclado;

public class MenuComandos extends Menu{

	public MenuComandos(Teclado teclado, Mouse mouse) {
		super(teclado, mouse);
	}

	@Override
	public void renderizar(Graphics2D g) {
		g.setColor(new Color(100,100,20,127));
		g.fillRect(0,0,Juego.ANCHO,Juego.ALTO);
		g.setColor(Color.white);
		g.drawString("C O M A N D O S", 20, 40);
		
	}

	@Override
	public void actualizar() {
		
		
	}

}
