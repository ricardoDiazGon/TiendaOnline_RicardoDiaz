/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Producto;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public interface IProductosDAO {
    
    public int addProductos(Producto producto);

    public ArrayList<Producto> getProductos(String clausulaWhere);

    public int updProductos(Producto producto);

    public int delProductos(String clausulaWhere);

    public void closeConnection();    
}
