/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Marca;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public interface IMarcasDAO {

    public int addMarcas(Marca marca);

    public ArrayList<Marca> getMarcas(String clausulaWhere);

    public int updMarcas(Marca marca);

    public int delMarcas(String clausulaWhere);

    public void closeConnection();
}
