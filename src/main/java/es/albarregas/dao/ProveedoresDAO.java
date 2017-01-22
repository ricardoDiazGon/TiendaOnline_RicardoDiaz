/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Proveedor;
import java.sql.Date;
import java.sql.PreparedStatement;
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
public class ProveedoresDAO implements IProveedoresDAO{

    @Override
    public int addProveedores(Proveedor proveedor) {
        int errorSQL = 0;
        String sql = null;

        sql = "INSERT INTO Proveedores VALUES(0,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(sql);
            preparada.setString(1, proveedor.getRazonSocial());
            preparada.setString(2, proveedor.getCIF());
            preparada.setString(3, proveedor.getDireccion());
            preparada.setString(4, proveedor.getCodigoPostal());
            preparada.setInt(5, proveedor.getIdPueblo());
            preparada.setInt(6, proveedor.getIdProvincia());
            preparada.setString(7, proveedor.getTelefono());
            preparada.setString(8, proveedor.getEmail());
            preparada.setString(9, proveedor.getWeb());
            preparada.setString(10, proveedor.getPersonaContacto());
            preparada.setDate(11, new Date(proveedor.getFechaAlta().getTime()));
            preparada.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProveedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
            errorSQL = ex.getErrorCode();
        }

        this.closeConnection();
        System.out.println("Error sql " + errorSQL);
        return errorSQL;
    }

    @Override
    public ArrayList<Proveedor> getProveedores(String clausulaWhere) {
        String sql = "SELECT * FROM Proveedores " + clausulaWhere;
        Statement sentencia;
        ArrayList<Proveedor> listaProveedores = null;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);

            listaProveedores = new ArrayList();
            Proveedor proveedor = null;
            while (resultado.next()) {
                proveedor = new Proveedor();
                proveedor.setIdProveedor(resultado.getInt("IdProveedor"));
                proveedor.setRazonSocial(resultado.getString("RazonSocial"));
                proveedor.setCIF(resultado.getString("CIF"));
                proveedor.setDireccion(resultado.getString("Direccion"));
                proveedor.setCodigoPostal(resultado.getString("CodigoPostal"));
                proveedor.setIdPueblo(resultado.getInt("IdPueblo"));
                proveedor.setTelefono(resultado.getString("Telefono"));
                proveedor.setEmail(resultado.getString("Email"));
                proveedor.setWeb(resultado.getString("Web"));
                proveedor.setPersonaContacto(resultado.getString("PersonaContacto"));
                proveedor.setFechaAlta(resultado.getDate("FechaAlta"));
                listaProveedores.add(proveedor);
            }

            resultado.close();
            sentencia.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProveedoresDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConnection();
        return listaProveedores;
    }

    @Override
    public int updProveedores(Proveedor proveedor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delProveedores(String clausulaWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
    
}
