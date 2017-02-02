/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.servletsAjax;

import es.albarregas.beans.Factura;
import es.albarregas.beans.General;
import es.albarregas.beans.LineaPedido;
import es.albarregas.beans.Pedido;
import es.albarregas.beans.Producto;
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

@WebServlet(name = "FinalizarCarrito", urlPatterns = {"/finalizarCarrito"})
public class FinalizarCarrito extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        int idPedido = Integer.parseInt(request.getParameter("idPed"));
        DAOFactory df = DAOFactory.getDAOFactory(1);
        IPedidosDAO iped = df.getPedidosDAO();
        ILineasPedidosDAO ilpd = df.getLineasPedidosDAO();
        IProductosDAO iprd = df.getProductosDAO();
        IFacturasDAO ifd = df.getFacturasDAO();
        Factura factura = new Factura();
        HttpSession sesion = request.getSession(true);
        Pedido carrito = (Pedido) sesion.getAttribute("carrito");
        String excesoCantidad = "";
        /* Vamos a necesitar sacar de la BD los productos que tenemos en el carrito para comparar el stock
        disponible con las unidades que vamos a retirar, para que en caso de tener suficiente stock, posteriormente
        reducirle el stock a estos productos y poner la compra como */

        //Seleccionamos todos los productos que tenemos en el carrito
        String clausulaWhereProds = "WHERE IdProducto IN (";

        for (LineaPedido linPed : carrito.getLineasPedidos()) {
            clausulaWhereProds += linPed.getProducto().getIdProducto() + ",";
        }

        //Cogemos toda la cadena pero la coma del final
        clausulaWhereProds = clausulaWhereProds.substring(0, clausulaWhereProds.length() - 1);

        clausulaWhereProds += ")";
        System.out.println(clausulaWhereProds);
        ArrayList<Producto> listaProductos = iprd.getProductos(clausulaWhereProds);

        General general = (General) request.getServletContext().getAttribute("general");
        double iva = 0.0;
        double baseImponible = 0.0;
        double ivaTotal = 0.0;
        double totalLinea = 0.0;
        for (Producto producto : listaProductos) {
            for (LineaPedido linPedido : carrito.getLineasPedidos()) {
                if (linPedido.getProducto().getIdProducto() == producto.getIdProducto()) {
                    if (linPedido.getCantidad() > producto.getStock()) {
                        excesoCantidad += (producto.getDenominacion() + ", ");
                        //El break afecta solo al for de dentro, ya que se habría encontrado el producto                       
                    } else {
                        //Reducimos el stock en la lista que tenemos en memoria por si después hay que actualizar en la bd
                        producto.setStock(producto.getStock() - linPedido.getCantidad());
                    }
                    //Metemos el producto en el carrito
                    linPedido.setProducto(producto);

                    //Aprovechamos la construcción del where para ir cogiendo los precios del pedido
                    /* Para sacar el iva de un producto que ya tiene el iva incluido, hay que multiplicar el precio unitario
                    por el porcentaje de iva, y dividirlo por el porcentaje de iva más 100, que es el 100% del valor del producto sin iva
                    Ejemplo: Si el precio con iva incluido es 121 €, el precio es 100 € y el iva 21€, pues
                    121 * 21 / 121 = 21 €. El resultado que esperábamos
                    */
                
                    linPedido.setPrecioUnitario(producto.getPrecioUnitario());
                    totalLinea = linPedido.getPrecioUnitario() * linPedido.getCantidad();
                    iva = totalLinea * general.getIva() / (general.getIva() + 100);
                    baseImponible += (totalLinea - iva);
                    ivaTotal += iva;
                    break;
                }
            }
        }
        carrito.setBaseImponible(baseImponible);
        carrito.setGastosEnvio(general.getGastosEnvio());
        carrito.setIva(ivaTotal);
        
        
        //Actualizamos las lineas de pedidos con los preciosUnitarios
        for(LineaPedido pedido : carrito.getLineasPedidos()){
            ilpd.updLineasPedidos(pedido);
        }

        if (excesoCantidad.equals("")) {
            //Si hay las cantidades de cada producto tenemos que cambiar el estdo del pedido y reducir el stock de cada producto
            excesoCantidad = "ok";

            for (Producto producto : listaProductos) {
                int errorSql = iprd.updProductos(producto);
                System.out.println("Actualizacion stock prod " + producto.getIdProducto() + ", cod sql: " + errorSql);
            }

            carrito.setEstado("r"); //recibido

        } else {
            //Si no hay las cantidades, tenemos que cambiar el estado del pedido sin cambiar las cantidades del producto
            carrito.setEstado("p"); //pendiente
            excesoCantidad = excesoCantidad.substring(0, excesoCantidad.length() - 2);
        }

        //Como hemos hecho el pedido, lo tenemos que quitar del carrito
        sesion.removeAttribute("carrito");
        iped.updPedidos(carrito);
        factura.setPedido(carrito);
        ifd.addFacturas(factura);
        //Enviamos el idPedido para ver factura
        request.setAttribute("excesoCantidad", excesoCantidad);
        sesion.setAttribute("idPedidoAct",carrito.getIdPedido());
        request.getRequestDispatcher("/jsp/componentes/panelFinalCarrito.jsp").forward(request, response);

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
