 
package server;

import agent.Agent;
import java.rmi.*;

// interfaz remota para el server
public interface ServerInterface extends Remote {
    
    // metodo que permite recibir un agente/barco
    public void recibe(Agent barco) throws RemoteException;
}
