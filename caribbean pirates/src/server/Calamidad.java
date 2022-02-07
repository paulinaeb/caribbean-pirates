package server;

public class Calamidad {
    public String nombre;
    public int bajasTripulacion;
    public int bajasMunicion;
    public int bajasRacion;
    public float prob;
    
    public Calamidad(String nombre, int bajasTripulacion, int bajasMunicion, int bajasRacion, float prob) {
        this.nombre = nombre;
        this.bajasTripulacion = bajasTripulacion;
        this.bajasRacion = bajasRacion;
        this.bajasMunicion = bajasMunicion;
        this.prob = prob;
    }
    
    // getters and setters
    public String getNombre() {
        return nombre;
    }

    public int getBajasTripulacion() {
        return bajasTripulacion;
    }

    public int getBajasMunicion() {
        return bajasMunicion;
    }

    public int getBajasRacion() {
        return bajasRacion;
    }

    public float getProb() {
        return prob;
    } 
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setBajasTripulacion(int bajasTripulacion) {
        this.bajasTripulacion = bajasTripulacion;
    }

    public void setBajasMunicion(int bajasMunicion) {
        this.bajasMunicion = bajasMunicion;
    }

    public void setBajasRacion(int bajasRacion) {
        this.bajasRacion = bajasRacion;
    }

    public void setProb(float prob) {
        this.prob = prob;
    } 
}
