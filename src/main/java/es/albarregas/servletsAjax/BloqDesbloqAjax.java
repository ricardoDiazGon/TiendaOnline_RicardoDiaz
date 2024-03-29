/*
    En este servlet realizamos con Ajax el bloqueo y desbloqueo de los usuarios y productos
 */
package es.albarregas.servletsAjax;

import es.albarregas.beans.LineaPedido;
import es.albarregas.beans.Pedido;
import es.albarregas.beans.Producto;
import es.albarregas.beans.Usuario;
import es.albarregas.dao.ILineasPedidosDAO;
import es.albarregas.dao.IPedidosDAO;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BloqDesbloqAjax", urlPatterns = {"/bloqDesbloqAjax"})
public class BloqDesbloqAjax extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String entidad = request.getParameter("entidad");
        String id = request.getParameter("id");
        String accion = request.getParameter("accion");
        DAOFactory df = DAOFactory.getDAOFactory(1);
        String estado = "n"; //variable con la que vemos si bloqueamos o desbloqueamos
        if (entidad.equals("usuario")) {
            IUsuariosDAO iud = df.getUsuariosDAO();
            Usuario usuario = iud.getUsuarios("WHERE IdUsuario = " + id).get(0);

            if (accion.equals("bloquear")) {
                estado = "s";
            }
            usuario.setIdUsuario(Integer.parseInt(id));
            usuario.setBloqueado(estado);
            iud.updUsuarios(usuario);
        } else {
            boolean enCarrito = false;
            IProductosDAO iprd = df.getProductosDAO();
            IPedidosDAO iped = df.getPedidosDAO();
            ILineasPedidosDAO ilped = df.getLineasPedidosDAO();
            ArrayList<LineaPedido> lineasPedidos = ilped.getLineasPedidos("WHERE IdProducto = " + id);
            ArrayList<Pedido> pedidos = null;
            for (LineaPedido lineaPedido : lineasPedidos) {
                pedidos = iped.getPedidos("WHERE IdPedido = " + lineaPedido.getIdPedido());
                for (Pedido pedido : pedidos) {
                    if (pedido.getEstado().equals("n")) {
                        //Si hay un pedido que esté en carrito (n), no se va a poder bloquear
                        enCarrito = true;
                    }
                }
            }

            if (enCarrito) {
            PrintWriter out = response.getWriter();
            out.print("El producto no se puede descatalogar porque un cliente lo tiene en el carrito");
            out.flush();
            out.close();
            } else {
                Producto producto = iprd.getProductos("WHERE IdProducto = " + id).get(0);
                if (accion.equals("descatalogar")) {
                    estado = "s";
                }
                producto.setIdProducto(Integer.parseInt(id));
                producto.setFueraCatalogo(estado);
                iprd.updProductos(producto);
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
