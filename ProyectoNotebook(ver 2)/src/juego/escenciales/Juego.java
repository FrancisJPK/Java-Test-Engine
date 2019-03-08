package juego.escenciales;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import javax.swing.JPanel;

import juego.escenciales.menus.FondoColorido;
import juego.estado.AdminEstados;

public class Juego extends JFrame implements Runnable{//implementa runnable para manejar el thread
    //y extiende JFrame porque actua como la ventana
    public static int ANCHO = 1200,ALTO = 900;
    //public static int ANCHO = Toolkit.getDefaultToolkit().getScreenSize().width, ALTO = Toolkit.getDefaultToolkit().getScreenSize().height;//static porque no van a cambiar
    private Canvas lienzo;//superficie o lienzo de dibujo
    private boolean corriendo;
    private Thread hilo;//este hilo lo vamos a usar para definir cuando inicia el metodo run()
    /*hacer este tema del hilo se podria omitir, pero despues de investigar la
    implementacion de Runnable vi que la mayoria lo enseña a usar con un Thread...
     */
    private Administrador admin;//este es el Handler de los objetos de juego
    private Teclado teclado;//el teclado maneja el estado de las teclas
    private Mouse mouse;//el mouse maneja el estado de los botones del mouse
    private AdminEstados adminEstados;//administrador de estados de juego(jugando,menu,comandos,etc)
    

    public Juego(String titulo) { 
        corriendo = true;//corriendo el juego
        
        setUndecorated(true);//quitamos los bordes del juego
        
        JPanel panel = (JPanel) this.getContentPane();//obtenemos el panel de contenido del frame
        
        panel.setLayout(null);//le quitamos cualquier layout que pueda tener
        ANCHO = panel.getGraphicsConfiguration().getDevice().getDisplayMode().getWidth();
        ALTO = panel.getGraphicsConfiguration().getDevice().getDisplayMode().getHeight();
        panel.setPreferredSize(new Dimension(ANCHO, ALTO));//y le damos tamaño
        

        lienzo = new Canvas();//iniciamos el lienzo
        lienzo.setBounds(0, 0, ANCHO, ALTO);//le damos tamaño
        lienzo.requestFocus();//y se lleva la atencion

        panel.add(lienzo);//y lo añadimos al contenido del frame

        //setResizable(false);//la ventana no puede modificarse
        setDefaultCloseOperation(EXIT_ON_CLOSE);//cuando apretamos la X salimos(no viene default)
        pack();//con esto el tamaño e la ventana se acomoda a sus componentes
        setVisible(true);
        setTitle("Juego");//titulo
        setLocationRelativeTo(null);
        

        teclado = new Teclado();//inicializamos el teclado
        addKeyListener(teclado);//esta clase es un detector de teclado y la clase
        //teclado se encargara de administrar el estado de las teclas
        mouse = new Mouse();//inicializamos el mouse
        
        lienzo.addMouseListener(mouse);//al mouse listener lo pnemos en el lienzo
        //ya que lo que el jugador clckara sera la imagen del juego siempre!
        
        admin = new Administrador(teclado);//inicializamos el handler de objetos
        //pasandole el teclado de parametro pa que los objetos que lo requieran
        //puedan consultarlo
        adminEstados = new AdminEstados(this,admin,teclado,mouse);
        
        this.setFocusable(true);//estas 3 lineas son para que el frame siempre reciba eventos
        //sino , al tocar juego el frame ya no "escucha" ni al mouse ni al teclado
        panel.setFocusable(false);
        lienzo.setFocusable(false);
        empezar();

    }

    private void empezar() {
        corriendo = true;//esto va antes del hilo ya que sino el bucle podria
        //leer esta variable como false si no llegase a cambiarse ante de run()

        hilo = new Thread(this,"hilo loop");//inicializamos el thread pasandole esta clase 
        //como parametro, para que ejecute el metodo run() de esta clase
        hilo.start();
    }

    private void Parar() {
        corriendo = false;

        try {
            hilo.join();//no se usa el stop porque frena de golpe, join espera a
            //que termine el proceso
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    private String contadorFPS_S = "";//string que guarda el valor de fps en texto
    @Override
    public void run() {//game loop
        float nano_por_segundo = 1000000000;
        int APS_objetivo = 61;
        float TiempoObjetivoEntreFrame = nano_por_segundo / APS_objetivo;
        //calculo de tiempo que tiene que haber entre frame en NanoSegundos
        float inicioCrono, finalCrono, duracionDelFrame, reiniciar = 0;//variables para el gameloop y 
        int contadorFPS = 0;//para el contador de fps's

        while (corriendo) {
            inicioCrono = System.nanoTime();//iniciamos cronometro del frame
            actualizar();//actualizamos el juego
            renderizar();//y lo renderizamos
            finalCrono = System.nanoTime();//finalizamos el crono
            duracionDelFrame = finalCrono - inicioCrono;//calculamos duracion del frame
            while (duracionDelFrame < TiempoObjetivoEntreFrame) {//si el frame tomo menos tiempo que el objetivo(lo cual es bueno)
                long dormirMS = (long) ((TiempoObjetivoEntreFrame - duracionDelFrame) / 1000000);//la diferencia entre el objetivo y el real
                //sera el tiempo que dormiremos(la dividimos por 1M para pasarlo a MS)
                try {
                    hilo.sleep(dormirMS);//dormimos el tiempo sobrante, dando tiempo a otros threads de java a ejecutarse
                } catch (InterruptedException ex) {
                    System.out.println("Error en sleep BUCLE: ");
                    ex.printStackTrace();
                }
                duracionDelFrame = System.nanoTime() - inicioCrono;//agregamos a
                //el tiempo dormido a la duracion del frame, y si sigue siendo
                //menor a TiempoObjetivoEntreFrame dormimos lo que falte!
            }
            reiniciar += System.nanoTime() - inicioCrono;//la variable reiniciar nos ayuda a saber si paso un segundo
            if (reiniciar >= nano_por_segundo) {//si ya paso un segundo
                contadorFPS_S = "" + contadorFPS;//cambiamos el texto indicador de fps's
                contadorFPS = 0;//contador lo cambiamos a 0 para que enpieze a contar otra vez
                reiniciar = 0;//la variable auxiliar la reiniciamos tambien
            }
            contadorFPS++;//cada vez que loopeamos aumentamos el contador que en un segundo sera reiniciado
        }
    }
    
    public void renderizar() {
    	BufferStrategy bs = lienzo.getBufferStrategy();
    	if(bs == null){
    		lienzo.createBufferStrategy(3);
    		System.out.println("estrategia buffer creada");
    		return;
    	}
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();//creamos un objeto
        //graphics el cual nos dara las herramientas para renderizar figuras
        //primitivas, poligonos, o imagenes. Este objeto graphics apuntara
        //al objeto graphics del BufferStrategy, asi este lo maneja
        g.setColor(Color.black);
        g.fillRect(0, 0, ANCHO, ALTO);
        /*limpiamos lo que queda en pantalla(llenandolo con un cuadro negro como corresponde xDD)*/
        //g.clip(new Rectangle(0,0,ANCHO,ALTO));
        renderizar(g);//llamamos a otro metodo donde renderizaremos lo que queremos
        g.dispose();//libera los recursos
        bs.show();//mostramos el lo que dibujamos con g2d en el buffer
    }

    public void renderizar(Graphics2D g) {
        //admin.renderizarObjetos(g);
        adminEstados.renderizar(g);
        g.setFont(new Font(Font.SERIF, Font.ITALIC, 16));
        g.drawString(contadorFPS_S, 10, 30);//contador de fps en pantalla;
    }

    public void actualizar() {//metodo para actualizar objetos de juego
        //admin.actualizarObjetos();//a travez del handler
        adminEstados.actualizar();
    }
    
    public static long mapear(long x, long in_min, long in_max, long out_min, long out_max)
    {
        return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
    }

    public static void main(String[] args) {
        Juego juego = new Juego("J U E G O");
    }

}
