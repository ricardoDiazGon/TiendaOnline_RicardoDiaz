package es.albarregas.dao;

import es.albarregas.beans.Provincia;
import java.util.ArrayList;

public interface IProvinciasDAO {
    
    public ArrayList<Provincia> getProvincias(String clausulaWhere);
    
    public void closeConnection();
}
