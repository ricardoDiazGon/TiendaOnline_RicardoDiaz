/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.LineaPedido;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo
 */
public class LineasPedidosDAO implements ILineasPedidosDAO{

    @Override
    public int addLineasPedidos(LineaPedido lineaPedido) {        
        int errorSQL = 0;
        String sql = null;

        sql = "INSERT INTO LineasPedidos VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setInt(1, lineaPedido.getIdPedido());
            preparada.setInt(2, lineaPedido.getNumeroLinea());
            preparada.setInt(3, lineaPedido.getIdProducto());
            preparada.setInt(4, lineaPedido.getCantidad());
            preparada.setDouble(5, lineaPedido.getPrecioUnitario());
            preparada.close();
        } catch (SQLException ex) {
            Logger.getLogger(LineasPedidosDAO.class.getName()).log(Level.SEVERE, null, ex);
            errorSQL = ex.getErrorCode();
        }

        this.closeConnection();
        System.out.println("Error sql " + errorSQL);
        return errorSQL;
    }

    @Override
    public ArrayList<LineaPedido> getLineasPedidos(String clausulaWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updLineasPedidos(LineaPedido lineaPedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delLineasPedidos(String clausulaWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
    
}
