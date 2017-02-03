/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.servletsAjax;

import es.albarregas.beans.LineaPedido;
import es.albarregas.beans.Pedido;
import es.albarregas.dao.ILineasPedidosDAO;
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
@WebServlet(name = "ModificarCantidadCarrito", urlPatterns = {"/modificarCantidadCarrito"})
public class ModificarCantidadCarrito extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        
        String idPedido = null;
        String numeroLinea = null;
        String cantidad = null;
        try {
            idPedido = request.getParameter("npe");
            numeroLinea = request.getParameter("nli");
            cantidad = request.getParameter("can");
        } catch (NullPointerException ex) {
            response.sendRedirect("index.jsp");
        }

        try {

            DAOFactory df = DAOFactory.getDAOFactory(1);
            ILineasPedidosDAO ilpd = df.getLineasPedidosDAO();

            LineaPedido lineaPedido = new LineaPedido();

            lineaPedido.setNumeroLinea(Integer.parseInt(numeroLinea));
            lineaPedido.setIdPedido(Integer.parseInt(idPedido));
            lineaPedido.setCantidad(Integer.parseInt(cantidad));

            int errorSql = ilpd.updLineasPedidos(lineaPedido);
            ArrayList<LineaPedido> listaLineaPedidos = ilpd.getLineasPedidos("WHERE IdPedido = " +Integer.parseInt(idPedido));
            Pedido pedido = (Pedido) request.getSession().getAttribute("carrito");
            pedido.setLineasPedidos(listaLineaPedidos);
            request.getSession().setAttribute("carrito", pedido);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
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
