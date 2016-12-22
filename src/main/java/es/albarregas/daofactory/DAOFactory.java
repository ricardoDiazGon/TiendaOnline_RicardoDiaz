package es.albarregas.daofactory;

import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IUsuariosDAO;

public abstract class DAOFactory {

    public static final int MYSQL = 1;
    public static final int ORACLE = 2;
    public static final int FICHERO = 3;

    public abstract IClientesDAO getClientesDAO();
    public abstract IUsuariosDAO getUsuariosDAO();

    public static DAOFactory getDAOFactory(int tipo) {
        DAOFactory daof = null;

        switch (tipo) {
            case MYSQL:
                daof = new MySQLDAOFactory();
                break;

            //LE PODEMOS SEGUIR AÑADIENDO TANTOS CASE COMO GESTORES DE BASE DE DATOS QUERAMOS/TENGAMOS
        }

        return daof;
    }
}
