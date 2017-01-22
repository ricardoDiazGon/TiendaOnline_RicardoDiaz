package es.albarregas.dao;

import es.albarregas.beans.Cliente;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLType;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientesDAO implements IClientesDAO {

    @Override
    public int addClientes(Cliente cliente) {
        int errorSQL = 0;
        String sql = null;

        sql = "INSERT INTO Clientes VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setInt(1, cliente.getIdCliente());
            preparada.setString(2, cliente.getNombre());
            preparada.setString(3, cliente.getApellidos());
            preparada.setString(4, cliente.getNIF());
            preparada.setNull(5, java.sql.Types.DATE);
            preparada.setTimestamp(6, new java.sql.Timestamp(cliente.getFechaAlta().getTime()));

            preparada.executeUpdate();

            //Cerramos las conexiones
            preparada.close();

        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            errorSQL = ex.getErrorCode();
        }

        this.closeConnection();
        System.out.println("Error sql addCliente " + errorSQL);
        return errorSQL;
    }

    @Override
    public ArrayList<Cliente> getClientes(String clausulaWhere) {
        String sql = "SELECT * FROM Clientes " + clausulaWhere;
        Statement sentencia;
        ArrayList<Cliente> listaClientes = null;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);

            listaClientes = new ArrayList();
            Cliente cliente = null;
            while (resultado.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(resultado.getInt("IdCliente"));
                cliente.setNombre(resultado.getString("Nombre"));
                cliente.setApellidos(resultado.getString("Apellidos"));
                cliente.setNIF(resultado.getString("NIF"));
                cliente.setFechaNacimiento(resultado.getDate("FechaNacimiento"));
                cliente.setFechaAlta(resultado.getDate("FechaAlta"));
                listaClientes.add(cliente);
            }

            resultado.close();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConnection();
        return listaClientes;
    }

    @Override
    public int updClientes(Cliente cliente) {
        int errorSQL = -1; //Ponemos a -1, para saber hemos realizado la consulta o no
        boolean set = false;
        ArrayList<Cliente> listaClientes = this.getClientes("WHERE IdCliente = '" + cliente.getIdCliente() + "'");
        Cliente cliente2 = listaClientes.get(0);
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Clientes ");
        /* 
            Actualizamos solamente los datos que nos vengan diferentes, para ello comparamos con los datos obtenidos
            de la base de datos. Los que sean iguales se dejan.
         */
        if (!cliente.getNombre().equals(cliente2.getNombre())) {
            sql.append("SET Nombre = '" + cliente.getNombre() + "'");
            set = true;
        }

        if (!cliente.getApellidos().equals(cliente2.getApellidos())) {
            if (set) {
                sql.append(", Apellidos= '" + cliente.getApellidos() + "'");
            } else {
                sql.append("SET Apellidos = '" + cliente.getApellidos() + "'");
                set = true;
            }
        }

        if (!cliente.getNIF().equals(cliente2.getNIF())) {
            if (set) {
                sql.append(", NIF = '" + cliente.getNIF() + "'");
            } else {
                sql.append("SET NIF = '" + cliente.getNIF() + "'");
                set = true;
            }
        }

        if (!cliente.getFechaNacimiento().equals(cliente2.getFechaNacimiento())) {
            if (set) {
                sql.append(", FechaNacimiento = '" + new Date(cliente.getFechaNacimiento().getTime()) + "'");
            } else {
                sql.append("SET FechaNacimiento = '" + new Date(cliente.getFechaNacimiento().getTime()) + "'");
                set = true;
            }
        }

        if (set) {
            sql.append(" WHERE IdCliente = '" + cliente.getIdCliente() + "'");
            Statement sentencia = null;
            try {
                sentencia = ConnectionFactory.getConnection().createStatement();
                sentencia.executeUpdate(sql.toString());
                sentencia.close();
                errorSQL = 0;
            } catch (SQLException ex) {
                Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
                errorSQL = ex.getErrorCode();
            }
        }
        this.closeConnection();
        return errorSQL;
    }

    @Override
    public int delClientes(String clausulaWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}
