package juego.escenciales;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;
import juego.mapa.Mapa;
import juego.mapa.MapaPrueba;

public class Administrador {

    private LinkedList<Objeto> listaDeObjetos = new LinkedList<Objeto>();//esta es una lista 
    //de tamaño variable que contentra nuestros objetos de juego
    
    private Teclado teclado;//el admin tiene un administrador de teclado integrado
    //el cual los objetos pueden consultar
    
    public Mapa mapaPrueba = new MapaPrueba(20000, 20000, 50, "mapaPrueba", this);//los mapas los tenemos declarados
    //y creados aca.
  
    private Spawner spawner;//tenemos un administrador de spawneo de objetos
    
    public Camara camara;//y una camara para que los objetos puedan consultar el offset
    

    public Administrador(Teclado teclado){//constructor
        this.teclado = teclado;
        spawner = new Spawner(this);//creamos un spawner que tiene el poder
        //de crear instancias de entidades(objetos)
        camara = new Camara("camara", spawner, this);//inicializamos la camara
        
    }

    public void agregarObjeto(Objeto objeto) {//metodo para añadir un objeto a la lista
        listaDeObjetos.add(objeto);
        System.out.println("Añadido objeto: " + objeto.ID);//informamos del objeto a�adido
    }

    public void eliminarObjeto(String ID) {//para eliminar un objeto segun su id
        for (int i = 0; i < listaDeObjetos.size(); i++) {
            if (ID == listaDeObjetos.get(i).ID) {
                System.out.println("REMOVIDO");
                listaDeObjetos.remove(i);
            }
        }
    }

    public void renderizarObjetos(Graphics2D g) {//renderizamos el mapa y los objetos de juego
        
        mapaPrueba.renderizar(g,camara.offsetX,camara.offsetY);
        
        for (int i = 0; i < listaDeObjetos.size(); i++) {
                listaDeObjetos.get(i).renderizar(g,camara.offsetX,camara.offsetY);
        }
    }

    public void actualizarObjetos() {//actualizamos la camara y los objetos de juego, el mapa no es nesesario
        camara.actualizar();
        for (int i = 0; i < listaDeObjetos.size(); i++) {
            listaDeObjetos.get(i).actualizar();
        }
    }

    public boolean teclaPresionada(int codigoTecla) {//devuelve si una tecla(dada por el codigo de tecla) esta presionada
        return teclado.teclaPresionada(codigoTecla);
    }
    
    public void existe(String id){//consulta de la existencia de un objeto segun ID
        for(int i = 0; i < listaDeObjetos.size();i++){
            if(listaDeObjetos.get(i).ID == id){
                System.out.println("YEAH!!");
            }
        }
    }

}
