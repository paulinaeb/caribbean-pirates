 
package agent;

import server.Cofre;
import java.util.*;
import java.rmi.*;
import java.rmi.registry.*;
import server.ServerInterface;
 
public class Agent implements AgentInterface{
    // atributos de conexiones
    public int port = 2000;
    public String maquinaActual;
    public String maquinaDestino;
    public String lugarDestino;
    public int idLugarOrigen;
    public int index;
    public Vector por_visitar;
    // atributos del agente/barco  
    public String nombre; 
    public int faccion;
    public int tripulacionInicial;
    public int tripulacionActual;
    public int municionesInicial;
    public int municionesActual;
    public int racionesInicial;
    public int racionesActual;
    public String base;
    public int idBase;
    public String sitioPartida;
    public int idSitioPartida;
    public Cofre cofre;
    public boolean init;
    public boolean ganador; 

    // constructor
    public Agent(String nombre, Vector por_visitar ,int port) {
        index = 0;
        init = false;
        lugarDestino = null;
        this.nombre = nombre;
        this.port = port;
        this.por_visitar = por_visitar;
    }
    
    //metodo de navegar para el agente
    @Override
    public void navigate() {
        String next;
        index++; 
        if(index == 4)
            index = 0; 
        if (index < por_visitar.size()) {//si hay servidores por visitar
            try { 
                next = (String) por_visitar.elementAt(index);  
                Registry registro = LocateRegistry.getRegistry("localhost", port);
                ServerInterface server = (ServerInterface) registro.lookup(next);
                System.out.println(nombre + " navegando hacia " + next); 
                server.recibe(this); 
            } catch (RemoteException |  NotBoundException e) {
                System.out.println("Error: " + e);
            }
        }
        else 
            System.out.println("Navegando hacia punto de partida");
    } 
}
