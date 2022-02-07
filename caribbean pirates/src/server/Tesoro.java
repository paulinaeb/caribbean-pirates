package server;

import java.io.Serializable;
//objeto serializable
public class Tesoro implements Serializable{
    public String nombre;
    public int peso;
    public int valor; 
    
//constructor
    public Tesoro(String nombre, int peso, int valor) {
        this.nombre = nombre;
        this.peso = peso;
        this.valor = valor;// 1=comun, 2=mapa, 3=corazon
    }
    
//getters and setters
    public String getNombre() {
        return nombre;
    }

    public int getPeso() {
        return peso;
    }

    public int getValor() {
        return valor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}


