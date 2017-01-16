/*
    Bean de Categorias
 */
package es.albarregas.beans;

import java.io.Serializable;

public class Categoria implements Serializable {
    
    private int IdCategoria;
    private String Nombre;

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    

}
