/*
    En este controlador solo vamos a redireccionar hacia el carrito, 
    No tiene mucho sentido, pero lo tengo por el MVC y por si en futuras
    versiones fuese necesario.
 */
package es.albarregas.controladores;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ricardo
 */
@WebServlet(name = "Carrito", urlPatterns = {"/carrito"})
public class Carrito extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = "";

        HttpSession sesion = request.getSession(true);
        
        if(sesion.getAttribute("usuario") != null){
            url = "/jsp/cliente/carrito.jsp";
        }
        
        
        response.sendRedirect(request.getContextPath() + url);
        
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
