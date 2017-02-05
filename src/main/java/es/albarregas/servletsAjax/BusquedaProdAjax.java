/*
    Como se puede ver, tenemos un buscador muy eficiente y totalmente dinámico con ajax, 
    pues este es el que se encarga de encontrar en los productos aquellos que empiecen por
    la cadena que le pasamos...
 */
package es.albarregas.servletsAjax;

import com.google.gson.Gson;
import es.albarregas.beans.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BusquedaProdAjax", urlPatterns = {"/busquedaProdAjax"})
public class BusquedaProdAjax extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        /* 
            En este controlador se hace la busqueda de productos dinamica,
            se encuentran los que empiecen por la cadena que se pasa
         */
        if (request.getParameter("cad") != null) {
            String cadena = request.getParameter("cad").trim();

            ArrayList<Producto> listaProductos = (ArrayList<Producto>) request.getServletContext().getAttribute("productos");

            ArrayList<Producto> productosEncontrados = new ArrayList();
            for (Producto producto : listaProductos) {
                if (producto.getDenominacion().toLowerCase().startsWith(cadena.toLowerCase())) {
                    productosEncontrados.add(producto);
                }
            }

            PrintWriter out = response.getWriter();
            Gson json = new Gson();
            out.print(json.toJson(productosEncontrados));
            out.flush();
            out.close();

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
