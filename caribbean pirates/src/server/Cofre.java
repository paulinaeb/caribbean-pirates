package server;

import java.io.Serializable;
import java.util.*;
//objeto serializable
public class Cofre implements Serializable {
    //atributos de cofre
    public int capacidad;
    public ArrayList<Tesoro> tesoros = new ArrayList<>();
    public ArrayList<Mapa> mapas = new ArrayList<>();

    public Cofre(int capacidad) {
        this.capacidad = capacidad; 
    }
//getters and setters
    public int getCapacidad() {
        return capacidad;
    }

    public List<Tesoro> getTesoros() {
        return tesoros;
    }

    public List<Mapa> getMapas() {
        return mapas;
    }
    
    public void setCapacidad(int cap) {
        this.capacidad = cap;
    }
    
    public void setMapa(ArrayList<Mapa> map) {
        this.mapas = map;
    }
    
    public void setTesoros(ArrayList<Tesoro> t) {
        this.tesoros = t;
    }
    //metodos para aumentar listas
    public void addTreasure(Tesoro tesoro){    
        tesoros.add(tesoro);
    }
    
    public void addMap(Mapa mapa){
        mapas.add(mapa);
    }
    
    public Tesoro deleteItemTreasure(int i){
        return(tesoros.remove(i));
    }
    
    public void deleteTreasure(Tesoro tesoro){
        tesoros.remove(tesoro);
    }
    
     // radio de plenitud del barco (peso actual/capacidad)
    public float plenitudBarco(){
        return (float) getValorTotal() / capacidad;
    }   
     
    public void addMap() { //registra mapas en cofre
        tesoros.stream().filter((i) -> (i.valor == 2)).forEachOrdered((i) -> {
            boolean nuevo = true; 
            for (Mapa map : this.getMapas()) {
                if(((Mapa)i).nombre.equals(map.nombre)) 
                    nuevo = false; 
            } 
            if (nuevo==true)
                this.getMapas().add((Mapa)i); 
        });
    }
        
    void transferirMitad(Cofre newcofre){ //transfiere la mitad del tesoro a otro cofre
        int num = this.getTesoros().size()/2;
        int aux = 0; 
        ArrayList<Tesoro> treasureCopy = new ArrayList<>();
        this.getTesoros().forEach((i) -> {
            treasureCopy.add(i);
        }); 
        ArrayList<Mapa> mapsCopy = new ArrayList<>();
        this.getMapas().forEach((i) -> {
            mapsCopy.add(i);
        }); 
        mapsCopy.forEach((i) -> {
            newcofre.addMap(i);
        }); 
        aux = treasureCopy.stream().filter((i) -> (i.valor==3)).map((i) -> {
            //corazon de la princesa
            newcofre.addTreasure(i);
            return i;
        }).map((i) -> {
            this.deleteItemTreasure(this.getTesoros().indexOf(i));
            return i; 
        }).map((_item) -> 1).reduce(aux, Integer::sum);
        // Deja la mitad de los demas tesoros 
        for (Tesoro i : treasureCopy){
            if (i.getValor()==1){
                newcofre.addTreasure(i);
                this.deleteItemTreasure(this.getTesoros().indexOf(i));
                aux++;
                if (aux==num){
                    break;
                }
            }
        } 
    }
          
    void transferAll(Cofre newcofre) { //transferir todo el contenido del cofre 
        ArrayList<Tesoro> treasureCopy = new ArrayList<>(); //treasureCopy 
        this.getTesoros().forEach((i) -> {
            treasureCopy.add(i);
        }); 
        treasureCopy.stream().map((i) -> {
            newcofre.addTreasure(i);
            return i;
        }).forEachOrdered((i) -> {
            this.deleteItemTreasure(this.getTesoros().indexOf(i));
        });  
    }
    
    //obtener valor de los tesoros
    public int getValorTotal() {
        int pesototal = 0; 
        for (Tesoro i : tesoros) {
            pesototal = pesototal + i.getPeso();
        }
        return pesototal;
    }
    
    public void actualizarMapa(Sitio sitio) {
        getMapas().stream().filter((m) -> (m.sitio.equals(sitio.nombre))).forEachOrdered((m) -> {
            m.aprox = sitio.aprox;
        });
    }
}