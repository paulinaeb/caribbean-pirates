 
package server; 

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import views.ImgBarco;
import views.Objeto;
import views.Frame;
import agent.Agent;

// Clase que contiene el servidor RMI, graficos y logica. se comunica con el agente 
public class ServerUtils extends UnicastRemoteObject implements ServerInterface {
    public String id;  
    public static Frame ventana;
    public static Objeto mar = new Objeto(null, "mar",850,550,0,0); 
    public static ArrayList<Sitio> sitios;
    public static ArrayList<Barco> barcos;
    public static ArrayList<ImgBarco> imgbarcos;
    public static ArrayList<Objeto> imgcayos, imgislas;
    public static int tiempos[][] = new int[10][];
    public static boolean fin = false;
    
    public ServerUtils(String nombre) throws RemoteException{
        super();
        tiempos[0] = new int[]{ 0, 3, 5, 7, 8,10,12,15,17,18}; // Isla Nueva Esperanza
        tiempos[1] = new int[]{ 3, 0, 2, 4, 5, 7, 9,12,15,15}; // Cayo del buen viento
        tiempos[2] = new int[]{ 5, 2, 0, 2, 3, 5, 7,10,12,13}; // Cayo de sotavento
        tiempos[3] = new int[]{ 7, 4, 2, 0, 1, 3, 5, 8,10,11}; // Isla la Holandesa
        tiempos[4] = new int[]{ 8, 5, 3, 1, 0, 2, 4, 7, 9,10}; // Isla el Naufrago
        tiempos[5] = new int[]{10, 7, 5, 3, 2, 0, 2, 5, 7, 8}; // Isla Tortuga
        tiempos[6] = new int[]{12, 9, 7, 5, 4, 2, 0, 3, 5, 6}; // Isla las Aves
        tiempos[7] = new int[]{15,12,10, 8, 7, 5, 3, 0, 2, 3}; // Cayo de Barlovento
        tiempos[8] = new int[]{17,14,12,10, 9, 7, 5, 2, 0, 1}; // La Gran Isla de la Española
        tiempos[9] = new int[]{18,15,13,11,10, 8, 6, 3, 1, 0}; // Cayo de los Pelicanos
        this.id = nombre; 
        sitios = new ArrayList<>();
        barcos = new ArrayList<>();
        imgbarcos = new ArrayList<>();
        imgcayos = new ArrayList<>();
        imgislas = new ArrayList<>();
    }
    // getters and setters
    public ArrayList<Sitio> getUbicaciones() {
        return sitios;
    }

    public ArrayList<Barco> getBarcos() {
        return barcos;
    } 
    
    public void setUbicaciones(ArrayList<Sitio> ubicaciones) {
        this.sitios = ubicaciones;
    }

    public void setBarcos(ArrayList<Barco> barcos) {
        this.barcos = barcos;
    }
    
    // metodo que recibe los agentes
    @Override
    public void recibe(Agent barco) throws RemoteException { 
        if(barco.ganador && !fin) {
            fin = true;
            System.out.println(barco.nombre + " ha ganado la misión!");
            barco.navigate();
        } 
        if (!fin) { 
            if (barco.maquinaDestino.equals(id)) { 
                if (!barco.init) {
                    Barco aux = barcos.get(0);
                    barco.nombre = aux.name;
                    barco.tripulacionInicial = aux.tripulacionInicial;
                    barco.racionesInicial = aux.racionInicial;
                    barco.municionesInicial = aux.municionInicial;
                    barco.tripulacionActual = aux.tripulacionInicial;
                    barco.racionesActual = aux.racionInicial;
                    barco.municionesActual = aux.municionInicial;
                    barco.faccion = aux.faccion;
                    barco.idBase = aux.idBase;
                    barco.base = aux.base;
                    barco.cofre = aux.cofre;
                    barco.init = true;
                    barcos.remove(0);
                }
                entrarJuego(barco);
            } else 
                barco.navigate();   //continua el itinerario a otra maquina
        }
    }
    //agregar subsitios
    public void setSubsitios() {
        sitios.forEach((site) -> {
            sitios.stream().filter((s) -> (site.parent == -1 && s.parent == site.id)).map((s) -> {
                s.x = site.x;
                return s;
            }).map((s) -> {
                s.y = site.y;
                return s;
            }).forEachOrdered((s) -> {
                site.agregarSubsitio(s);
            });
        });
    }
    //iniciar interfaz grafica
    public void iniciarVentana() {
        ventana = new Frame();
        ImgBarco pirata = new ImgBarco();
        ImgBarco real1 = new ImgBarco();
        ImgBarco real2 = new ImgBarco();
        addBarco(pirata); addBarco(real1); addBarco(real2);
        imgbarcos.forEach((barco) -> {
            ventana.add(barco.getLabel());
        });
        imgcayos.forEach((cayo) -> { 
            ventana.add(cayo.getLabel());
        });
        imgislas.forEach((isla) -> { 
            ventana.add(isla.getLabel());
        });
        ventana.add(mar.getLabel());
        ventana.repaint();
    }
    
        public void entrarJuego(Agent barco) {
        System.out.println(barco.nombre + " ha llegado a maquina destino");
        BarcoMovil barcomovil = new BarcoMovil(barco);
    }
    
    public void addSitio(Sitio sitio) {
        this.sitios.add(sitio);
    }
    
    public void addBarco(Barco barco) {
        this.barcos.add(barco);
    }
        
    public void addBarco(ImgBarco barco) {
        this.imgbarcos.add(barco);
    }
    
    public void addCayo(Objeto cayo) {
        this.imgcayos.add(cayo);
    }
    
    public void addIsla(Objeto isla) {
        this.imgislas.add(isla);
    }
}
