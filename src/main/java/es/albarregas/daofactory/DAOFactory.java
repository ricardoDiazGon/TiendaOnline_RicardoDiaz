package es.albarregas.daofactory;

import es.albarregas.dao.ICaractYProdsDAO;
import es.albarregas.dao.ICaracteristicasDAO;
import es.albarregas.dao.ICategoriasDAO;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IDireccionesDAO;
import es.albarregas.dao.IGeneralDAO;
import es.albarregas.dao.IIMagenesDAO;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.dao.IProvinciasDAO;
import es.albarregas.dao.IPueblosDAO;
import es.albarregas.dao.IUsuariosDAO;

public abstract class DAOFactory {

    public static final int MYSQL = 1;
    public static final int ORACLE = 2;
    public static final int FICHERO = 3;

    public abstract IClientesDAO getClientesDAO();
    public abstract IUsuariosDAO getUsuariosDAO();
    public abstract IDireccionesDAO getDireccionesDAO();
    public abstract IPueblosDAO getPueblosDAO();
    public abstract IProvinciasDAO getProvinciasDAO();
    public abstract IIMagenesDAO getImagenesDAO();
    public abstract IProductosDAO getProductosDAO();
    public abstract ICategoriasDAO getCategoriasDAO();
    public abstract IGeneralDAO getGeneralDAO();
    public abstract ICaracteristicasDAO getCaracteristicasDAO();
    public abstract ICaractYProdsDAO getCaractYProdsDAO();
    
    
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
