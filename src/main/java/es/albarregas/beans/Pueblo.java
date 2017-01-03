
package es.albarregas.beans;

import java.io.Serializable;


public class Pueblo implements Serializable {
 
    private int IdPueblo;
    private Provincia provincia;
    private String CodigoPostal;
    private String nombre;

    public int getIdPueblo() {
        return IdPueblo;
    }

    public void setIdPueblo(int IdPueblo) {
        this.IdPueblo = IdPueblo;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public String getCodigoPostal() {
        return CodigoPostal;
    }

    public void setCodigoPostal(String CodigoPostal) {
        this.CodigoPostal = CodigoPostal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
