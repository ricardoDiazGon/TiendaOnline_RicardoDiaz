package es.albarregas.dao;

import es.albarregas.beans.Cliente;
import es.albarregas.beans.Usuario;
import es.albarregas.daofactory.DAOFactory;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        Statement sentencia = null;
        ResultSet resultado = null;
        ArrayList<Usuario> listaUsuarios = null;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            resultado = sentencia.executeQuery(sql);

            listaUsuarios = new ArrayList();
            Usuario usuario = null;
            while (resultado.next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(resultado.getInt("IdUsuario"));
                usuario.setUserName(resultado.getString("UserName"));
                usuario.setClave(resultado.getString("Clave"));
                usuario.setUltimoAcceso(resultado.getTimestamp("UltimoAcceso"));
                usuario.setTipo(resultado.getString("Tipo"));
                usuario.setBloqueado(resultado.getString("Bloqueado"));
                listaUsuarios.add(usuario);
            }
                sentencia.close();
                resultado.close();
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConnection();
        return listaUsuarios;

    }

    @Override
    public int updUsuarios(Usuario usuario) {
        int errorSQL = -1; //Ponemos a -1, para saber hemos realizado la consulta o no
        boolean set = false;
        ArrayList<Usuario> listaUsuarios = this.getUsuarios("WHERE IdUsuario = '" + usuario.getIdUsuario() + "'");
        Usuario usuario2 = listaUsuarios.get(0);
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Usuarios ");
        /* 
            Actualizamos solamente los datos que nos vengan diferentes, para ello comparamos con los datos obtenidos
            de la base de datos. Los que sean iguales se dejan.
         */
        if (!usuario.getUserName().equals(usuario2.getUserName())) {
            sql.append("SET UserName = '" + usuario.getUserName() + "'");
            set = true;
        }

        if (!usuario.getClave().equals(usuario2.getClave())) {
            if (set) {
                sql.append(", Clave = '" + usuario.getClave() + "'");
            } else {
                sql.append("SET Clave = '" + usuario.getClave() + "'");
                set = true;
            }
        }

        if (!usuario.getUltimoAcceso().equals(usuario2.getUltimoAcceso())) {
            if (set) {
                sql.append(", UltimoAcceso = '" + new java.sql.Timestamp(usuario.getUltimoAcceso().getTime()) + "'");
            } else {
                sql.append("SET UltimoAcceso = '" + new java.sql.Timestamp(usuario.getUltimoAcceso().getTime()) + "'");
                set = true;
            }
        }

        if (!usuario.getTipo().equals(usuario2.getTipo())) {
            if (set) {
                sql.append(", Tipo = '" + usuario.getTipo() + "'");
            } else {
                sql.append("SET Tipo = '" + usuario.getTipo() + "'");
                set = true;
            }
        }

        if (!usuario.getBloqueado().equals(usuario2.getBloqueado())) {
            if (set) {
                sql.append(", Bloqueado = '" + usuario.getBloqueado() + "'");
            } else {
                sql.append("SET Bloqueado = '" + usuario.getBloqueado() + "'");
                set = true;
            }
        }

        if (set) {
            sql.append(" WHERE IdUsuario = '" + usuario.getIdUsuario() + "'");
            Statement sentencia = null;
            try {
                sentencia = ConnectionFactory.getConnection().createStatement();
                sentencia.executeUpdate(sql.toString());
                sentencia.close();
                errorSQL = 0;
            } catch (SQLException ex) {
                Logger.getLogger(UsuariosDAO.class.getName()).log(Level.SEVERE, null, ex);
                errorSQL = ex.getErrorCode();
            }
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
