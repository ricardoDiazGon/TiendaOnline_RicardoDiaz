package es.albarregas.dao;

import es.albarregas.beans.Usuario;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuariosDAO implements IUsuariosDAO {

    @Override
    public int addUsuarios(Usuario usuario) {
        
        int errorSQL = 0;
        String sql = null;
        
        sql = "INSERT INTO usuarios VALUES(0,?,?,?,?,?)";
        try {
            PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setString(1, usuario.getUserName());
            preparada.setString(2, usuario.getClave());
            preparada.setTimestamp(3, new java.sql.Timestamp(usuario.getUltimoAcceso().getTime()));
            preparada.setString(4, Character.toString(usuario.getTipo()));
            preparada.setString(5, Character.toString(usuario.getBloqueado()));
            preparada.executeUpdate();
            
            //Cerramos las conexiones
            preparada.close();
            this.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            errorSQL = ex.getErrorCode();
        }
     
        System.out.println("Error sql " +errorSQL);
        return errorSQL;
    }

    @Override
    public ArrayList<Usuario> getUsuarios(String clausulaWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int updUsuarios(Usuario usuario) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delUsuarios(String clausulaWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}
