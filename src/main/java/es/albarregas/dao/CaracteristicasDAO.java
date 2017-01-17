/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Caracteristica;
import es.albarregas.beans.Producto;
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
public class CaracteristicasDAO implements ICaracteristicasDAO{

    @Override
    public int addCaracteristicas(Caracteristica caracteristica) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Caracteristica> getCaracteristicas(String clausulaWhere) {
        
        String sql = "SELECT * FROM Caracteristicas C LEFT JOIN caractyprods CP ON  C.IdCaracteristica =  CP.IdCaracteristica "
            + clausulaWhere;
        Statement sentencia = null;
        ResultSet resultado = null;
        ArrayList<Caracteristica> listaCaracteristicas = null;
        try {

            sentencia = ConnectionFactory.getConnection().createStatement();
            resultado = sentencia.executeQuery(sql);

            listaCaracteristicas = new ArrayList();
            Caracteristica caracteristica = null;
            while (resultado.next()) {
                caracteristica = new Caracteristica();
                caracteristica.setIdCaracteristica(resultado.getInt("C.IdCaracteristica"));
                caracteristica.setIdCategoria(resultado.getInt("C.IdCategoria"));
                caracteristica.setNombre(resultado.getString("C.Nombre"));
                caracteristica.setDescripcion(resultado.getString("CP.Descripcion"));
                listaCaracteristicas.add(caracteristica);
            }
            sentencia.close();
            resultado.close();
        } catch (SQLException ex) {
            Logger.getLogger(CaracteristicasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConnection();
        return listaCaracteristicas;
    }

    @Override
    public int updCaracteristicas(Caracteristica caracteristica) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delCaracteristicas(String clausulaWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
    
}
