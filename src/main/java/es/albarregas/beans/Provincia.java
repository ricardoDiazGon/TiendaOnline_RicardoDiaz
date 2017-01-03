package es.albarregas.beans;

import java.io.Serializable;

public class Provincia implements Serializable{
    
    private int IdProvincia;
    private String nombre;

    public int getIdProvincia() {
        return IdProvincia;
    }

    public void setIdProvincia(int IdProvincia) {
        this.IdProvincia = IdProvincia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
