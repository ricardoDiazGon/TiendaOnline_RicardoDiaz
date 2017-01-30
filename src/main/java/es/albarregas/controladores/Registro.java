package es.albarregas.controladores;

import es.albarregas.beans.Cliente;
import es.albarregas.beans.Usuario;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
        /*
            Solo hacemos cosas si se ha pulsado el botón registrar
            Este es el registro inicial, de los datos de usuario
         */
        if (request.getParameter("registrar") != null) {
            sesion = request.getSession(true);
            String email = request.getParameter("email");
            String clave = request.getParameter("clave");
            String claveRep = request.getParameter("claveRep");
            int sqlError = 0;
            DAOFactory df = DAOFactory.getDAOFactory(1);
            IUsuariosDAO iud = df.getUsuariosDAO();
            IClientesDAO icd = df.getClientesDAO();
            //Validamos 
            if (email.equals("")) {
                error = "El campo Email (UserName) no puede estar vacío. ";
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
                usuario.setEmail(email);
                usuario.setClave(clave);
                usuario.setTipo("u");
                usuario.setBloqueado("n");
                usuario.setUltimoAcceso(new Date());
                sqlError = iud.addUsuarios(usuario);
                if (sqlError == 1062) {
                    // 1022 es para las pk duplicadas
                    error = "El Email (UserName) ya ha sido registrado";
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
                cliente = new Cliente();
                
                //Necesitamos recuperar el usuario de la base de datos para coger el IdCliente, ya que es foreign key de usuarios
                ArrayList<Usuario> listaUsuarios = iud.getUsuarios("WHERE Email = '" + usuario.getEmail() + "'");
                for (Usuario usuarioObtenido : listaUsuarios) {
                    cliente.setIdCliente(usuarioObtenido.getIdUsuario());
                }
                //Rellenamos los campos que no vamos a registrar al principio con null
                cliente.setNombre("null");
                cliente.setApellidos("null");
                cliente.setNIF("null");
                cliente.setFechaAlta(new Date());
                icd.addClientes(cliente);
            }

            request.setAttribute("email", email);
            request.setAttribute("clave", clave);
            request.setAttribute("claveRep", claveRep);

            //Si hemos pulsado añadir los datos del cliente del panel de control del usuario    
        } else if (request.getParameter("addCliente") != null) {

            String nombre = request.getParameter("nombre");
            String apellidos = request.getParameter("apellidos");
            String nif = request.getParameter("NIF");
            String fechaNacimiento = request.getParameter("fechaNacimiento");

            //Validación de los campos
            if (nombre.trim().equals("")) {
                error = "El campo NOMBRE no puede estar vacío";
            } else if (apellidos.trim().equals("")) {
                error = "El campo APELLIDOS no puede estar vacío";
            } else if (nif.trim().equals("")) {
                error = "El campo NIF no puede estar vacío";
            } else if (fechaNacimiento.trim().equals("")) {
                error = "El campo FECHA DE NACIMIENTO no puede estar vacío";
            }

            if (error.equals("")) {
                try {
                    usuario = (Usuario) sesion.getAttribute("usuario");
                    cliente = new Cliente();
                    cliente.setIdCliente(usuario.getIdUsuario());
                    cliente.setNombre(nombre);
                    cliente.setApellidos(apellidos);
                    cliente.setNIF(nif);
                    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/mm/yyyy");
                    Date fechaNac = null;
                    fechaNac = formatoFecha.parse(fechaNacimiento);
                    cliente.setFechaNacimiento(fechaNac);

                    DAOFactory df = DAOFactory.getDAOFactory(1);
                    IClientesDAO icd = df.getClientesDAO();
                    int errorSQL = icd.updClientes(cliente);
                    if (errorSQL != 0) {
                        error = "No ha sido posible dar de alta los datos";
                    } else {
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
                request.setAttribute("fechaNacimiento", fechaNacimiento);
            }
            request.setAttribute("errorCliente", error);
            
            if(request.getParameter("formulario") != null && request.getParameter("formulario").equals("carrito")){
                request.setAttribute("datosPer", "ok");
                url = "/jsp/cliente/carrito.jsp";
            }else{
                url = "/jsp/cliente/panelCli.jsp";
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
