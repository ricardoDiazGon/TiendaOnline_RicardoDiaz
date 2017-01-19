package es.albarregas.controladores;

import es.albarregas.beans.Cliente;
import es.albarregas.beans.Usuario;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Usuario usuario = null;
        Cliente cliente = null;
        String url = "";
        String error = "";
        HttpSession sesion = request.getSession(true);
        //Solo hacemos cosas si se ha pulsado el botón registrar
        if (request.getParameter("registrar") != null) {
            sesion = request.getSession(true);
            String userName = request.getParameter("userName");
            String clave = request.getParameter("clave");
            String claveRep = request.getParameter("claveRep");
            int sqlError = 0;
            DAOFactory df = DAOFactory.getDAOFactory(1);
            IUsuariosDAO iud = df.getUsuariosDAO();

            //Validamos 
            if (userName.equals("")) {
                error = "El campo NOMBRE DE USUARIO no puede estar vacío. ";
            } else if (clave.equals("")) {
                error += "El campo CLAVE no puede estar vacía. ";
            } else if (claveRep.equals("")) {
                error += "El campo REPETIR CLAVE no puede estar vacío.";
            } else if (!clave.equals(claveRep)) {
                error += " Tu contraseña no coincide con el campo de confirmación.";
            }

            //Si no hay errores
            if (error.equals("")) {
                usuario = new Usuario();
                usuario.setUserName(userName);
                usuario.setClave(clave);
                usuario.setTipo("u");
                usuario.setBloqueado("n");
                usuario.setUltimoAcceso(new Date());
                System.out.println(usuario.getUltimoAcceso());
                sqlError = iud.addUsuarios(usuario);
                if (sqlError == 1062) {
                    // 1022 es para las pk duplicadas
                    error = "El nombre de usuario ya existe";
                } else if (sqlError != 0) {
                    error = "Ha ocurrido un error inesperado, vuelva a intentarlo más tarde";
                }
            }

            //Tenemos en cuenta si se ha producido algún error
            if (!error.equals("")) {
                request.setAttribute("registro", "ok");
                request.setAttribute("error", error);
            } else {
                request.setAttribute("login", "ok");
            }

            request.setAttribute("userName", userName);
            request.setAttribute("clave", clave);
            request.setAttribute("claveRep", claveRep);
            
        //Si hemos pulsado añadir los datos del cliente del panel de control del usuario    
        } else if (request.getParameter("addCliente") != null) {
            
            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String nif = request.getParameter("NIF");
            String email = request.getParameter("email");
            String fechaNacimiento = request.getParameter("fechaNacimiento");

            //Validación de los campos
            if (nombre.trim().equals("")) {
                error = "El campo NOMBRE no puede estar vacío";
            } else if (apellidos.trim().equals("")) {
                error = "El campo APELLIDOS no puede estar vacío";
            } else if (nif.trim().equals("")) {
                error = "El campo NIF no puede estar vacío";
            } else if (email.trim().equals("")) {
                error = "El campo EMAIL no puede estar vacío";
            } else if (fechaNacimiento.trim().equals("")) {
                error = "El campo FECHA DE NACIMIENTO no puede estar vacío";
            }

            System.out.println("Fecha Nacimiento" +fechaNacimiento);
            if (error.equals("")) {
                try {
                    usuario = (Usuario) sesion.getAttribute("usuario");
                    cliente = new Cliente();
                    cliente.setIdCliente(usuario.getIdUsuario());
                    cliente.setNombre(nombre);
                    cliente.setApellidos(apellidos);
                    cliente.setNIF(nif);
                    cliente.setEmail(email);
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
                    Date fechaNac = null;
                    fechaNac = formatoFecha.parse(fechaNacimiento);
                    cliente.setFechaNacimiento(fechaNac);
                    cliente.setFechaAlta(new Date());

                    DAOFactory df = DAOFactory.getDAOFactory(1);
                    IClientesDAO icd = df.getClientesDAO();
                    int errorSQL = icd.addClientes(cliente);
                    if (errorSQL != 0) {
                        error = "No ha sido posible dar de alta los datos";
                    }else{
                        usuario.setCliente(cliente);
                        sesion.setAttribute("usuario", usuario);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(Registro.class.getName()).log(Level.SEVERE, null, ex);
                    error = "La FECHA DE NACIMIENTO es incorrecta";
                }
            }

            //Cargamos datos y controlamos el flujo
            if (error.equals("")) {
                usuario.setCliente(cliente);
                sesion.setAttribute("usuario", usuario);
                error = "ok";
            } else {
                request.setAttribute("nombre", nombre);
                request.setAttribute("apellidos", apellidos);
                request.setAttribute("nif", nif);
                request.setAttribute("email", email);
                request.setAttribute("fechaNacimiento", fechaNacimiento);

            }
            request.setAttribute("errorCliente", error);
            url = "/jsp/cliente/panelCli.jsp";

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
