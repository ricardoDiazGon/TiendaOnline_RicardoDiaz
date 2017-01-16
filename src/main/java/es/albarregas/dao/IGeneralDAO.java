package es.albarregas.dao;

import es.albarregas.beans.General;

public interface IGeneralDAO {

    public int addGeneral(General general);

    public General getGeneral(String clausulaWhere);

    public int updGeneral(General general);

    public int delGenerals(String clausulaWhere);

    public void closeConnection();
}
