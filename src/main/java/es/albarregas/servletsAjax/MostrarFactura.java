/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.servletsAjax;

import es.albarregas.beans.Cliente;
import es.albarregas.beans.Direccion;
import es.albarregas.beans.Factura;
import es.albarregas.beans.LineaPedido;
import es.albarregas.beans.Pedido;
import es.albarregas.beans.Producto;
import es.albarregas.dao.IClientesDAO;
import es.albarregas.dao.IDireccionesDAO;
import es.albarregas.dao.IFacturasDAO;
import es.albarregas.dao.ILineasPedidosDAO;
import es.albarregas.dao.IPedidosDAO;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "MostrarFactura", urlPatterns = {"/mostrarFactura"})
public class MostrarFactura extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String idPedido = request.getParameter("idPed");
        DAOFactory df = DAOFactory.getDAOFactory(1);
        IPedidosDAO iped = df.getPedidosDAO();
        ILineasPedidosDAO ilped = df.getLineasPedidosDAO();
        IClientesDAO icd = df.getClientesDAO();
        IDireccionesDAO idd = df.getDireccionesDAO();
        IProductosDAO iprd = df.getProductosDAO();
        ArrayList<Pedido> listaPedidos = iped.getPedidos("WHERE IdPedido = " + idPedido);
        ArrayList<LineaPedido> listaLineasPedidos = ilped.getLineasPedidos("WHERE IdPedido = " + idPedido);
        Pedido pedido = new Pedido();
        HttpSession sesion = request.getSession(true);
        System.out.println("Mostrar factura: " +idPedido);
        //Es un solo pedido, así que lo cogemos
        for (Pedido pedidoAux : listaPedidos) {
            pedido = pedidoAux;
        }
        //Metemos las lineas de pedido en el pedido
        pedido.setLineasPedidos(listaLineasPedidos);

        //Metemos la direccion y el cliente en le pedido
        ArrayList<Direccion> listaDirecciones = idd.getDirecciones("WHERE IdDireccion = " +pedido.getDireccion().getIdDireccion());
        ArrayList<Cliente> listaClientes = icd.getClientes("WHERE IdCliente = " +pedido.getCliente().getIdCliente());

        for(Direccion direccionAux : listaDirecciones){
            pedido.setDireccion(direccionAux);
        }
        
        for(Cliente clienteAux : listaClientes){
            pedido.setCliente(clienteAux);
        }        
        

        //Rescatamos los productos de nuestros pedidos para meterlos en cada bean de lineapedido
        String clausulaWhereProds = "WHERE IdProducto IN (";

        for (LineaPedido linPed : pedido.getLineasPedidos()) {
            clausulaWhereProds += linPed.getProducto().getIdProducto() + ",";

        }

        clausulaWhereProds = clausulaWhereProds.substring(0, clausulaWhereProds.length()-1);
        clausulaWhereProds += ")";
        ArrayList<Producto> productosPedido = iprd.getProductos(clausulaWhereProds);
        for (LineaPedido linPed : pedido.getLineasPedidos()) {
            for (Producto producto : productosPedido) {
                if (producto.getIdProducto() == linPed.getProducto().getIdProducto()) {
                    linPed.setProducto(producto);
                    break;
                }

            }

        }

        //Hemos estado trabajando con el pedido, ahora lo tenemos que meter en la factura
        IFacturasDAO ifd = df.getFacturasDAO();
        ArrayList<Factura> listaFacturas = ifd.getFacturas("WHERE IdPedido = " + idPedido);
        Factura factura = null;
        for (Factura facturaAux : listaFacturas) {
            factura = facturaAux;
        }

        factura.setPedido(pedido);
     
        sesion.setAttribute("factura", factura);
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
