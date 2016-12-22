/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.controladores;

import es.albarregas.beans.Usuario;
import es.albarregas.dao.IUsuariosDAO;
import es.albarregas.daofactory.DAOFactory;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
@WebServlet(name = "Login", urlPatterns = {"/login"})
public class Login extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String url = "";
        String error = "";

        if (request.getParameter("login") != null) {

            String userName = request.getParameter("userName");
            String clave = request.getParameter("clave");
            HttpSession sesion = request.getSession(true);
            DAOFactory df = DAOFactory.getDAOFactory(1);
            IUsuariosDAO iud = df.getUsuariosDAO();
            
            if (sesion.getAttribute("usuario") != null && sesion.getAttribute("usuario").equals(userName)) {
                Usuario usuario = (Usuario) sesion.getAttribute(userName);
                usuario.setUltimoAcceso(new Date());
                int errorSQL = iud.updUsuarios(usuario);
                System.out.println("Codigo sql update: " +errorSQL);
                if(errorSQL != 0){
                    
                }else{
                    sesion.setAttribute("usuario", usuario);
                    //Esta parte puede ir más abajo
                    if(usuario.getBloqueado() == 'n'){
                        if(usuario.getTipo() == 'u'){
                            
                        }else if(usuario.getTipo() == 'a'){
                            
                        }
                    }else{
                        request.setAttribute("login", "El usuario ha sido bloqueado");
                    }
                }              
            } else {
                
                ArrayList<Usuario> listaUsuarios = iud.getUsuarios("WHERE UserName = " +userName +" AND Clave = " +clave);
                if(listaUsuarios.isEmpty()){
                    
                }else{
                    Usuario usuario = listaUsuarios.get(0);
                    sesion.setAttribute("usuario", usuario);
                    
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
