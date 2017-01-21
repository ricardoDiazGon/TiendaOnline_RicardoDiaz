/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Pedido;
import java.sql.Date;
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
public class PedidosDAO implements IPedidosDAO {

    @Override
    public int addPedidos(Pedido pedido) {
        int errorSQL = 0;
        String sql = null;

        sql = "INSERT INTO pedidos VALUES(0,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setDate(1, (Date) pedido.getFecha());
            preparada.setString(2, pedido.getEstado());
            preparada.setInt(3, pedido.getIdCliente());
            preparada.setDouble(4, pedido.getBaseImponible());
            preparada.setDouble(5, pedido.getDescuento());
            preparada.setDouble(6, pedido.getGastosEnvio());
            preparada.setDouble(7, pedido.getIva());
            preparada.setInt(8, pedido.getIdDireccion());
            preparada.setString(errorSQL, sql);
            preparada.close();
        } catch (SQLException ex) {
            Logger.getLogger(PedidosDAO.class.getName()).log(Level.SEVERE, null, ex);
            errorSQL = ex.getErrorCode();
        }

        this.closeConnection();
        System.out.println("Error sql " + errorSQL);
        return errorSQL;
    }

    @Override
    public ArrayList<Pedido> getPedidos(String clausulaWhere) {
        String sql = "SELECT * FROM Pedidos " + clausulaWhere;
        Statement sentencia;
        ArrayList<Pedido> listaPedidos = null;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);

            listaPedidos = new ArrayList();
            Pedido pedido = null;
            while (resultado.next()) {
                pedido = new Pedido();
                pedido.setIdPedido(resultado.getInt("IdPedido"));
                pedido.setFecha(resultado.getDate("Fecha"));
                pedido.setEstado(resultado.getString("Estado"));
                pedido.setIdCliente(resultado.getInt("IdCliente"));
                pedido.setBaseImponible(resultado.getDouble("BaseImponible"));
                pedido.setDescuento(resultado.getDouble("Descuento"));
                pedido.setGastosEnvio(resultado.getDouble("GastosEnvio"));
                pedido.setIva(resultado.getDouble("Iva"));
                pedido.setIdDireccion(resultado.getInt("IdDireccion"));
                listaPedidos.add(pedido);
            }

            resultado.close();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConnection();
        return listaPedidos;
    }

    @Override
    public int updPedidos(Pedido pedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delPedidos(String clausulaWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}
