/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.LineaPedido;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public interface ILineasPedidosDAO {

    public int addLineasPedidos(LineaPedido lineaPedido);

    public ArrayList<LineaPedido> getLineasPedidos(String clausulaWhere);

    public int updLineasPedidos(LineaPedido lineaPedido);

    public int delLineasPedidos(String clausulaWhere);

    public void closeConnection();
}
