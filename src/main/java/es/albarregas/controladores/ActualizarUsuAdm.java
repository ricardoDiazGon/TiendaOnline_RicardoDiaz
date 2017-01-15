/*
    Controlador donde se controla la actualización de los usuarios por parte
    del administrador
 */
package es.albarregas.controladores;

import es.albarregas.beans.Cliente;
import es.albarregas.beans.Usuario;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ActualizarUsuAdm", urlPatterns = {"/actualizarUsuAdm"})
public class ActualizarUsuAdm extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String url = "";
        DAOFactory df = DAOFactory.getDAOFactory(1);
        IUsuariosDAO iud = df.getUsuariosDAO();
        IClientesDAO icd = df.getClientesDAO();
        ArrayList<Usuario> listaUsuarios = iud.getUsuarios("");
        //Cargamos todos los usuarios con sus respectivos datos de clientes
        for (Usuario usuario : listaUsuarios) {
            ArrayList<Cliente> listaClientes = icd.getClientes("WHERE IdCliente = " + usuario.getIdUsuario());
            for(Cliente cliente : listaClientes){
                usuario.setCliente(cliente);
            }
        }
        
        request.setAttribute("listaUsuarios", listaUsuarios);
        
        url = "jsp/administrador/panelActCli.jsp";
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
