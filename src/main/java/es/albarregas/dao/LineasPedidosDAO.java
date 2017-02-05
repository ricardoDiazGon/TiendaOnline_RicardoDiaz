/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.LineaPedido;
import es.albarregas.beans.Producto;
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
public class LineasPedidosDAO implements ILineasPedidosDAO {

    @Override
    public int addLineasPedidos(LineaPedido lineaPedido) {
        int errorSQL = 0;
        String sql = null;

        sql = "INSERT INTO LineasPedidos VALUES(?,?,?,?,?)";
        try {
            PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setInt(1, lineaPedido.getIdPedido());
            preparada.setInt(2, lineaPedido.getNumeroLinea());
            preparada.setInt(3, lineaPedido.getProducto().getIdProducto());
            preparada.setInt(4, lineaPedido.getCantidad());
            preparada.setDouble(5, lineaPedido.getPrecioUnitario());
            preparada.executeUpdate();
            preparada.close();
        } catch (SQLException ex) {
            Logger.getLogger(LineasPedidosDAO.class.getName()).log(Level.SEVERE, null, ex);
            errorSQL = ex.getErrorCode();
        }

        this.closeConnection();
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
                Producto producto = new Producto();
                producto.setIdProducto(resultado.getInt("IdProducto"));
                lineaPedido.setProducto(producto);
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
        int errorSQL = -1; //Ponemos a -1, para saber hemos realizado la consulta o no
        boolean set = false;
        ArrayList<LineaPedido> listaLineasPedidos = this.getLineasPedidos("WHERE IdPedido = " + lineaPedido.getIdPedido()
                + " AND NumeroLinea = " + lineaPedido.getNumeroLinea());
        LineaPedido lineaPedido2 = new LineaPedido();
        for (LineaPedido lineaPedidoAct : listaLineasPedidos) {
            lineaPedido2 = lineaPedidoAct;
        }

        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE LineasPedidos ");
        /* 
            Actualizamos solamente los datos que nos vengan diferentes, para ello comparamos con los datos obtenidos
            de la base de datos. Los que sean iguales se dejan.
         */

        if (lineaPedido.getCantidad() != lineaPedido2.getCantidad()) {
            sql.append("SET Cantidad = '" + lineaPedido.getCantidad() + "'");
            set = true;
        }

        if (lineaPedido.getPrecioUnitario() != lineaPedido2.getPrecioUnitario()) {
            if (set) {
                sql.append(", PrecioUnitario = " + lineaPedido.getPrecioUnitario());
            } else {
                sql.append("SET PrecioUnitario = " + lineaPedido.getPrecioUnitario());
                set = true;
            }
        }

        if (set) {
            sql.append("WHERE IdPedido = " + lineaPedido.getIdPedido()
                    + " AND NumeroLinea = " + lineaPedido.getNumeroLinea());
            Statement sentencia = null;
            try {
                sentencia = ConnectionFactory.getConnection().createStatement();
                sentencia.executeUpdate(sql.toString());
                sentencia.close();
                errorSQL = 0;
            } catch (SQLException ex) {
                Logger.getLogger(LineasPedidosDAO.class.getName()).log(Level.SEVERE, null, ex);
                errorSQL = ex.getErrorCode();
            }
        }
        this.closeConnection();
        return errorSQL;
    }

    @Override
    public int delLineasPedidos(String clausulaWhere) {
        int errorSQL = -1; //Ponemos a -1, para saber hemos realizado la consulta o no

        String sql = "DELETE FROM LineasPedidos " + clausulaWhere;
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
