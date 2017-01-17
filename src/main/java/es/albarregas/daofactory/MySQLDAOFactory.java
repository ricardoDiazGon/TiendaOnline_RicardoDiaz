package es.albarregas.daofactory;

import es.albarregas.dao.CaractYProdsDAO;
import es.albarregas.dao.CaracteristicasDAO;
import es.albarregas.dao.CategoriasDAO;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.ClientesDAO;
import es.albarregas.dao.DireccionesDAO;
import es.albarregas.dao.GeneralDAO;
import es.albarregas.dao.ICaractYProdsDAO;
import es.albarregas.dao.ICaracteristicasDAO;
import es.albarregas.dao.ICategoriasDAO;
import es.albarregas.dao.IDireccionesDAO;
import es.albarregas.dao.IGeneralDAO;
import es.albarregas.dao.IIMagenesDAO;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.dao.IProvinciasDAO;
import es.albarregas.dao.IPueblosDAO;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.dao.ImagenesDAO;
import es.albarregas.dao.ProductosDAO;
import es.albarregas.dao.ProvinciasDAO;
import es.albarregas.dao.PueblosDAO;
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

    public IDireccionesDAO getDireccionesDAO() {
        return new DireccionesDAO();
    }

    public IPueblosDAO getPueblosDAO() {
        return new PueblosDAO();
    }

    public IProvinciasDAO getProvinciasDAO() {
        return new ProvinciasDAO();
    }

    public IIMagenesDAO getImagenesDAO() {
        return new ImagenesDAO();
    }
    
    public IProductosDAO getProductosDAO() {
        return new ProductosDAO();
    }
    
    public ICategoriasDAO getCategoriasDAO(){
        return new CategoriasDAO();
    }
    
    public IGeneralDAO getGeneralDAO(){
        return new GeneralDAO();
    }
    
    public ICaracteristicasDAO getCaracteristicasDAO(){
        return new CaracteristicasDAO();
    }
    
    public ICaractYProdsDAO getCaractYProdsDAO(){
        return new CaractYProdsDAO();
    }
}
