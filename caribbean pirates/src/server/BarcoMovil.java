 
package server;

import agent.Agent;
 
public class BarcoMovil implements Runnable{
    Thread hilo;
    Barco barco;
    Agent agente;
    // inicializar barcos
    public BarcoMovil(Agent boat){
        barco = new Barco(boat.nombre, boat.faccion, boat.tripulacionInicial, boat.municionesInicial, boat.racionesInicial, boat.base, boat.idBase);
        barco.municionActual = boat.municionesActual;
        barco.racionActual = boat.racionesActual;
        barco.tripulacionActual = boat.tripulacionActual;
        barco.cofre = boat.cofre;
        barco.sitioDestino = boat.lugarDestino;
        barco.idBase = 0;
        agente = boat;
        hilo = new Thread(this, "Hilo de agente: " + boat.nombre);
        hilo.yield();
        hilo.start();
    }
    
    @Override
    public void run() {
        barco.iniciar(); 
        agente.cofre = barco.cofre;
        agente.racionesActual = barco.racionActual;
        agente.tripulacionActual = barco.tripulacionActual;
        agente.municionesActual = barco.municionActual;
        agente.maquinaDestino = barco.maquinaDestino;
        agente.lugarDestino = barco.sitioDestino;
        agente.idLugarOrigen = barco.idSitioActual;
        agente.ganador = barco.ganador;
        agente.idSitioPartida = barco.idSitioPartida; 
        agente.navigate();
    }
}
