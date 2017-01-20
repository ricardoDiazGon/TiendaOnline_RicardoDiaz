/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Pedido;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public interface IPedidosDAO {

    public int addPedidos(Pedido pedido);

    public ArrayList<Pedido> getPedidos(String clausulaWhere);

    public int updPedidos(Pedido pedido);

    public int delPedidos(String clausulaWhere);

    public void closeConnection();
}
