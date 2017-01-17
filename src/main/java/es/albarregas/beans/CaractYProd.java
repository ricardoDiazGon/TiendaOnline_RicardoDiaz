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
public class CaractYProd implements Serializable{
    
    private int IdCaractyProds;
    private int IdProducto;
    private int IdCaracteristica;
    private String Descripcion;

    public int getIdCaractyProds() {
        return IdCaractyProds;
    }

    public void setIdCaractyProds(int IdCaractyProds) {
        this.IdCaractyProds = IdCaractyProds;
    }

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public int getIdCaracteristica() {
        return IdCaracteristica;
    }

    public void setIdCaracteristica(int IdCaracteristica) {
        this.IdCaracteristica = IdCaracteristica;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }
    
    
    
}
