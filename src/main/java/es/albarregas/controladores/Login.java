
package es.albarregas.controladores;

import es.albarregas.beans.Cliente;
import es.albarregas.beans.Usuario;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = "";
        StringBuffer error = new StringBuffer();

        //Si viene de hacer login
        if (request.getParameter("login") != null) {

            String userName = request.getParameter("userName");
            String clave = request.getParameter("clave");
            HttpSession sesion = request.getSession(true);
            DAOFactory df = DAOFactory.getDAOFactory(1);
            IUsuariosDAO iud = df.getUsuariosDAO();
            IClientesDAO icd = df.getClientesDAO();
            Usuario usuario = null;

            if (userName.equals("")) {
                error.append(" El nombre de usuario no puede estar vacío. ");
            } if (clave.equals("")) {
                error.append(" La contraseña no puede estar vacía. ");
            }

            //Si no hay errores seguimos...
            if (error.length() == 0) {
                ArrayList<Usuario> listaUsuarios = iud.getUsuarios(" WHERE UserName = '" + userName + "' AND Clave = '" + clave +"'"); 
                if (listaUsuarios.isEmpty()) {
                    error.append("El nombre de usuario o la clave no son correctos.");
                    usuario = null;
                } else {
                    usuario = listaUsuarios.get(0);
                }

            }

            //Si el usuario ha sido encontrado
            if (usuario != null) {
                //Esta parte puede ir más abajo
                if (usuario.getBloqueado().equals("n")) {
                    sesion.setAttribute("usuario", usuario);

                    //Solo enviamos el usuario a la sesión si sabemos seguro que existe y se ha registrado bien
                    usuario.setUltimoAcceso(new Date());
                    int errorSQL = iud.updUsuarios(usuario);
                    System.out.println("Codigo sql update: " + errorSQL);
                    sesion.setAttribute("usuario", usuario);
                    //Rescatamos el cliente que corresponde al usuario
                    ArrayList<Cliente> listaClientes = icd.getClientes("WHERE IdCliente = '" +usuario.getIdUsuario() +"'");
                    
                    if(listaClientes.size() == 1){
                        sesion.setAttribute("cliente", listaClientes.get(0));
                    }
                    
                } else {
                    request.setAttribute("login", "El usuario \"" + usuario.getUserName() + "\" ha sido bloqueado");
                }
            }else {
                request.setAttribute("userName", userName);
                request.setAttribute("clave", clave);
                request.setAttribute("login", error.toString());
            }
        }else if(request.getParameter("cerrar") != null && request.getParameter("cerrar").equals("ok")){
            //Si damos a invalidar sesión...
            if(request.getSession() != null){
                request.getSession().invalidate();
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
