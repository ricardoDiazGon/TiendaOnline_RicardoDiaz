/*
    En este servlet para ajax se van a modificar las propiedades de los productos
 */
package es.albarregas.servletsAjax;

import es.albarregas.beans.Producto;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ActualizarProductos", urlPatterns = {"/actualizarProductos"})
public class ActualizarProductos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        boolean actualizar = false;
        String valorAnt = null;
        try {

            String idProducto = request.getParameter("idPro");
            //Valor nuevo
            String valor = request.getParameter("val");
            //El campo que debemos modificar
            String campo = request.getParameter("cam"); 

            DAOFactory df = DAOFactory.getDAOFactory(1);
            IProductosDAO iprd = df.getProductosDAO();
            ArrayList<Producto> listaProductos = iprd.getProductos("WHERE IdProducto = " + idProducto);
            Producto producto = null;
            for (Producto productoAux : listaProductos) {
                producto = productoAux;
            }

            //Vemos que es lo que debemos modificar
            if (Double.parseDouble(valor) >= 0) {
                switch (campo) {
                    case "stock":
                        valorAnt = String.valueOf(producto.getStock());
                        producto.setStock(Integer.parseInt(valor));
                        break;
                    case "stockMinimo":
                        valorAnt = String.valueOf(producto.getStockMinimo());
                        producto.setStockMinimo(Integer.parseInt(valor));
                        break;
                    case "precio":
                        valorAnt = String.valueOf(producto.getPrecioUnitario());
                        producto.setPrecioUnitario(Double.parseDouble(valor));
                        break;
                    default: ;
                }

                iprd.updProductos(producto);
                actualizar = true;
            }

        } catch (NumberFormatException | NullPointerException ex) {
            ex.printStackTrace();
            //Si ocurre algún error lo debemos saber
            valorAnt = "error";
        }

        PrintWriter out = response.getWriter();
        if (actualizar) {
            out.print("ok");

            //Comprobamos si hay productos con menos stock que el mínimo
            DAOFactory df = DAOFactory.getDAOFactory(1);
            IProductosDAO iprd = df.getProductosDAO();
            ArrayList<Producto> listaProductos = iprd.getProductos("WHERE FueraCatalogo = 'n'");
            boolean alertaStock = false;
            HttpSession sesion = request.getSession(true);
            for (Producto producto : listaProductos) {
                if (producto.getStock() < producto.getStockMinimo()) {                 
                    sesion.setAttribute("alertaStock", "error");
                    alertaStock = true;
                }
            }

            //AlertaStock es una variable que si es true nos dice que el stock es inferior al stock mínimo
            if (alertaStock) {
                sesion.setAttribute("alertaStock", "error");
            }else{
                if(sesion.getAttribute("alertaStock") != null){
                    sesion.removeAttribute("alertaStock");
                }
                
            }

        } else {
            out.print(valorAnt);
        }
        out.flush();
        out.close();

        if (actualizar) {

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
