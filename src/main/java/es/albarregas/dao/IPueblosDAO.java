package es.albarregas.dao;

import es.albarregas.beans.Pueblo;
import java.util.ArrayList;

public interface IPueblosDAO {
    
    public ArrayList<Pueblo> getPueblos(String clausulaWhere);
    
    public void closeConnection();
    
}
