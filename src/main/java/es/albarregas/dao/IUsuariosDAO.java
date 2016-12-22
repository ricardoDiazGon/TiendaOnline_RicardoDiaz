/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Usuario;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public interface IUsuariosDAO {

    public int addUsuarios(Usuario usuario);

    public ArrayList<Usuario> getUsuarios(String clausulaWhere);

    public int updUsuarios(Usuario usuario);

    public int delUsuarios(String clausulaWhere);

    public void closeConnection();
}
