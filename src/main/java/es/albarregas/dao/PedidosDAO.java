/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Cliente;
import es.albarregas.beans.Direccion;
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

    // Estados: - P: Pendiente, X: Remitido, R: Recibido, N: Nuevo
    @Override
    public int addPedidos(Pedido pedido) {
        int errorSQL = 0;
        String sql = null;

        sql = "INSERT INTO pedidos VALUES(0,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setTimestamp(1, new java.sql.Timestamp(pedido.getFecha().getTime()));
            preparada.setString(2, pedido.getEstado());
            preparada.setInt(3, pedido.getCliente().getIdCliente());
            preparada.setDouble(4, pedido.getBaseImponible());
            preparada.setDouble(5, pedido.getDescuento());
            preparada.setDouble(6, pedido.getGastosEnvio());
            preparada.setDouble(7, pedido.getIva());
            preparada.setInt(8, pedido.getDireccion().getIdDireccion());
            preparada.executeUpdate();
            preparada.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
                Cliente cliente = new Cliente();
                cliente.setIdCliente(resultado.getInt("IdCliente"));
                pedido.setCliente(cliente);
                pedido.setBaseImponible(resultado.getDouble("BaseImponible"));
                pedido.setDescuento(resultado.getDouble("Descuento"));
                pedido.setGastosEnvio(resultado.getDouble("GastosEnvio"));
                pedido.setIva(resultado.getDouble("Iva"));
                Direccion direccion = new Direccion();
                direccion.setIdDireccion(resultado.getInt("idDireccion"));
                pedido.setDireccion(direccion);
                listaPedidos.add(pedido);
            }

            resultado.close();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(PedidosDAO.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        this.closeConnection();
        return listaPedidos;
    }

    @Override
    public int updPedidos(Pedido pedido) {
        int errorSQL = -1; //Ponemos a -1, para saber hemos realizado la consulta o no
        boolean set = false;

        ArrayList<Pedido> listaPedidos = this.getPedidos("WHERE IdPedido = '" + pedido.getIdPedido() + "'");
        Pedido pedido2 = listaPedidos.get(0);
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Pedidos ");
        /* 
            Actualizamos solamente los datos que nos vengan diferentes, para ello comparamos con los datos obtenidos
            de la base de datos. Los que sean iguales se dejan.
         */
        if (!pedido.getEstado().equals(pedido2.getEstado())) {
            sql.append("SET Estado = '" + pedido.getEstado() + "'");
            set = true;
        }

        if (pedido.getBaseImponible() != pedido2.getBaseImponible()) {
            if (set) {
                sql.append(", BaseImponible = " + pedido.getBaseImponible());
            } else {
                sql.append("SET BaseImponible = '" + pedido.getBaseImponible() + "'");
                set = true;
            }
        }

        if (pedido.getDescuento() != pedido2.getDescuento()) {
            if (set) {
                sql.append(", Descuento = " + pedido.getDescuento());
            } else {
                sql.append("SET Descuento = " + pedido.getDescuento());
                set = true;
            }
        }

        if (pedido.getGastosEnvio() != pedido2.getGastosEnvio()) {
            if (set) {
                sql.append(", GastosEnvio = " + pedido.getGastosEnvio());
            } else {
                sql.append("SET GastosEnvio = " + pedido.getGastosEnvio());
                set = true;
            }
        }

        if (pedido.getIva() != pedido2.getIva()) {
            if (set) {
                sql.append(", Iva = " + pedido.getIva());
            } else {
                sql.append("SET Iva = " + pedido.getIva());
                set = true;
            }
        }
        
        if (!pedido.getFecha().equals(pedido2.getFecha())) {
            if (set) {
                sql.append(", Fecha = '" + new java.sql.Timestamp(pedido.getFecha().getTime()) + "'");
            } else {
                sql.append("SET Fecha = '" + new java.sql.Timestamp(pedido.getFecha().getTime()) + "'");
                set = true;
            }
        }

        if (pedido.getDireccion().getIdDireccion() != pedido2.getDireccion().getIdDireccion()) {
            if (set) {
                sql.append(", IdDireccion = " + pedido.getDireccion().getIdDireccion());
            } else {
                sql.append("SET IdDireccion = " + pedido.getDireccion().getIdDireccion());
                set = true;
            }
        }        
        
        if (set) {
            sql.append(" WHERE IdPedido = '" + pedido.getIdPedido() + "'");
            Statement sentencia = null;
            try {
                sentencia = ConnectionFactory.getConnection().createStatement();
                sentencia.executeUpdate(sql.toString());
                sentencia.close();
                errorSQL = 0;
            } catch (SQLException ex) {
                Logger.getLogger(PedidosDAO.class.getName()).log(Level.SEVERE, null, ex);
                errorSQL = ex.getErrorCode();
            }
        }
        this.closeConnection();
        return errorSQL;
    }

    @Override
    public int delPedidos(String clausulaWhere) {
        int errorSQL = -1; //Ponemos a -1, para saber hemos realizado la consulta o no

        String sql = "DELETE FROM Pedidos " + clausulaWhere;
        Statement sentencia;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            sentencia.executeUpdate(sql);
            sentencia.close();
            errorSQL = 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            errorSQL = ex.getErrorCode();
        }
        this.closeConnection();
        return errorSQL;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}
