/*
    Controlador donde hacemos la búsqueda avanzada
    Recibimos los parámetros del formulario de búsqueda avanzada, realizamos la
    Where en la tabla productos y llevamos los productos a la página de salida
 */
package es.albarregas.controladores;

import es.albarregas.beans.Producto;
import es.albarregas.dao.ICaracteristicasDAO;
import es.albarregas.dao.IIMagenesDAO;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ricardo
 */
@WebServlet(name = "BusquedaAvanzada", urlPatterns = {"/busquedaAvanzada"})
public class BusquedaAvanzada extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String url = "/jsp/comun/paginaBusqueda.jsp";
        
        String marca = request.getParameter("marca");
        String oferta = request.getParameter("oferta");
        String descripcion = request.getParameter("descripcion");
        String precio = request.getParameter("precio");
        
        //Cogemos el precio o minimo y maximo, en precio viene asi p.e. 150-300
        String[] precios = precio.split("-");
        int precioMin = Integer.parseInt(precios[0]);
        int precioMax = Integer.parseInt(precios[1]);
        
        DAOFactory df = DAOFactory.getDAOFactory(1);
        IProductosDAO iprd = df.getProductosDAO();
        IIMagenesDAO iid = df.getImagenesDAO();
        ICaracteristicasDAO icard = df.getCaracteristicasDAO();
        
        //Vamos a ir construyendo la clausula where con los parámetros que hemos recibido
        String clausulaWhere = "WHERE ";
        
        if(!descripcion.isEmpty()){
            clausulaWhere += "Descripcion LIKE '%" +descripcion +"%' AND ";
        }
        
        if(!marca.equals("todas")){
            clausulaWhere += "P.IdMarca = " +Integer.parseInt(marca) +" AND ";
        }
        
        clausulaWhere += "P.Oferta = '" +oferta +"'"
                + " AND P.PrecioUnitario BETWEEN " +precioMin + " AND " 
                +precioMax + " ORDER BY P.PrecioUnitario ASC";
        ArrayList<Producto> listaProductos = iprd.getProductos(clausulaWhere);

        //Cargamos las imagenes y características en productos
        for (Producto producto : listaProductos) {
            producto.setImagenes(iid.getImagenes("WHERE IdProducto = " + producto.getIdProducto()));
            producto.setCaracteristicas(icard.getCaracteristicas("WHERE IdProducto = " + producto.getIdProducto()));
        }
        
        request.setAttribute("productosBus", listaProductos);
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = "";
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
