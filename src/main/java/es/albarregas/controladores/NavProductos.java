/*
    Controlador de la navegación entre productos. También para cuando seleccionamos alguno para ampliarlo   
    Este controlador es muy importante, ya que gestiona la paginación, ordenación y demás de cada sección
 */
package es.albarregas.controladores;

import es.albarregas.beans.Categoria;
import es.albarregas.beans.Producto;
import es.albarregas.dao.ICaracteristicasDAO;
import es.albarregas.dao.IGeneralDAO;
import es.albarregas.dao.IIMagenesDAO;
import es.albarregas.dao.IProductosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
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
@WebServlet(name = "NavProductos", urlPatterns = {"/navProductos"})
public class NavProductos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        String url = "";
        String opcion = "ind";
        String orden = "1";
        String param = "1";
        String prodPorPagina = "8";
        int pagina = 1;

        try {

            //Hacemos las comprobaciones sobre los datos que nos deben o nos pueden llegar
            if (request.getParameter("opt") != null) {
                opcion = request.getParameter("opt"); //Opción si ampliamos o mostramos categoria
            }

            if (request.getParameter("param") != null) {
                param = request.getParameter("param"); //Valos que pasamos, con el idProducto o idCategoria
            }

            if (request.getParameter("pag") != null) {
                pagina = Integer.parseInt(request.getParameter("pag")); //Número de página
            }
            if (request.getParameter("ord") != null) {
                orden = request.getParameter("ord");
            }
            if (request.getParameter("pxp") != null) {
                prodPorPagina = request.getParameter("pxp");
            }

            switch (opcion) {
                //en amp Param sería el id de producto. Se redirige a la ventana del producto
                case "amp":
                    url = "/jsp/comun/paginaProducto.jsp";
                    request.setAttribute("idProducto", Integer.parseInt(param));
                    break;

                //Si hemos seleccionado una categoría en el menú de navegación
                case "tod":
                case "ofe":
                case "ind":
                case "cat":
                    if (opcion.equals("cat")) {
                        url = "/jsp/comun/paginaCategoria.jsp";
                        request.setAttribute("idCategoria", Integer.parseInt(param));
                    } else if (opcion.equals("tod")) {
                        url = "/jsp/comun/paginaTodos.jsp";
                    } else if (opcion.equals("ofe")) {
                        url = "/jsp/comun/paginaOfertas.jsp";
                    }
                    request.setAttribute("actual", pagina);
                    //Rescatamos el array de productos del contexto de la aplicación, para poder manejarlo
                    
                    //Actualizamos la información del contexto
                    DAOFactory df = DAOFactory.getDAOFactory(1);
                    IIMagenesDAO iid = df.getImagenesDAO();
                    IProductosDAO ipd = df.getProductosDAO();
                    ICaracteristicasDAO icard = df.getCaracteristicasDAO();
                    IGeneralDAO igd = df.getGeneralDAO();

                    ArrayList<Producto> listaProductos = ipd.getProductos("WHERE FueraCatalogo = 'n'");

                    //Introducimos las imagenes y las caracteristicas en los productos
                    
                    for (Producto producto : listaProductos) {
                        producto.setImagenes(iid.getImagenes("WHERE IdProducto = " + producto.getIdProducto()));
                        producto.setCaracteristicas(icard.getCaracteristicas("WHERE IdProducto = " + producto.getIdProducto()));
                    }
                    request.getServletContext().setAttribute("productos", listaProductos);
                    ArrayList<Producto> productos = (ArrayList<Producto>) request.getServletContext().getAttribute("productos");
                    //Cargamos los productos del slider
                    ArrayList<Producto> listaProductosSlider = new ArrayList();
                    int cont = 0;
                    for (int i = 0; i < listaProductos.size() && cont < 4; i++) {
                        if (listaProductos.get(i).getOferta().equals("s")) {
                            listaProductosSlider.add(listaProductos.get(i));
                            cont++;
                        }
                    }
                    request.getServletContext().setAttribute("proSlider", listaProductosSlider);
                    //Finaliza la actualización de la información del contexto

                    //Según el orden que nos venga en el atributo, ordenamos de una forma u otra
                    ArrayList<Producto> productosCat = new ArrayList();

                    switch (orden) {
                        case "2":
                            productos = this.ordenarPrecioAsc(productos);
                            break;
                        case "3":
                            productos = this.ordenarPrecioDesc(productos);
                            break;
                        case "4":
                            productos = this.ordenarPopularidad(productos);
                            break;
                        default:
                            productos = this.ordenarNombre(productos);
                    }

                    //Cosas propias de la paginación
                    int max = Integer.parseInt(prodPorPagina) * pagina;
                    int min = Integer.parseInt(prodPorPagina) * (pagina - 1);
                    double total = 0; //total de productos de la categoria que vamos a recorrer
                    if (!opcion.equals("tod")) {
                        for (int i = 0; i < productos.size(); i++) {
                            if (productos.get(i).getIdCategoria() == Integer.parseInt(param) && total >= min && total < max) {
                                productosCat.add(productos.get(i));
                            }
                            //Aprovechamos el bucle para obtener el número maximo de productos de la categoria
                            //Así lo podremos emplear para hacer la paginacion
                            if (productos.get(i).getIdCategoria() == Integer.parseInt(param)) {
                                total++;
                            }
                        }
                    } else {
                        total = productos.size();
                        for (int i = 0; i < productos.size(); i++) {
                            if (i >= min && i < max) {
                                productosCat.add(productos.get(i));
                            }
                        }
                    }
                    //El número de páginas será el resultado del maximo entre el número de articulos           
                    int pag = (int) Math.ceil(total / Double.parseDouble(prodPorPagina)); //Math.ceil redondea al alza
                    request.setAttribute("pag", pag);

                    request.setAttribute("orden", orden);
                    request.setAttribute("productosCat", productosCat);
                    request.setAttribute("proxpag", prodPorPagina);
                    break;

                default:
                    url = "";
            }

        } catch (NullPointerException ex) {
            ex.printStackTrace();
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

    public ArrayList<Producto> ordenarPrecioAsc(ArrayList<Producto> productos) {

        //Ordenamos el array por precio ascendente con metodo de la burbuja
        Producto proAuxiliar = null;
        for (int i = 0; i < productos.size(); i++) {
            for (int j = i + 1; j < productos.size(); j++) {
                if (productos.get(i).getPrecioUnitario() > productos.get(j).getPrecioUnitario()) {
                    proAuxiliar = productos.get(i);
                    productos.set(i, productos.get(j));
                    productos.set(j, proAuxiliar);
                }
            }
        }

        return productos;
    }

    public ArrayList<Producto> ordenarPrecioDesc(ArrayList<Producto> productos) {

        //Ordenamos el array por precio descendente con metodo de la burbuja
        Producto proAuxiliar = null;
        for (int i = 0; i < productos.size(); i++) {
            for (int j = i + 1; j < productos.size(); j++) {
                if (productos.get(i).getPrecioUnitario() < productos.get(j).getPrecioUnitario()) {
                    proAuxiliar = productos.get(i);
                    productos.set(i, productos.get(j));
                    productos.set(j, proAuxiliar);
                }
            }
        }

        return productos;
    }

    public ArrayList<Producto> ordenarPopularidad(ArrayList<Producto> productos) {

        //Ordenamos el array por popularidad con metodo de la burbuja
        Producto proAuxiliar = null;
        for (int i = 0; i < productos.size(); i++) {
            for (int j = i + 1; j < productos.size(); j++) {
                if (productos.get(i).getRating() < productos.get(j).getRating()) {
                    proAuxiliar = productos.get(i);
                    productos.set(i, productos.get(j));
                    productos.set(j, proAuxiliar);
                }
            }
        }

        return productos;
    }

    public ArrayList<Producto> ordenarNombre(ArrayList<Producto> productos) {

        //Ordenamos el array por nombre con metodo de la burbuja con compareTo 
        Producto proAuxiliar = null;
        for (int i = 0; i < productos.size(); i++) {
            for (int j = i + 1; j < productos.size(); j++) {
                if (productos.get(i).getDenominacion().compareTo(productos.get(j).getDenominacion()) > 0) {
                    proAuxiliar = productos.get(i);
                    productos.set(i, productos.get(j));
                    productos.set(j, proAuxiliar);
                }
            }
        }

        return productos;
    }
}
