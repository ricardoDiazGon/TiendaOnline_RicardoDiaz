/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.eventos;

import es.albarregas.beans.Caracteristica;
import es.albarregas.beans.Categoria;
import es.albarregas.beans.General;
import es.albarregas.beans.Producto;
import es.albarregas.dao.ICaracteristicasDAO;
import es.albarregas.dao.ICategoriasDAO;
import es.albarregas.dao.IGeneralDAO;
import es.albarregas.dao.IIMagenesDAO;
import es.albarregas.dao.IPedidosDAO;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Ricardo
 */
public class Inicializar implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        DAOFactory df = DAOFactory.getDAOFactory(1);
        IProductosDAO ipd = df.getProductosDAO();
        ICategoriasDAO icd = df.getCategoriasDAO();
        IIMagenesDAO iid = df.getImagenesDAO();
        ICaracteristicasDAO icard = df.getCaracteristicasDAO();
        IGeneralDAO igd = df.getGeneralDAO();

        ArrayList<Producto> listaProductos = ipd.getProductos("WHERE FueraCatalogo = 'n'");
        ArrayList<Categoria> listaCategorias = icd.getCategorias("");

        //Introducimos las imagenes y las caracteristicas en los productos
        for (Producto producto : listaProductos) {
            producto.setImagenes(iid.getImagenes("WHERE IdProducto = " + producto.getIdProducto()));
            producto.setCaracteristicas(icard.getCaracteristicas("WHERE IdProducto = " + producto.getIdProducto()));
        }

        //Cargamos los productos del slider
        ArrayList<Producto> listaProductosSlider = new ArrayList();
        int cont = 0;
        for (int i = 0; i < listaProductos.size() && cont < 4; i++) {
            if (listaProductos.get(i).getOferta().equals("s")) {
                listaProductosSlider.add(listaProductos.get(i));
                cont++;
            }
        }

        General general = igd.getGeneral("");
        
        ServletContext contexto = sce.getServletContext();
        synchronized (contexto) {
            contexto.setAttribute("productos", listaProductos);
            contexto.setAttribute("categorias", listaCategorias);
            contexto.setAttribute("proSlider", listaProductosSlider);
            contexto.setAttribute("general", general);
        }
        
        //Llamamos a la función para eliminar de la BD aquellos carritos que lleven más de una semana inactivos
        comprobarCarritosAntiguos();

    }
    
    
    //Función para eliminar de la BD aquellos carritos que lleven más de una semana inactivos
    public void comprobarCarritosAntiguos(){
        DAOFactory df = DAOFactory.getDAOFactory(1);
        IPedidosDAO iped = df.getPedidosDAO();
        //La diferencia del datediff es en días, entre la fecha actual y la del carrito
        //Si es mayor que 7 días, se elimina de la base de datos
        iped.delPedidos("WHERE Estado = 'n' and datediff(now(), fecha) > 7");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext contexto = sce.getServletContext();

        synchronized (contexto) {
            contexto.removeAttribute("productos");
            contexto.removeAttribute("categorias");
            contexto.removeAttribute("proSlider");
            contexto.removeAttribute("general");
        }
    }

}
