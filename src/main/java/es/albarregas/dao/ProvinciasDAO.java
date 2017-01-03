package es.albarregas.dao;

import es.albarregas.beans.Provincia;
import es.albarregas.beans.Pueblo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProvinciasDAO implements IProvinciasDAO {

    @Override
    public ArrayList<Provincia> getProvincias(String clausulaWhere) {
        String sql = "SELECT * FROM Provincias " + clausulaWhere;
        Statement sentencia = null;
        ResultSet resultado = null;
        ArrayList<Provincia> listaProvincias = null;
        try {
            sentencia = ConnectionFactory.getConnection().createStatement();
            resultado = sentencia.executeQuery(sql);

            listaProvincias = new ArrayList();
            Provincia provincia = null;
            while (resultado.next()) {
                provincia = new Provincia();
                provincia.setIdProvincia(resultado.getInt("IdProvincia"));
                provincia.setNombre(resultado.getString("Nombre"));
                listaProvincias.add(provincia);
            }

        } catch (SQLException ex) {
            Logger.getLogger(PueblosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConnection();
        return listaProvincias;
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }

}
