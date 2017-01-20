/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Proveedor;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public interface IProveedoresDAO {

    public int addProveedores(Proveedor proveedor);

    public ArrayList<Proveedor> getProveedores(String clausulaWhere);

    public int updProveedores(Proveedor proveedor);

    public int delProveedores(String clausulaWhere);

    public void closeConnection();

}
