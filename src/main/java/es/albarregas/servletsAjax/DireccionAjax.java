/* 
    Este Servlet lo vamos a utilizar para sacar direcciones en varias interfaces.
    Primero, para sacar datos relacionados con un codigo postal a la hora de introducir una 
    nueva dirección, y también para sacar datos relacionados con un idDireccion si venimos de
    seleccionar una dirección en el carrito
 */
package es.albarregas.servletsAjax;

import com.google.gson.Gson;
import es.albarregas.beans.Direccion;
import es.albarregas.beans.Pueblo;
import es.albarregas.beans.Usuario;
import es.albarregas.dao.IProvinciasDAO;
import es.albarregas.dao.IPueblosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "DireccionAjax", urlPatterns = {"/direccionAjax"})
public class DireccionAjax extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        //Si estamos añadiendo direcciones y queremos sacar el Pueblo y Provincia en base a un CP
        if (request.getParameter("cp") != null) {
            String cp = request.getParameter("cp").trim();
            ArrayList<Pueblo> listaPueblos = null;

            if (cp.length() == 5) {
                DAOFactory df = DAOFactory.getDAOFactory(1);
                IPueblosDAO ipud = df.getPueblosDAO();
                listaPueblos = ipud.getPueblos("WHERE CodigoPostal = '" + cp + "'");
                IProvinciasDAO iprd = df.getProvinciasDAO();
                System.out.println(listaPueblos);
                for (int i = 0; i < listaPueblos.size(); i++) {
                    listaPueblos.get(i).setProvincia(
                            iprd.getProvincias("WHERE IdProvincia = " + listaPueblos.get(i).getProvincia().getIdProvincia()).get(0)
                    );
                }
            }
            PrintWriter out = response.getWriter();
            Gson json = new Gson();
            out.print(json.toJson(listaPueblos));
            out.flush();
            out.close();

            //Si estamos eligiendo dirección en el carrito y queremos sacar datos direccion de un IdDireccion
        } else if (request.getParameter("idDir") != null) {
            int idDireccion = Integer.parseInt(request.getParameter("idDir").trim());
//            Aquí no vamos a sacar la direccion de la BD, porque es consumir recursos innecesarios, ya que 
//            tenemos esos datos en sesión...
            Usuario usuario = (Usuario) request.getSession().getAttribute("usuario");
            ArrayList<Direccion> direcciones = usuario.getCliente().getListaDirecciones();
            Direccion direccionSelec = new Direccion();
            for(Direccion direccion : direcciones){
                if(direccion.getIdDireccion() == idDireccion){
                    direccionSelec = direccion;
                }
            }
            
            
            PrintWriter out = response.getWriter();
            Gson json = new Gson();
            out.print(json.toJson(direccionSelec));
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
