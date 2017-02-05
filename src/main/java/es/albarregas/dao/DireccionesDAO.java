package es.albarregas.dao;

import es.albarregas.beans.Direccion;
import es.albarregas.beans.Pueblo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DireccionesDAO implements IDireccionesDAO {
    
    static final org.apache.log4j.Logger LOG_ERROR = org.apache.log4j.Logger.getRootLogger();
    static final org.apache.log4j.Logger LOG_INFO = org.apache.log4j.Logger.getLogger(DireccionesDAO.class);
    
    @Override
    public int addDirecciones(Direccion direccion) {
        int errorSQL = 0;
        String sql = null;

        sql = "INSERT INTO Direcciones VALUES(0,?,?,?,?,?,?)";
        try {
            PreparedStatement preparada = ConnectionFactory.getConnection().prepareStatement(sql);
                     
            preparada.setInt(1, direccion.getIdCliente());
            preparada.setString(2, direccion.getNombreDireccion());
            preparada.setString(3, direccion.getDireccion());
            preparada.setString(4, direccion.getCodigoPostal());
            preparada.setInt(5, direccion.getIdPueblo());
            preparada.setString(6, direccion.getTelefono());
            preparada.executeUpdate();
            preparada.close();
            LOG_INFO.info("Se ha añadido direccion para el cliente con ID " + direccion.getIdCliente() + " de forma exitosa");
        } catch (SQLException ex) {
            errorSQL = ex.getErrorCode();
            LOG_ERROR.fatal("Error SQL al añadir direcciones: " +ex.getErrorCode());
        }

        this.closeConnection();
            
        return errorSQL;        
    }

    @Override
    public ArrayList<Direccion> getDirecciones(String clausulaWhere) {

        String sql = "SELECT * FROM Direcciones D INNER JOIN Pueblos P ON  D.IdPueblo =  P.IdPueblo "
                + "INNER JOIN Provincias Pr ON P.IdProvincia = Pr.IdProvincia " + clausulaWhere;
        
        Statement sentencia = null;
        ResultSet resultado = null;
        ArrayList<Direccion> listaDirecciones = null;
        try {

            sentencia = ConnectionFactory.getConnection().createStatement();
            resultado = sentencia.executeQuery(sql);

            listaDirecciones = new ArrayList();
            Direccion direccion = null;
            Pueblo pueblo = null;
            while (resultado.next()) {
                direccion = new Direccion();
                direccion.setIdDireccion(resultado.getInt("D.IdDireccion"));
                direccion.setIdCliente(resultado.getInt("D.IdCliente"));
                direccion.setCodigoPostal(resultado.getString("P.CodigoPostal"));
                direccion.setNombreDireccion(resultado.getString("D.NombreDireccion"));
                direccion.setDireccion(resultado.getString("D.Direccion"));
                direccion.setTelefono(resultado.getString("D.Telefono"));
                direccion.setIdPueblo(resultado.getInt("D.IdPueblo"));
                direccion.setNombrePueblo(resultado.getString("P.Nombre"));
                direccion.setIdProvincia(resultado.getInt("Pr.IdProvincia"));
                direccion.setNombreProvincia(resultado.getString("Pr.Nombre"));
                listaDirecciones.add(direccion);
            }
            sentencia.close();
            resultado.close();
            LOG_INFO.info("Se han consultado direcciones de forma exitosa");
        } catch (SQLException ex) {
            LOG_ERROR.fatal("Error SQL al añadir direcciones: " +ex.getErrorCode());
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
        ConnectionFactory.closeConnection();
    }

}
