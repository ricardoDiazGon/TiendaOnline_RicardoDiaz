/*
    Controlador para actualizar precios de productos.
    No es muy eficiente hacerlo de esta forma, porque va actualizando uno por uno.
    Pero el método actualizar del DAO producto estaba diseñado así, y para modificarlo habría
    que cambiar todo el código, y como se ha avisado a gente que no se pueden tener varios métodos
    para hacer lo mismo (actualizar o leer), pues lo hago así, porque no deja de ser una actividad
    del fp...
 */
package es.albarregas.controladores;

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

/**
 *
 * @author Ricardo
 */
@WebServlet(name = "ActualizarPrecios", urlPatterns = {"/actualizarPrecios"})
public class ActualizarPrecios extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String forma = request.getParameter("forma");
        String marca = request.getParameter("marca");;
        String categoria = request.getParameter("categoria");;
        String oferta = request.getParameter("oferta");
        String porcentaje = request.getParameter("porcentaje");
        String signo = request.getParameter("signo");
        String clausulaWhere = "WHERE ";
        boolean centinelaAnd = false;
        String error = "";
        String url = "jsp/administrador/panelActPrecios.jsp";
        int errorSQL = 0;
        double precioUnitario = 0.0;
        DAOFactory df = DAOFactory.getDAOFactory(1);
        IProductosDAO iprd = df.getProductosDAO();

        if (!porcentaje.equals("porcentaje")) {
            if (forma.equals("marca")) {

                if (!marca.equals("todas")) {
                    clausulaWhere += "P.IdMarca = " + marca;
                    centinelaAnd = true;
                }

            } else if (!categoria.equals("todas")) {
                clausulaWhere += "P.IdCategoria = " + categoria;
                centinelaAnd = true;
            }

            if(!oferta.equals("todos")){
                if(centinelaAnd){
                    clausulaWhere += " AND ";
                }
                clausulaWhere += "P.Oferta = '" + oferta +"'";
                centinelaAnd = true;
            }
            
            if(!centinelaAnd){
                clausulaWhere = "";
            }

            ArrayList<Producto> listaProductos = iprd.getProductos(clausulaWhere);

            for (Producto productoAux : listaProductos) {
                //Calculamos el precio
                precioUnitario = productoAux.getPrecioUnitario();
                if (signo.equals("disminuir")) {
                    precioUnitario -= precioUnitario * Integer.parseInt(porcentaje) / 100;
                } else {
                    precioUnitario += precioUnitario * Integer.parseInt(porcentaje) / 100;
                }
                productoAux.setPrecioUnitario(precioUnitario);
                errorSQL = iprd.updProductos(productoAux);
                if (errorSQL != 0) {
                    error += productoAux.getIdProducto() + ", ";
                }
            }

            if(!error.equals("")){
                error += "Los productos con id = " + error + "no se han actualizado correctamente";
            }
            
        } else {
            error = "Por favor, seleccione un porcentaje";
        }

        if (!error.equals("")) {
            request.setAttribute("error", error);
        }else{
            request.setAttribute("error", "ok");
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
