package es.albarregas.controladores;

import es.albarregas.beans.Cliente;
import es.albarregas.beans.Direccion;
import es.albarregas.beans.LineaPedido;
import es.albarregas.beans.Pedido;
import es.albarregas.beans.Usuario;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IDireccionesDAO;
import es.albarregas.dao.ILineasPedidosDAO;
import es.albarregas.dao.IPedidosDAO;
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
        String url = "/index.jsp";
        StringBuffer error = new StringBuffer();
        HttpSession sesion = request.getSession(true);
        //Si viene de hacer login
        if (request.getParameter("login") != null) {

            String email = request.getParameter("email");
            String clave = request.getParameter("clave");
            DAOFactory df = DAOFactory.getDAOFactory(1);
            IUsuariosDAO iud = df.getUsuariosDAO();
            IPedidosDAO iped = df.getPedidosDAO();
            ILineasPedidosDAO ilpd = df.getLineasPedidosDAO();

            Usuario usuario = null;

            if (email.equals("")) {
                error.append(" El email (UserName) no puede estar vacío. ");
            }
            if (clave.equals("")) {
                error.append(" La contraseña no puede estar vacía. ");
            }

            //Si no hay errores seguimos...
            if (error.length() == 0) {
                ArrayList<Usuario> listaUsuarios = iud.getUsuarios(" WHERE Email = '" + email + "' AND Clave = '" + clave + "'");
                if (listaUsuarios.isEmpty()) {
                    error.append("El email (UserName) o la clave no son correctos.");
                } else {
                    usuario = listaUsuarios.get(0);
                    //Recuperamos el cliente que le corresponde al usuario
                    IClientesDAO icd = df.getClientesDAO();
                    ArrayList<Cliente> listaClientes = icd.getClientes("WHERE IdCliente = " + usuario.getIdUsuario());
                    for (Cliente cliente : listaClientes) {
                        usuario.setCliente(cliente);
                    }

                    //Recuperamos las direcciones
                    if (usuario.getCliente() != null) {
                        IDireccionesDAO idd = df.getDireccionesDAO();
                        ArrayList<Direccion> listaDirecciones = idd.getDirecciones("WHERE IdCliente = " + usuario.getIdUsuario());
                        usuario.getCliente().setListaDirecciones(listaDirecciones);
                    }

                }
            }

            //Si el usuario ha sido encontrado
            if (usuario != null) {
                //Esta parte puede ir más abajo
                if (usuario.getBloqueado().equals("n")) {
                    //Solo enviamos el usuario a la sesión si sabemos seguro que existe y se ha registrado bien
                    usuario.setUltimoAcceso(new Date());
                    int errorSQL = iud.updUsuarios(usuario);

                    sesion.setAttribute("usuario", usuario);
                    //El carrito solo es para usuario no admin
                    if (usuario.getTipo().equals("u")) {
                        ArrayList<Pedido> listaPedidos = iped.getPedidos("WHERE IdCliente = " + usuario.getCliente().getIdCliente()
                                + " AND Estado = 'n'");

                        ArrayList<LineaPedido> listaLineasPedidos = null;
                        for (Pedido pedido : listaPedidos) {
                            listaLineasPedidos = ilpd.getLineasPedidos("WHERE IdPedido = " + pedido.getIdPedido());
                            pedido.setLineasPedidos(listaLineasPedidos);
                            sesion.setAttribute("carrito", pedido);
                        }
                    }
                } else {
                    request.setAttribute("login", "El usuario \"" + usuario.getEmail() + "\" ha sido bloqueado");
                }
            } else {
                request.setAttribute("email", email);
                request.setAttribute("clave", clave);
                request.setAttribute("login", error.toString());
            }
        } else if (request.getParameter("cerrar") != null && request.getParameter("cerrar").equals("ok")) {
            //Si damos a invalidar sesión...
            if (sesion != null) {
                sesion.invalidate();
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
