package server;

import java.util.*;

public class Sitio {
    public int id;
    public String nombre;
    public String tipo;
    public int time;
    public int parent;
    public Cofre cofre;
    public ArrayList<Sitio> subSitios;
    public ArrayList<Calamidad> calamidades;
    public ArrayList<Barco> barcos = new ArrayList<>();
    public int aprox;
    public int x;
    public int y;
    
    //sitios: islas o cayos
    public Sitio(int id, String nombre, String tipo, int time, int x, int y) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.calamidades = new ArrayList<>();
        if (tipo.equals("isla"))
            this.subSitios = new ArrayList<>();
        this.time = time;
        this.parent = -1;
        this.x = x;
        this.y = y;
    }
    // sitios dentro de islas 
    public Sitio(int id, String nombre, String tipo, int time, int parent) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.calamidades = new ArrayList<>();
        this.subSitios = null;
        this.time = time;
        this.parent = parent;
        this.x = 0;
        this.y = 0;
    }
    
    // getters and setters
    public int getIdSitio() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public int getTime() {
        return time;
    }

    public int getParent() {
        return parent;
    }

    public Cofre getCofre() {
        return cofre;
    }

    public ArrayList<Sitio> getSubSitios() {
        return subSitios;
    }

    public ArrayList<Calamidad> getCalamidades() {
        return calamidades;
    }

    public ArrayList<Barco> getBarcos() {
        return barcos;
    }
 
    public void setIdSitio(int idSitio) {
        this.id = idSitio;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public void setCofre(Cofre cofre) {
        this.cofre = cofre;
    }

    public void setSubSitios(ArrayList<Sitio> subSitios) {
        this.subSitios = subSitios;
    }

    public void setCalamidades(ArrayList<Calamidad> calamidades) {
        this.calamidades = calamidades;
    }

    public void setBarcos(ArrayList<Barco> barcos) {
        this.barcos = barcos;
    }
     
    public void addCalamidad(Calamidad calamidad) {
        calamidades.add(calamidad);
    }
    
    public void removeCalamidad(Calamidad calamidad) {
        calamidades.remove(calamidad);
    }

    public void agregarSubsitio(Sitio sitio) {
        this.subSitios.add(sitio);
    }
    
    public void recibirBarco(Barco barco){
        this.barcos.add(barco);
    }
    
    public void removeBarco(Barco barco) {
        this.barcos.remove(barco);
    }

    public void updateAprox() {
        int aux = 0;
        if (!getCofre().getTesoros().isEmpty())
            aux = getCofre().getTesoros().stream().map((t) -> t.getValor()).reduce(aux, Integer::sum);
        aprox = aux;
    }
}
