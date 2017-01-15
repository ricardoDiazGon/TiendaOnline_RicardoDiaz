/*
    Bean de imágenes
 */
package es.albarregas.beans;

import java.io.Serializable;


public class Imagen implements Serializable {
    
    private int IdImagen;
    private int IdProducto;
    private String Imagen;

    public int getIdImagen() {
        return IdImagen;
    }

    public void setIdImagen(int IdImagen) {
        this.IdImagen = IdImagen;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }
    
    
}
