/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.io.Serializable;

/**
 *
 * @author Ricardo
 */
public class Caracteristica implements Serializable {
    
    private int IdCaracteristica;
    private int IdCategoria;
    private String Nombre;
    private String Descripcion; //Es propia de CaractYProds, pero está mejor aquí para facilitar las cosas
    
    public int getIdCaracteristica() {
        return IdCaracteristica;
    }

    public void setIdCaracteristica(int IdCaracteristica) {
        this.IdCaracteristica = IdCaracteristica;
    }

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

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    
    
}
