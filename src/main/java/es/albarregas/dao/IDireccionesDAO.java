package es.albarregas.dao;

import es.albarregas.beans.Direccion;
import java.util.ArrayList;

public interface IDireccionesDAO {

    public int addDirecciones(Direccion direccion);

    public ArrayList<Direccion> getDirecciones(String clausulaWhere);

    public int updDirecciones(Direccion direccion);

    public int delDirecciones(String clausulaWhere);

    public void closeConnection();
}
