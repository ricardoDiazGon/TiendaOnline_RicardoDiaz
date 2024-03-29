/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Imagen;
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
public class ImagenesDAO implements IIMagenesDAO{

    @Override
    public int addImagenes(Imagen imagen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Imagen> getImagenes(String clausulaWhere) {
        String sql = "SELECT * FROM Imagenes " + clausulaWhere;
        Statement sentencia = null;
        ResultSet resultado = null;
        ArrayList<Imagen> listaImagenes = null;
        try {

            sentencia = ConnectionFactory.getConnection().createStatement();
            resultado = sentencia.executeQuery(sql);

            listaImagenes = new ArrayList();
            Imagen imagen = null;
            while (resultado.next()) {
                imagen = new Imagen();
                imagen.setIdImagen(resultado.getInt("IdImagen"));
                imagen.setIdProducto(resultado.getInt("IdProducto"));
                imagen.setImagen(resultado.getString("Imagen"));
                listaImagenes.add(imagen);
            }
            sentencia.close();
            resultado.close();
        } catch (SQLException ex) {
            Logger.getLogger(ImagenesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConnection();
        return listaImagenes;
    }

    @Override
    public int updImagenes(Imagen imagen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delImagenes(String clausulaWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
    
}
