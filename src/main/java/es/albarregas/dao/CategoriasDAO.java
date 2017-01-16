/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Categoria;
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
public class CategoriasDAO implements ICategoriasDAO{

    @Override
    public int addCategorias(Categoria categoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Categoria> getCategorias(String clausulaWhere) {
        String sql = "SELECT * FROM Categorias " + clausulaWhere;
        Statement sentencia = null;
        ResultSet resultado = null;
        ArrayList<Categoria> listaCategorias = null;
        try {

            sentencia = ConnectionFactory.getConnection().createStatement();
            resultado = sentencia.executeQuery(sql);

            listaCategorias = new ArrayList();
            Categoria categoria = null;
            while (resultado.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(resultado.getInt("IdCategoria"));
                categoria.setNombre(resultado.getString("Nombre"));
                listaCategorias.add(categoria);
            }
            sentencia.close();
            resultado.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoriasDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConnection();
        return listaCategorias;
    }

    @Override
    public int updCategorias(Categoria categoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delCategorias(String clausulaWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
    
}
