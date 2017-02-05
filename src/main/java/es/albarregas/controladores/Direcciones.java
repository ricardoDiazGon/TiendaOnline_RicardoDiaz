/* Controlador donde insertamos las direcciones principalmente */

package es.albarregas.controladores;

import es.albarregas.beans.Direccion;
import es.albarregas.beans.Usuario;
import es.albarregas.dao.IDireccionesDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Direcciones", urlPatterns = {"/direcciones"})
public class Direcciones extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = "/index.jsp";
        HttpSession sesion = request.getSession(true);

        //Si existe el usuario en la sesión seguimos, sino para el index
        if (sesion.getAttribute("usuario") != null) {
            //Si el usuario ha pulsado este botón, sino para el index
            if (request.getParameter("guardarDir") != null) {
                String mensajeError = "";
                String nombreDir = request.getParameter("NombreDireccion");
                String direccion = request.getParameter("Direccion");
                String codigoPostal = request.getParameter("CodigoPostal");
                String provincia = request.getParameter("IdProvincia");
                String pueblo = request.getParameter("IdPueblo");
                String telefono = request.getParameter("Telefono");
                String nombrePro = request.getParameter("Provincia");
                String nombrePue = request.getParameter("Poblacion");

                //Obtenemos el id de cliente
                Usuario usuario = (Usuario) sesion.getAttribute("usuario");
                int idCliente = usuario.getCliente().getIdCliente();

                //Validamos básica de direcciones por contenido vacío y máximo de caracteres.
                //La validación más exhaustiva se realiza en cliente
                if (nombreDir.equals("")) {
                    mensajeError = "Rellene el campo NOMBRE DE DIRECCION";
                } else if (direccion.equals("")) {
                    mensajeError = "Rellene el campo DIRECCIÓN. ";
                } else if (codigoPostal.equals("")) {
                    mensajeError = "Rellene el campo CÓDIGO POSTAL . ";
                } else if (provincia.equals("")) {
                    mensajeError = "Introduzca un CÓDIGO POSTAL correcto . ";
                } else if (pueblo.equals("")) {
                    mensajeError = "Introduzca un CÓDIGO POSTAL correcto . ";
                } else if (telefono.equals("")) {
                    mensajeError = "Rellene el campo TELÉFONO. ";
                }else if (nombreDir.length() > 20 ) {
                    mensajeError = "El campo NOMBRE DE DIRECCION no puede tener más de 20 caracteres";
                } else if (direccion.length() > 50) {
                    mensajeError = "El campo DIRECCION no puede tener más de 50 caracteres";
                } else if (codigoPostal.length() > 5) {
                    mensajeError = "El campo CÓDIGO POSTAL no puede tener más de 5 caracteres";
                }
               

                Direccion dir = new Direccion();

                dir.setCodigoPostal(codigoPostal);
                dir.setNombreDireccion(nombreDir);
                dir.setTelefono(telefono);
                dir.setDireccion(direccion);
                dir.setIdCliente(idCliente);

                //Para meter los enteros tenemos que comprobar que no vienen vacíos
                if (!pueblo.equals("")) {
                    dir.setIdPueblo(Integer.parseInt(pueblo));
                    dir.setNombrePueblo(nombrePue);
                }
                if (!provincia.equals("")) {
                    dir.setIdProvincia(Integer.parseInt(provincia));
                    dir.setNombreProvincia(nombrePro);
                }

                //Si no ha habido errores, añadimos la dirección
                if (mensajeError.equals("")) {
                    DAOFactory df = DAOFactory.getDAOFactory(1);
                    IDireccionesDAO idd = df.getDireccionesDAO();
                    int errorSQL = idd.addDirecciones(dir);
                    if (errorSQL == 0) {
                        //Si todo ha salido bien, para pintar que se ha añadido correctamente
                        mensajeError = "ok";
                        //Actualizamos las direcciones del usuarios en sesion
                        usuario.getCliente().setListaDirecciones(idd.getDirecciones("WHERE IdCliente = " + usuario.getIdUsuario()));
                        sesion.setAttribute("usuario", usuario);
                    } else {
                        mensajeError = "No se ha podido guardar la dirección " + nombreDir.toUpperCase();
                    }
                }

                request.setAttribute("errorDirec", mensajeError);

                //Si ha habido errores mandamos el bean al formulario para mostrar los campos introducidos
                if (!mensajeError.equals("") && !mensajeError.equals("ok")) {
                    request.setAttribute("direc", dir);
                }
                if (request.getParameter("formulario") != null && request.getParameter("formulario").equals("carrito")) {
                    url = "/jsp/cliente/carrito.jsp";
                    request.setAttribute("datosDir", "ok");
                } else {
                    url = "/jsp/cliente/panelCli.jsp";
                }

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
