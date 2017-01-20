/*
    Controlador donde actualizaremos tanto los datos de los Usuarios como de los Clientes
 */
package es.albarregas.controladores;

import es.albarregas.beans.Usuario;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ActualizarUsuCli", urlPatterns = {"/actualizarUsuCli"})
public class ActualizarUsuCli extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = "";

        //Cambiamos la contraseña del Usuario
        if (request.getParameter("updClave") != null) {

            String claveAnt = request.getParameter("claveAnt");
            String claveNue = request.getParameter("claveNue");
            String claveNueRep = request.getParameter("claveNueRep");
            String error = "";

            if (claveAnt.equals("")) {
                error = "Contraseña Antigua es un campo obligatorio";
            } else if (claveNue.equals("")) {
                error = "Contraseña Nueva es un campo obligatorio";
            } else if (claveNueRep.equals("")) {
                error = "Repetir Contraseña es un campo obligatorio";
            } else if (!claveNue.equals(claveNueRep)) {
                error = "Los campos Contraseña Nueva y Repetir Contraseña deben de ser iguales";
            } else if (claveNue.equals(claveAnt)) {
                error = "La Contraseña Nueva no puede ser igual que la Contraseña Antigua";
            }
            try {
                HttpSession sesion = request.getSession(true);
                Usuario usuario = (Usuario) sesion.getAttribute("usuario");
                if (error.equals("")) {
              
                    DAOFactory df = DAOFactory.getDAOFactory(1);
                    IUsuariosDAO iud = df.getUsuariosDAO();
                    usuario.setClave(claveNue);

                    int errorSQL = iud.updUsuarios(usuario);
                    if (errorSQL != 0) {
                        error = "No ha podido actualizarse la contraseña";
                    }

                }

                if (!error.equals("")) {
                    request.setAttribute("errorClave", error);
                } else {
                    sesion.setAttribute("usuario", usuario);
                    request.setAttribute("errorClave", "ok");
                }

                //Como la contraseña la puede cambiar también el administrador redirigimos según quien la haya hecho
                if (usuario.getTipo().equals("u")) {
                    url = "/jsp/cliente/panel.jsp";
                } else {
                    url = "/jsp/administrador/panel.jsp";
                }
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }
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
