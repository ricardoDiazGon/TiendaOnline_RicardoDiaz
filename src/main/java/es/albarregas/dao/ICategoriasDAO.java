/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Categoria;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public interface ICategoriasDAO {
    
    public int addCategorias(Categoria categoria);

    public ArrayList<Categoria> getCategorias(String clausulaWhere);

    public int updCategorias(Categoria categoria);

    public int delCategorias(String clausulaWhere);

    public void closeConnection();    
    
}
