/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.LineaPedido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        String sql = "SELECT * FROM LineasPedidos " + clausulaWhere;
        Statement sentencia;
        ArrayList<LineaPedido> listaLineasPedidos = null;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);

            listaLineasPedidos = new ArrayList();
            LineaPedido lineaPedido = null;
            while (resultado.next()) {
                lineaPedido = new LineaPedido();
                lineaPedido.setIdPedido(resultado.getInt("IdPedido"));
                lineaPedido.setNumeroLinea(resultado.getInt("NumeroLinea"));
                lineaPedido.setIdProducto(resultado.getInt("IdProducto"));
                lineaPedido.setCantidad(resultado.getInt("Cantidad"));
                lineaPedido.setPrecioUnitario(resultado.getDouble("PrecioUnitario"));
                listaLineasPedidos.add(lineaPedido);
            }

            resultado.close();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConnection();
        return listaLineasPedidos;
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
