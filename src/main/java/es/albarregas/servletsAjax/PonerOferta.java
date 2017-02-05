/*
    Aquí ponemos y quitamos los productos de oferta
 */
package es.albarregas.servletsAjax;

import es.albarregas.beans.Producto;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "PonerOferta", urlPatterns = {"/ponerOferta"})
public class PonerOferta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        if(request.getParameter("id") != null && request.getParameter("accion") != null){
            
            String idProducto = request.getParameter("id");
            String accion = request.getParameter("accion");
            DAOFactory df = DAOFactory.getDAOFactory(1);
            IProductosDAO iprd = df.getProductosDAO();
            ArrayList<Producto> listaProductos = iprd.getProductos("WHERE IdProducto = " +idProducto);
            Producto producto = null;
            //Modificamos el producto seleccionado
            for(Producto productoAux : listaProductos){
                //Comprobamos si está en oferta o no y lo cambiamos
                if(accion.equals("ofertar")){
                    productoAux.setOferta("s");
                }else{
                    productoAux.setOferta("n");
                }
                
                producto = productoAux;
                //Actualizamos el producto, sacamos los productos que no están descatalogados y los actualizamos
                // en el contexto
                iprd.updProductos(producto);
                listaProductos = iprd.getProductos("WHERE FueraCatalogo = 'n'");
                request.getServletContext().setAttribute("productos", listaProductos);
            }
            
            
            
            
        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
