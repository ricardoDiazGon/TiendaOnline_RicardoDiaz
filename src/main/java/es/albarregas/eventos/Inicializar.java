/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.eventos;

import es.albarregas.beans.Caracteristica;
import es.albarregas.beans.Categoria;
import es.albarregas.beans.Producto;
import es.albarregas.dao.ICaracteristicasDAO;
import es.albarregas.dao.ICategoriasDAO;
import es.albarregas.dao.IIMagenesDAO;
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

        ServletContext contexto = sce.getServletContext();
        synchronized (contexto) {
            contexto.setAttribute("productos", listaProductos);
            contexto.setAttribute("categorias", listaCategorias);
            contexto.setAttribute("proSlider", listaProductosSlider);
        }

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext contexto = sce.getServletContext();

        synchronized (contexto) {
            contexto.removeAttribute("productos");
            contexto.removeAttribute("categorias");
            contexto.removeAttribute("proSlider");
        }
    }

}
