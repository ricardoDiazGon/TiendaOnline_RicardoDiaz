/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controladores;

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
@WebServlet(name = "ActualizarProAdm", urlPatterns = {"/actualizarProAdm"})
public class ActualizarProAdm extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        int pagina = 1;
        if (request.getParameter("pag") != null) {
            pagina = Integer.parseInt(request.getParameter("pag")); //Número de página
        }

        int min = 15 * (pagina - 1); //20 productos por página
        String url = "";
        DAOFactory df = DAOFactory.getDAOFactory(1);
        IProductosDAO iprd = df.getProductosDAO();
        ArrayList<Producto> listaProductosTotal = iprd.getProductos("");
        int total = listaProductosTotal.size();
        ArrayList<Producto> listaProductos = iprd.getProductos("LIMIT " +min +", 15");
        //Cargamos todos los usuarios con sus respectivos datos de clientes

        request.setAttribute("listaProductos", listaProductos);

        int pag = (int) Math.ceil(Double.valueOf(total) / Double.valueOf(15));
        request.setAttribute("actual", pagina);
        request.setAttribute("pag", pag);
        url = "jsp/administrador/panelActPro.jsp";
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
