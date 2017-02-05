package es.albarregas.dao;

import es.albarregas.beans.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuariosDAO implements IUsuariosDAO {

    static final org.apache.log4j.Logger LOG_ERROR = org.apache.log4j.Logger.getRootLogger();
    static final org.apache.log4j.Logger LOG_INFO = org.apache.log4j.Logger.getLogger(UsuariosDAO.class);

    @Override
    public int addUsuarios(Usuario usuario) {

        int errorSQL = 0;
        String sql = null;

        sql = "INSERT INTO usuarios VALUES(0,?,AES_ENCRYPT(?,'laClaveQueLePasamos'),?,?,?)";
        try {
            PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setString(1, usuario.getEmail());
            preparada.setString(2, usuario.getClave());
            preparada.setTimestamp(3, new java.sql.Timestamp(usuario.getUltimoAcceso().getTime()));
            preparada.setString(4, usuario.getTipo());
            preparada.setString(5, usuario.getBloqueado());
            preparada.executeUpdate();

            preparada.close();
            LOG_INFO.info("Se ha añadido el usuario con email " + usuario.getEmail() + " de forma exitosa");
        } catch (SQLException ex) {
            LOG_ERROR.fatal("Error SQL al añadir Usuario: " +ex.getErrorCode());
            errorSQL = ex.getErrorCode();
        }

        this.closeConnection();
        return errorSQL;
    }

    @Override
    public ArrayList<Usuario> getUsuarios(String clausulaWhere) {

        String sql = "SELECT IdUsuario, Email, AES_DECRYPT(Clave,'laClaveQueLePasamos') Clave, UltimoAcceso,"
                + "Tipo, Bloqueado FROM Usuarios " + clausulaWhere;
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
                usuario.setEmail(resultado.getString("Email"));
                usuario.setClave(resultado.getString("Clave"));
                usuario.setUltimoAcceso(resultado.getTimestamp("UltimoAcceso"));
                usuario.setTipo(resultado.getString("Tipo"));
                usuario.setBloqueado(resultado.getString("Bloqueado"));
                listaUsuarios.add(usuario);
            }
            sentencia.close();
            resultado.close();
            LOG_INFO.info("Se ha consultado usuarios de forma exitosa");
        } catch (SQLException ex) {
            LOG_ERROR.fatal("Error SQL al consultar usuarios: " +ex.getErrorCode());
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
        if (!usuario.getEmail().equals(usuario2.getEmail())) {
            sql.append("SET Email = '" + usuario.getEmail() + "'");
            set = true;
        }

        if (!usuario.getClave().equals(usuario2.getClave())) {
            if (set) {
                sql.append(", Clave = AES_ENCRYPT('" + usuario.getClave() + "', 'laClaveQueLePasamos')");
            } else {
                sql.append("SET Clave = AES_ENCRYPT('" + usuario.getClave() + "', 'laClaveQueLePasamos')");
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
                LOG_INFO.info("Se ha actualizado el usuario con ID " +usuario.getIdUsuario() +" de forma exitosa");
            } catch (SQLException ex) {
                LOG_ERROR.fatal("Error SQL al actualizar usuarios: " +ex.getErrorCode());
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
