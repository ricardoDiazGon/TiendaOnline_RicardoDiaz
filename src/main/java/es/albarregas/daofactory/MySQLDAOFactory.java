package es.albarregas.daofactory;

import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.ClientesDAO;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.dao.UsuariosDAO;

public class MySQLDAOFactory extends DAOFactory {

    @Override
    public IClientesDAO getClientesDAO() {
        return new ClientesDAO();
    }

    @Override
    public IUsuariosDAO getUsuariosDAO() {
        return new UsuariosDAO();
    }

}
