
package server;

public class Mapa extends Tesoro{
    
    public String machine; 
    public String localizacion;
    public String sitio; 
    public int aprox; // aproximación de localizacion 
    
    // constructor de mapas a cayos
    public Mapa(String machine, String sitio, int aprox) {
        super("Mapa a " + sitio, 5, 2);
        this.machine = machine;
        this.localizacion = sitio;
        this.sitio = sitio;
        this.aprox = aprox;
    }
    
    // constructor de mapas a localizaciones en islas
    public Mapa(String machine, String localizacion, String sitio, int valorPosible) {
        super("Mapa a " + sitio, 5, 2);
        this.machine = machine;
        this.localizacion = localizacion;
        this.sitio = sitio;
        this.aprox = valorPosible;
    }
    
    //getters and setters
    public String getMachine() {
        return machine;
    }

    public String getNombreSitio() {
        return localizacion;
    }

    public int getAprox() {
        return aprox;
    } 
    
    public void setMachine(String machine) {
        this.machine = machine;
    }

    public void setSitio(String sitio) {
        this.localizacion = sitio;
    }

    public void setAprox(int valorPosible) {
        this.aprox = valorPosible;
    }  

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }
}
