package juego.escenciales;

import java.awt.Color;
import juego.entidades.Jugador;
import juego.entidades.dummies.DummyChiquito;

public class Spawner {

    private Administrador admin;
    public Jugador jugador;

    public Spawner(Administrador admin) {
        this.admin = admin;
        jugador = new Jugador(0, 0, "Jugador", admin);
        agregarObjeto(jugador);
        
        agregarObjeto(new DummyChiquito((int)jugador.posX, (int)jugador.posY, Color.blue, this, admin));
        
        
        
    }

    private void agregarObjeto(Objeto objeto) {
        admin.agregarObjeto(objeto);
    }

    private void eliminarObjeto(String id) {
        admin.eliminarObjeto(id);
    }

}
