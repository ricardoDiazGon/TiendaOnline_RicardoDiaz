/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Provincia;
import es.albarregas.beans.Pueblo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PueblosDAO implements IPueblosDAO {

    @Override
    public ArrayList<Pueblo> getPueblos(String clausulaWhere) {
        String sql = "SELECT * FROM Pueblos " + clausulaWhere;
        Statement sentencia = null;
        ResultSet resultado = null;
        ArrayList<Pueblo> listaPueblos = null;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            resultado = sentencia.executeQuery(sql);

            listaPueblos = new ArrayList();
            Pueblo pueblo = null;
            Provincia provincia = null;
            while (resultado.next()) {
                pueblo = new Pueblo();
                pueblo.setIdPueblo(resultado.getInt("IdPueblo"));
                pueblo.setCodigoPostal(resultado.getString("CodigoPostal"));
                pueblo.setNombre(resultado.getString("Nombre"));
                provincia = new Provincia();
                provincia.setIdProvincia(resultado.getInt("IdProvincia"));
                pueblo.setProvincia(provincia);
                listaPueblos.add(pueblo);
            }
            sentencia.close();
            resultado.close();
        } catch (SQLException ex) {
            Logger.getLogger(PueblosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConnection();
        return listaPueblos;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}
