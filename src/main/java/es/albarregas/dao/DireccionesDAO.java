package es.albarregas.dao;

import es.albarregas.beans.Direccion;
import es.albarregas.daofactory.DAOFactory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DireccionesDAO implements IDireccionesDAO {

    @Override
    public int addDirecciones(Direccion direccion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Direccion> getDirecciones(String clausulaWhere) {

        String sql = "SELECT * FROM Direccions " + clausulaWhere;
        Statement sentencia = null;
        ResultSet resultado = null;
        ArrayList<Direccion> listaDirecciones = null;
        try {
            DAOFactory df = DAOFactory.getDAOFactory(1);
            IPueblosDAO ipud = df.getPueblosDAO();

            sentencia = ConnectionFactory.getConnection().createStatement();
            resultado = sentencia.executeQuery(sql);

            listaDirecciones = new ArrayList();
            Direccion direccion = null;
            while (resultado.next()) {
                direccion = new Direccion();
                direccion.setIdDireccion(resultado.getInt("IdDireccion"));
                direccion.setIdCliente(resultado.getInt("IdCliente"));
                direccion.setCodigoPostal(resultado.getString("CodigoPostal"));
                direccion.setNombreDireccion(resultado.getString("NombreDireccion"));
                direccion.setDireccion(resultado.getString("Direccion"));
                direccion.setTelefono(resultado.getString("Telefono"));
                direccion.setPueblo(ipud.getPueblos("").get(0));
                listaDirecciones.add(direccion);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DireccionesDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConnection();
        return listaDirecciones;
    }

    @Override
    public int updDirecciones(Direccion direccion) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delDirecciones(String clausulaWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
