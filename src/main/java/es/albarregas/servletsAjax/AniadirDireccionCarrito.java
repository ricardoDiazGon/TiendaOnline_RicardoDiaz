/*
    Servlet para ajax con el que añadimos las direcciones desde el carrito.
    Podriamos haber usado el otro controlador que hace esto, pero el problema de fondo
    es que como el carrito casi en su totalidad está compuesto por paginas insertadas en capas
    y por carga en ajax, resulta más cómodo tener controladores a parte, ya que su naturaleza es distinta.
 */
package es.albarregas.servletsAjax;

import es.albarregas.beans.Direccion;
import es.albarregas.beans.Pedido;
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
@WebServlet(name = "AniadirDireccionCarrito", urlPatterns = {"/aniadirDireccionCarrito"})
public class AniadirDireccionCarrito extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        try {
            String idDireccion = request.getParameter("idDir");

            HttpSession sesion = request.getSession(true);
            Pedido carrito = (Pedido) sesion.getAttribute("carrito");
            Direccion direccion = new Direccion();
            direccion.setIdDireccion(Integer.parseInt(idDireccion));
            carrito.setDireccion(direccion);
            sesion.setAttribute("carrito", carrito);
            response.getWriter().println("ok");
        } catch (NumberFormatException | NullPointerException ex) {
            response.getWriter().println("Los datos no se han guardado en sesión");
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
