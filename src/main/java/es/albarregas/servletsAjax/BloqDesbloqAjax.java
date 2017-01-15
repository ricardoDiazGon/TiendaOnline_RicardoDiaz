/*
    En este servlet realizamos con Ajax el bloqueo y desbloqueo de los usuarios y productos
 */
package es.albarregas.servletsAjax;

import es.albarregas.beans.Usuario;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
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
        
        if(entidad.equals("usuario")){
            DAOFactory df = DAOFactory.getDAOFactory(1);
            IUsuariosDAO iud = df.getUsuariosDAO();
            String estado = "n"; //variable con la que vemos si bloqueamos o desbloqueamos
            Usuario usuario = iud.getUsuarios("WHERE IdUsuario = " +Integer.parseInt(id)).get(0);
            
            if(accion.equals("bloquear")){
                estado = "s";
            }
            usuario.setIdUsuario(Integer.parseInt(id));
            usuario.setBloqueado(estado);
            iud.updUsuarios(usuario);
        }else{
            //para los productos
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
