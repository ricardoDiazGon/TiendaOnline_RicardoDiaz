/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Factura;
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
public class FacturasDAO implements IFacturasDAO{

    @Override
    public int addFacturas(Factura factura) {
        int errorSQL = 0;
        String sql = null;

        sql = "INSERT INTO Facturas VALUES(0,?)";
        try {
            PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setInt(1, factura.getIdPedido());

            preparada.close();
        } catch (SQLException ex) {
            Logger.getLogger(FacturasDAO.class.getName()).log(Level.SEVERE, null, ex);
            errorSQL = ex.getErrorCode();
        }

        this.closeConnection();
        System.out.println("Error sql " + errorSQL);
        return errorSQL;
    }

    @Override
    public ArrayList<Factura> getFacturas(String clausulaWhere) {
        String sql = "SELECT * FROM Facturas " + clausulaWhere;
        Statement sentencia;
        ArrayList<Factura> listaFacturas = null;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);

            listaFacturas = new ArrayList();
            Factura factura = null;
            while (resultado.next()) {
                factura = new Factura();
                factura.setNumeroFactura(resultado.getInt("NumeroFactura"));
                factura.setIdPedido(resultado.getInt("IdPedido"));
                listaFacturas.add(factura);
            }

            resultado.close();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(PedidosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConnection();
        return listaFacturas;
    }

    @Override
    public int updFacturas(Factura factura) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delFacturas(String clausulaWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
    
}
