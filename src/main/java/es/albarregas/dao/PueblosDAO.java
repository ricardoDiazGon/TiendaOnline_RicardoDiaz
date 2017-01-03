/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Pueblo;
import es.albarregas.daofactory.DAOFactory;
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
            DAOFactory df = DAOFactory.getDAOFactory(1);
            IProvinciasDAO iprd = df.getProvinciasDAO();

            sentencia = ConnectionFactory.getConnection().createStatement();
            resultado = sentencia.executeQuery(sql);

            listaPueblos = new ArrayList();
            Pueblo pueblo = null;
            while (resultado.next()) {
                pueblo = new Pueblo();
                pueblo.setIdPueblo(resultado.getInt("IdPueblo"));
                pueblo.setCodigoPostal(resultado.getString("CodigoPostal"));
                pueblo.setNombre(resultado.getString("Nombre"));
                pueblo.setProvincia(iprd.getProvincias("").get(0));
                listaPueblos.add(pueblo);
            }

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
