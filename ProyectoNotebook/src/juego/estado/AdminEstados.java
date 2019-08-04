
package juego.estado;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import juego.escenciales.Administrador;
import juego.escenciales.Juego;
import juego.escenciales.Mouse;
import juego.escenciales.Teclado;
import juego.escenciales.menus.Menu;
import juego.escenciales.menus.MenuComandos;
import juego.escenciales.menus.MenuPausa;
import juego.escenciales.menus.MenuPrincipal;

/*esta clase administra el juego segun el estado en el que el mismo
 * se encuentra en determinado momento, se encarga de mandar a renderizar
 * y mandar a actualizar determinadas cosas dependiendo, reitero, del 
 * estado actual del juego.*/
public class AdminEstados {
    
    public enum ESTADO{//usamos un enum para declarar cuales son los
    	//estados posibles del juego
        menuPrincipal,
        jugando,
        pausa,
        comandos;
        
    }
    
    public ESTADO estadoActual;//estado actual del juego
    
    private Juego juego;//aca guardamos el juego que vamos a administrar
    private Administrador admin;//el administrador o manejador del juego cuando
    //se esta jugando
    private Teclado teclado;//un teclado del cual leer teclas presionadas
    //lo nesesitamos por ejemplo para detectar el boton "escape" y cambiar
    //el estado a pausa, entre otros
    private int delay = 30;//delay de control
    private Mouse mouse;//un mouse de donde leer los clicks en los botones
    //de los distintos menus
    
    /*NOTA:
     * es probable que muchos de los atributos como el teclado y el mouse
     * apunten a los que creamos en el juego
     */
    
    //aqui declaramos los menus del juego
    private MenuPrincipal menuPrincipal;
    private MenuPausa menuPausa;
    private MenuComandos menuComandos;
    
    public AdminEstados(Juego juego,Administrador admin, Teclado teclado, Mouse mouse){
    	//de nuevo, muchos de los parametros que pasemos seran los que tenemos
    	//en la clase juego
        this.juego = juego;
        this.admin = admin;
        estadoActual = ESTADO.menuPrincipal;
        this.teclado = teclado;
        this.mouse = mouse;
        
        menuPrincipal = new MenuPrincipal(teclado, mouse);
        menuPausa = new MenuPausa(teclado, mouse);
        menuComandos = new MenuComandos(teclado,mouse);
    }
    
    public void cambiarEstado(ESTADO estado){
    	if(delay < 1){
    		estadoActual = estado;
            delay = 30;
    	}
        /*cada vez que cambiamos el estado de juego, que se puede
         * hacer tambien desde otras clases, damos un delay de treinta
         * para controlar la cantidad de veces por segundo que el 
         * jugador puede tocar o elegir una opcion en un menu
         */
    }
    
    public void actualizar(){
        delay--;
        if(estadoActual == ESTADO.menuPrincipal){
                actualizarMenuPrincipal();
        }else{
            if(estadoActual == ESTADO.jugando){
                    actualizarJugando();
            }else{
                if(estadoActual == ESTADO.pausa){  
                        actualizarPausa();
                        
                }else{
                	if(estadoActual == ESTADO.comandos){
                		 actualizarComandos();
                	} 
                }
            }
        }
        
    }
    
    public void renderizar(Graphics2D g){
    	/*las tareas de renderizar las dividimos en funciones
    	 * ya que sino queda un bodoque muy desprolijo
    	 */
        if(estadoActual == ESTADO.menuPrincipal){
            renderizarMenuPrincipal(g);
        }else{
            if(estadoActual == ESTADO.jugando){
                renderizarJugando(g);
            }else{
                if(estadoActual == ESTADO.pausa){
                    renderizarPausa(g);
                }else{
                    renderizarComandos(g);
                }
            }
        }
    }
    
    private void actualizarMenuPrincipal(){
        menuPrincipal.actualizar();
        if(menuPrincipal.jugar){
            cambiarEstado(ESTADO.jugando);
        }else{
        	if(menuPrincipal.salir){
        		if(delay < 1){
        			System.exit(0);
        		}else{
        			menuPrincipal.salir = false;
        		}
        	}
        }
        /*dentro de menu principal nos encargamos de detectar
         * si el jugador elige la opcion jugar
         * si es asi cambiamos el estado.
         * lo mismo para la opcion salir
         */
    }
    
    private void actualizarJugando(){
        admin.actualizarObjetos();
        //cuando estamos jugando actualizamos el administrador
        if(teclado.teclaPresionada(KeyEvent.VK_ESCAPE) && delay < 1){
            cambiarEstado(ESTADO.pausa);
            //detectamos la pausa si presionan la tecla escape
        }else{
        	if(teclado.teclaPresionada(KeyEvent.VK_F3) && delay < 1){
        		cambiarEstado(ESTADO.comandos);
        		//y el menu de comandos si tocan f3
        	}
        }
    }
    
    private void actualizarPausa(){
        menuPausa.actualizar();
        if(teclado.teclaPresionada(KeyEvent.VK_ESCAPE) && delay < 1){
            cambiarEstado(ESTADO.jugando);
        }
        if(menuPausa.reanudar()){
        	cambiarEstado(ESTADO.jugando);
        }else{
        	if(menuPausa.salir()){
        		cambiarEstado(ESTADO.menuPrincipal);
        	}
        }
    }
    
    private void actualizarComandos(){
        menuComandos.actualizar();
        if(teclado.teclaPresionada(KeyEvent.VK_ESCAPE) && delay < 1){
        	cambiarEstado(ESTADO.jugando);
        }
    }
    //-----------------------------------
    private void renderizarMenuPrincipal(Graphics2D g){
        menuPrincipal.renderizar(g);
    }
    
    private void renderizarJugando(Graphics2D g){
        admin.renderizarObjetos(g);
    }
    
    private void renderizarPausa(Graphics2D g){
    	admin.renderizarObjetos(g);
        menuPausa.renderizar(g);
    }
    
    private void renderizarComandos(Graphics2D g){
    	admin.renderizarObjetos(g);
        menuComandos.renderizar(g);
    }
    
}
