/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Marca;
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
public class MarcasDAO implements IMarcasDAO{

    @Override
    public int addMarcas(Marca marca) {
        int errorSQL = 0;
        String sql = null;

        sql = "INSERT INTO marcas VALUES(0,?)";
        try {
            PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setString(1, marca.getDenominacion());

            preparada.close();
        } catch (SQLException ex) {
            Logger.getLogger(MarcasDAO.class.getName()).log(Level.SEVERE, null, ex);
            errorSQL = ex.getErrorCode();
        }

        this.closeConnection();
        System.out.println("Error sql " + errorSQL);
        return errorSQL;
    }

    @Override
    public ArrayList<Marca> getMarcas(String clausulaWhere) {
        String sql = "SELECT * FROM Marcas " + clausulaWhere;
        Statement sentencia;
        ArrayList<Marca> listaMarcas = null;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);

            listaMarcas = new ArrayList();
            Marca marca = null;
            while (resultado.next()) {
                marca = new Marca();
                marca.setIdMarca(resultado.getInt("IdMarca"));
                marca.setDenominacion(resultado.getString("Denominacion"));
                listaMarcas.add(marca);
            }

            resultado.close();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(MarcasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConnection();
        return listaMarcas;
    }

    @Override
    public int updMarcas(Marca marca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delMarcas(String clausulaWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
    
}
