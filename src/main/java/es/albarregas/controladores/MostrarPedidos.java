/*
    En este controlador enviamos los pedidos de usuario o administrador
 */
package es.albarregas.controladores;

import es.albarregas.beans.Cliente;
import es.albarregas.beans.Direccion;
import es.albarregas.beans.Pedido;
import es.albarregas.beans.Usuario;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IDireccionesDAO;
import es.albarregas.dao.IPedidosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "MostrarPedidos", urlPatterns = {"/mostrarPedidos"})
public class MostrarPedidos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        int pagina = 1;
        if (request.getParameter("pag") != null) {
            pagina = Integer.parseInt(request.getParameter("pag")); //Número de página
        }

        int min = 5 * (pagina - 1); //5 pedidos por página        
        
        HttpSession sesion = request.getSession(true);
        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        DAOFactory df = DAOFactory.getDAOFactory(1);
        IPedidosDAO iped = df.getPedidosDAO();
        IClientesDAO icd = df.getClientesDAO();
        IDireccionesDAO idd = df.getDireccionesDAO();
        ArrayList<Pedido> listaPedidos = null;
        String url = "";

        /* 
            En este if entramos si nos piden pasar un producto de enviado a recibido 
            No es el lugar idoneo para ponerlo, pero lo pongo aquí para no aumentar demasiado
            el número de controladores/servlets
        */
        if (request.getParameter("idPed") != null) {
            String idPedido = request.getParameter("idPed");
            listaPedidos = iped.getPedidos("WHERE IdPedido = " + idPedido);
            for (Pedido pedido : listaPedidos) {
                if (pedido.getEstado().equals("x")) {
                    pedido.setEstado("r");
                    iped.updPedidos(pedido);
                }

            }
        }

        int total = 0;
        //Si lo busca el usuario
        if (usuario.getTipo().equals("u")) {
            listaPedidos = iped.getPedidos("WHERE IdCliente = " + usuario.getIdUsuario() + " AND Estado <> 'n' "
                    + "ORDER BY Fecha DESC");
            total = listaPedidos.size();
            listaPedidos = iped.getPedidos("WHERE IdCliente = " + usuario.getIdUsuario() + " AND Estado <> 'n' "
                    + "ORDER BY Fecha DESC LIMIT " +min +",5");
            ArrayList<Direccion> listaDirecciones = idd.getDirecciones("WHERE IdCliente = " + usuario.getIdUsuario());
            for (Pedido pedido : listaPedidos) {
                for (Direccion direccion : listaDirecciones) {
                    if (pedido.getDireccion().getIdDireccion() == direccion.getIdDireccion()) {
                        pedido.setDireccion(direccion);
                        break;
                    }
                }
            }
            url = "/jsp/cliente/panelPedidosCli.jsp";

            //Si lo busca el administrador
        } else {
            listaPedidos = iped.getPedidos("WHERE Estado <> 'n' ORDER BY Fecha DESC");
            total = listaPedidos.size();
            listaPedidos = iped.getPedidos("WHERE Estado <> 'n' ORDER BY Fecha DESC LIMIT " +min +",5");
            ArrayList<Cliente> listaClientes = icd.getClientes("");
            for (Pedido pedido : listaPedidos) {
                for (Cliente cliente : listaClientes) {
                    if (cliente.getIdCliente() == pedido.getCliente().getIdCliente()) {
                        pedido.setCliente(cliente);
                        break;
                    }
                }
            }
            url = "/jsp/administrador/panelPedidosAdm.jsp";
        }

        request.setAttribute("listaPedidos", listaPedidos);
        int pag = (int) Math.ceil(Double.valueOf(total) / Double.valueOf(5));
        request.setAttribute("actual", pagina);
        request.setAttribute("pag", pag);
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
