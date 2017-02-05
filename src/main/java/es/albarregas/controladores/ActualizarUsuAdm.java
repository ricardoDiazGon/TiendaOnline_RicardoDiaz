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
        
        int pagina = 1;
        if (request.getParameter("pag") != null) {
            pagina = Integer.parseInt(request.getParameter("pag")); //Número de página
        }

        int min = 8 * (pagina - 1); //8 clientes por página
        
        
        String url = "";
        DAOFactory df = DAOFactory.getDAOFactory(1);
        IUsuariosDAO iud = df.getUsuariosDAO();
        IClientesDAO icd = df.getClientesDAO();
        ArrayList<Usuario> listaTotalUsuarios = iud.getUsuarios("WHERE Tipo = 'u'");
        int total = listaTotalUsuarios.size();

        ArrayList<Usuario> listaUsuarios = iud.getUsuarios("WHERE Tipo = 'u' LIMIT " +min +",8");
        //Cargamos todos los usuarios con sus respectivos datos de clientes
        for (Usuario usuario : listaUsuarios) {
            ArrayList<Cliente> listaClientes = icd.getClientes("WHERE IdCliente = " + usuario.getIdUsuario());
            for(Cliente cliente : listaClientes){
                usuario.setCliente(cliente);
            }
        }

        int pag = (int) Math.ceil(total / Double.valueOf(8));
        
        request.setAttribute("listaUsuarios", listaUsuarios);
        
        request.setAttribute("pag", pag);
        request.setAttribute("actual", pagina);
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
