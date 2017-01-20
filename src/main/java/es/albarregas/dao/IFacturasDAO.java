/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Factura;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public interface IFacturasDAO {

    public int addFacturas(Factura factura);

    public ArrayList<Factura> getFacturas(String clausulaWhere);

    public int updFacturas(Factura factura);

    public int delFacturas(String clausulaWhere);

    public void closeConnection();
}
