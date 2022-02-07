 
package agent;

import java.io.*;
 
// Interfaz remota de barco
public interface AgentInterface extends Serializable {
    //metodo de interfaz que permite al agente navegar entre maquinas
    void navigate();
}
