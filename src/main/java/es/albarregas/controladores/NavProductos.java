/*
    Controlador de la navegación entre productos. También para cuando seleccionamos alguno para ampliarlo
 */
package es.albarregas.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Ricardo
 */
@WebServlet(name = "NavProductos", urlPatterns = {"/navProductos"})
public class NavProductos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String url = "";
        try {

            String opcion = request.getParameter("opt");
            String param = request.getParameter("param");
            
            switch(opcion){
                //en amp Param sería el id de producto. Se redirige a la ventana del producto
                case "amp": url="/jsp/comun/paginaProducto.jsp"; 
                request.setAttribute("idProducto", Integer.parseInt(param));
                break;
                
                //Si hemos seleccionado una categoría en el menú de navegación
                case "cat": url="/jsp/comun/paginaCategoria.jsp"; 
                request.setAttribute("idCategoria", Integer.parseInt(param));
                break;
            }
            
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

        request.getRequestDispatcher(url).forward(request, response);
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
