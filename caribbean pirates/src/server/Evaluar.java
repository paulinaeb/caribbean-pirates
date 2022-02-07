package server;

public class Evaluar{
    public String orden = null;
    public Barco barco = null;
    public Sitio sitio = null;
    public String destino = null;
//constructors
    public Evaluar(String orden) {
        this.orden = orden;
    }

    public Evaluar(String orden, Barco barco) {
        this.orden = orden;
        this.barco= barco;
    }

    public Evaluar(String orden, Sitio sitio) {
        this.orden = orden;
        this.sitio = sitio;
    }

    public Evaluar(String orden, String destino) {
        this.orden = orden;
        this.destino = destino;
    }
    
//getters and setters
    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    public Barco getBarco() {
        return barco;
    }

    public void setBarco(Barco barco) {
        this.barco = barco;
    }

    public Sitio getSitio() {
        return sitio;
    }

    public void setSitio(Sitio sitio) {
        this.sitio = sitio;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
