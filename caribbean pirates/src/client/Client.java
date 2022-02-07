 
package client;

import java.util.*;
import java.rmi.*;
import java.rmi.registry.*;

import agent.Agent;
import server.ServerInterface;
 
//Clase cliente: envia los agentes/barcos a los servidores
public class Client { 
    //atributos de conexion
    static int port = 2000;
    static String ip = "localhost"; 
    
    public static void main(String[] args) { 
        try {  
            System.out.println("¿Desea enviar la orden secreta del capitán?\n1.Si\n2.No\n3.Salir");
            Scanner sc = new Scanner(System.in);
            int option = sc.nextInt();  
            if (option==1)
                System.out.println("Iniciando...."); 
            else{
                System.out.println("Ingrese una opción válida");
                System.exit(0);
            }
            Registry registro = LocateRegistry.getRegistry(ip, port);  
            ServerInterface server; 
            server = (ServerInterface) registro.lookup("Pantalla 1"); //localiza servidor #1 
            Vector por_visitar = new Vector();//lista de servidores (itinerario del agente)
            por_visitar.addElement("Pantalla 1");
            por_visitar.addElement("Pantalla 2");
            por_visitar.addElement("Pantalla 3");
            por_visitar.addElement("Pantalla 4");  
            //crear agentes
            Agent pirata = new Agent("pirata", por_visitar ,port);
            pirata.maquinaActual = "Pantalla 1";
            pirata.maquinaDestino = "Pantalla 1";  
            server.recibe(pirata);  
            Agent real1 = new Agent("HSM Interceptor", por_visitar ,port);
            real1.maquinaActual = "Pantalla 1";
            real1.maquinaDestino = "Pantalla 2";
            Agent real2 = new Agent("HSM Invencible", por_visitar, port); 
            real2.maquinaActual = "Pantalla 1";
            real2.maquinaDestino = "Pantalla 2"; 
            server.recibe(real1);
            server.recibe(real2);
            System.out.println("***Agentes móviles (barcos) han comenzado la aventura!***");
        }
        catch (NotBoundException | RemoteException e) {
            System.out.println("Error en cliente: " + e);
        }
    }
}
