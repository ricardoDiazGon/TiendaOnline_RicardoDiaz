package es.albarregas.controladores;

import es.albarregas.beans.Usuario;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Registro", urlPatterns = {"/registro"})
public class Registro extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = "";

        if (request.getParameter("registrar") != null) {
            HttpSession sesion = request.getSession(true);
            String userName = request.getParameter("userName");
            String clave = request.getParameter("clave");
            String claveRep = request.getParameter("claveRep");
            String error = "";
            int sqlError = 0;
            DAOFactory df = DAOFactory.getDAOFactory(1);
            IUsuariosDAO iud = df.getUsuariosDAO();
            Usuario usuario = null;
            //Validamos 

            if (userName.equals("")) {
                error = "Campo Nombre de Usuario no puede estar vacío. ";
            } else if (clave.equals("")) {
                error += " Clave no puede estar vacía. ";
            } else if (claveRep.equals("")) {
                error += " Repetir clave no puede estar vacío.";
            } else if (!clave.equals(claveRep)) {
                error += " Los campos clave y repetir clave deben ser iguales.";
            }

            if (error.equals("")) {
                usuario = new Usuario();
                usuario.setUserName(userName);
                usuario.setClave(clave);
                usuario.setTipo('u');
                usuario.setBloqueado('n');
                usuario.setUltimoAcceso(new Date());
                System.out.println(usuario.getUltimoAcceso());
                sqlError = iud.addUsuarios(usuario);
                if (sqlError == 0) {
                    url = "/index.jsp";
                } else if (sqlError == 1062) { 
                    // 1022 es para las pk duplicadas
                    error = "El nombre de usuario ya existe";
                    url = "/index.jsp";
                } else {
                    error = "Ha ocurrido un error inesperado, vuelva a intentarlo más tarde";
                    url = "/index.jsp";
                }
            }

            //Tenemos en cuenta si se ha producido algún error
            if (!error.equals("")) {
                request.setAttribute("registro", "ok");
                request.setAttribute("error", error);
                request.setAttribute("userName", userName);
            } else {
                request.setAttribute("login", "ok");
                sesion.setAttribute("usuario", usuario);
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
