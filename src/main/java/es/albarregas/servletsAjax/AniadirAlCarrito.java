/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.servletsAjax;

import com.google.gson.Gson;
import es.albarregas.beans.LineaPedido;
import es.albarregas.beans.Pedido;
import es.albarregas.beans.Usuario;
import es.albarregas.dao.ILineasPedidosDAO;
import es.albarregas.dao.IPedidosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Ricardo
 */
@WebServlet(name = "AniadirAlCarrito", urlPatterns = {"/aniadirAlCarrito"})
public class AniadirAlCarrito extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        int cantidad = 1;
        int idProducto = Integer.parseInt(request.getParameter("pro"));

        if (request.getParameter("can") != null) {
            try {
                cantidad = Integer.parseInt(request.getParameter("can"));
            } catch (NumberFormatException ex) {
                cantidad = 0;
                //Ponemos 0 porque si no viene un numero como cantidad no va a hacer nada
            }

        }

        if (request.getSession().getAttribute("usuario") != null && cantidad > 0) {
            HttpSession sesion = request.getSession(true);
            Usuario usuario = (Usuario) sesion.getAttribute("usuario");
            DAOFactory df = DAOFactory.getDAOFactory(1);
            IPedidosDAO iped = df.getPedidosDAO();
            ILineasPedidosDAO ilpd = df.getLineasPedidosDAO();
            Pedido pedido = null;
            LineaPedido lineaPedido = new LineaPedido();
            ArrayList<LineaPedido> listaLineasPedidos = null;
            int errorSql = 0;
            
            //Si el atributo de sesion está a null es que tenemos que crear el pedido también
            if (sesion.getAttribute("carrito") == null) {
                //Si no habia nada en el carrito...
                pedido = new Pedido();
                pedido.setFecha(new Date());
                pedido.setEstado("n");
                pedido.setIdCliente(usuario.getCliente().getIdCliente());
                errorSql = iped.addPedidos(pedido);

                lineaPedido.setCantidad(cantidad);
                lineaPedido.setNumeroLinea(1);
                lineaPedido.setIdProducto(idProducto);

                ArrayList<Pedido> listaPedidos = iped.getPedidos("WHERE IdCliente = " + pedido.getIdCliente()
                        + " AND Estado = 'n'");
                //En teoria tiene que haber un solo pedido nuevo (en carrito) por cliente, por eso debe ser único...
                //Para saber el idpedido para lineaspedidos tenemos que acceder otra vez al pedido que hemos introducido
                for (Pedido pedidoAux : listaPedidos) {
                    lineaPedido.setIdPedido(pedidoAux.getIdPedido());
                    pedido.setIdPedido(pedidoAux.getIdPedido());
                }

                errorSql = ilpd.addLineasPedidos(lineaPedido);
                listaLineasPedidos = new ArrayList();
                listaLineasPedidos.add(lineaPedido);
                pedido.setLineasPedidos(listaLineasPedidos);
                sesion.setAttribute("carrito", pedido);
            } else {
                //Si ya había cosas en el carrito...
                boolean lineaEncontrada = false;
                pedido = (Pedido) sesion.getAttribute("carrito");
                int numeroLinea = pedido.getLineasPedidos().size() + 1;
                
                /* Si el producto que metemos en el carrito ya lo tenemos
                 habrá que actualizarlo con la cantidad total */
                for (LineaPedido lineaPedAux : pedido.getLineasPedidos()) {
                    if (lineaPedAux.getIdProducto() == idProducto) {
                        lineaEncontrada = true;
                        numeroLinea = lineaPedAux.getNumeroLinea();
                        cantidad += lineaPedAux.getCantidad();
                        break;
                    }
                }

                lineaPedido.setCantidad(cantidad);
                lineaPedido.setNumeroLinea(numeroLinea);
                lineaPedido.setIdPedido(pedido.getIdPedido());
                lineaPedido.setIdProducto(idProducto);
                
                //Si el producto que vamos a añadir existe, tenemos que incrementar la cantidad
                if (lineaEncontrada) {

                    errorSql = ilpd.updLineasPedidos(lineaPedido);

                    if (errorSql == 0) {
                        listaLineasPedidos = pedido.getLineasPedidos();
                        //Tenemos que actualizar los datos también en sesión, no podemos añadir
                        for(LineaPedido lineaPedAux: listaLineasPedidos){
                            if(lineaPedAux.getNumeroLinea() == numeroLinea){
                                lineaPedAux.setCantidad(cantidad);
                            }
                        }
                        
                        pedido.setLineasPedidos(listaLineasPedidos);

                        sesion.setAttribute("carrito", pedido);
                    }

                    //Y sino existe tenemos que añadir la linea
                } else {
                    //Nos quedamos con el numero de linea de la ultima linea de productos. Esto se hace para
                    //evitar problemas si eliminamos un producto, ya que siempre para el nuevo coge el numero anterior  +1
                    numeroLinea = pedido.getLineasPedidos().get(numeroLinea - 2).getNumeroLinea() +1 ;
                    lineaPedido.setNumeroLinea(numeroLinea);
                    errorSql = ilpd.addLineasPedidos(lineaPedido);

                    if (errorSql == 0) {
                        listaLineasPedidos = pedido.getLineasPedidos();
                        listaLineasPedidos.add(lineaPedido);
                        pedido.setLineasPedidos(listaLineasPedidos);

                        sesion.setAttribute("carrito", pedido);
                    }
                }

            }

            PrintWriter out = response.getWriter();
            Gson json = new Gson();
            out.print(json.toJson(pedido));
            out.flush();
            out.close();

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
