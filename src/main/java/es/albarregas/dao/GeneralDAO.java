/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.General;
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
public class GeneralDAO implements IGeneralDAO {

    @Override
    public int addGeneral(General general) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public General getGeneral(String clausulaWhere) {
        String sql = "SELECT * FROM General " + clausulaWhere;
        Statement sentencia;
        ArrayList<General> listaGeneral = null;
        General general = null;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);

            listaGeneral = new ArrayList();
            while (resultado.next()) {
                general = new General();
                general.setGastosEnvio(resultado.getDouble("GastosEnvio"));
                general.setIva(resultado.getDouble("Iva"));
                listaGeneral.add(general);
            }

            resultado.close();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(GeneralDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConnection();
        return general;
    }

    @Override
    public int updGeneral(General general) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delGenerals(String clausulaWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}
