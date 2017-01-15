package es.albarregas.servletsAjax;

import com.google.gson.Gson;
import es.albarregas.beans.Provincia;
import es.albarregas.beans.Pueblo;
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
                System.out.println("Leyendo provincia");
            }
        }
        PrintWriter out = response.getWriter();
        Gson json = new Gson();
        out.print(json.toJson(listaPueblos));
        out.flush();
        out.close();
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
