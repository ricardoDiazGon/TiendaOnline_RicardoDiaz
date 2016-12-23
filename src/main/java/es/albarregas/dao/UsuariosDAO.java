package es.albarregas.dao;

import es.albarregas.beans.Usuario;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            preparada.setString(4, usuario.getTipo());
            preparada.setString(5, usuario.getBloqueado());
            preparada.executeUpdate();

            //Cerramos las conexiones
            preparada.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            errorSQL = ex.getErrorCode();
        }

        this.closeConnection();
        System.out.println("Error sql " + errorSQL);
        return errorSQL;
    }

    @Override
    public ArrayList<Usuario> getUsuarios(String clausulaWhere) {
        
        String sql = "SELECT * FROM Usuarios " + clausulaWhere;
        Statement sentencia;
        ArrayList<Usuario> listaUsuarios = null;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            
            listaUsuarios = new ArrayList();
            Usuario usuario = null;
            while(resultado.next()){
                usuario = new Usuario();
                usuario.setIdUsuario(resultado.getInt("IdUsuario"));
                usuario.setUserName(resultado.getString("UserName"));
                usuario.setClave(resultado.getString("Clave"));
                usuario.setUltimoAcceso(resultado.getTimestamp("UltimoAcceso"));
                usuario.setTipo(resultado.getString("Tipo"));
                usuario.setBloqueado(resultado.getString("Bloqueado"));
                listaUsuarios.add(usuario);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listaUsuarios;
        
    }

    @Override
    public int updUsuarios(Usuario usuario) {
        int errorSQL = 0;

        String sql = "UPDATE Usuarios SET UserName = '" + usuario.getUserName() + "', Clave = '" + usuario.getClave()
                + "', UltimoAcceso = '" + new java.sql.Timestamp(usuario.getUltimoAcceso().getTime()) + "',Tipo = '" + usuario.getTipo() 
                + "', Bloqueado = '" + usuario.getBloqueado() + "' WHERE IdUsuario = '" + usuario.getIdUsuario() +"'";
        Statement sentencia = null;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            sentencia.executeUpdate(sql);
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
            errorSQL = ex.getErrorCode();
        }

        this.closeConnection();
        return errorSQL;
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
