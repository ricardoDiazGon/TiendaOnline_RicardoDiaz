/*
    Este servlet de ajax no tiene más cometido que eliminar lineas del carrito de la compra
    o el mismo carrito de la compra entero. Cuando eliminamos la ultima linea de pedido elimina el pedido
 */
package es.albarregas.servletsAjax;

import es.albarregas.beans.LineaPedido;
import es.albarregas.beans.Pedido;
import es.albarregas.dao.ILineasPedidosDAO;
import es.albarregas.dao.IPedidosDAO;
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
@WebServlet(name = "EliminarCarrito", urlPatterns = {"/eliminarCarrito"})
public class EliminarCarrito extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        /*  - Este es el controlador en el que podemos borrar las lineas de pedidos y los pedidos enteros 
            que están en el carrito de la compra, o más adelante quizá en el panel de control también.
            - Solo se puede hacer una de estas dos opciones a la vez, aunque como se puede ver en el código,
            si eliminas la última linea de pedido del carrito, se elimina también el pedido, para respetar la
            integridad de nuestra base de datos y que no haya datos zombi...
        */
        
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        DAOFactory df = DAOFactory.getDAOFactory(1);
        IPedidosDAO ipd = df.getPedidosDAO();
        ILineasPedidosDAO ilpd = df.getLineasPedidosDAO();
        PrintWriter out = response.getWriter();
        String error = "";
        int errorSql = 0;

        //Si queremos eliminar alguna linea de pedido
        if (request.getParameter("nli") != null && !request.getParameter("nli").equals("null")) {

            String numeroLinea = request.getParameter("nli");
            String idPedido = request.getParameter("npe");

            errorSql = ilpd.delLineasPedidos("WHERE IdPedido = " + idPedido + " AND NumeroLinea = " + numeroLinea);

            if (errorSql == 0) {
                error = "ok";

                ArrayList<LineaPedido> listaLineasPedidos = ilpd.getLineasPedidos("WHERE IdPedido = " + idPedido);

                //Si nos devuelve null al hacer la consulta es que no quedan lineasPedido, por tanto borramos pedido
                if (listaLineasPedidos.isEmpty()) {
                    //Como eliminamos pedido eliminamos su atributo de sesión
                    ipd.delPedidos("WHERE IdPedido = " +idPedido);
                    request.getSession().removeAttribute("carrito");
                } else {
                    //Si quedan lineas de pedido pues vamos a cargar el pedido actual en la sesión
                    Pedido pedido = (Pedido) request.getSession().getAttribute("carrito");
                    pedido.setLineasPedidos(listaLineasPedidos);
                    request.getSession().setAttribute("carrito", pedido);
                }

            } else {
                error = "No se ha podido eliminar el pedido " + idPedido;
            }
            out.print(error);
            out.flush();
            out.close();

            //Si queremos eliminar algún pedido
        } else if (request.getParameter("npe") != null) {

            String idPedido = request.getParameter("npe");

            errorSql = ipd.delPedidos("WHERE IdPedido = " + Integer.parseInt(idPedido));

            if (errorSql == 0) {
                error = "ok";
                //Si hemos eliminado el pedido eliminamos su atributo de sesión
                request.getSession().removeAttribute("carrito");

            } else {
                error = "No se ha podido eliminar el pedido " + idPedido;
            }
            out.print(error);
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
