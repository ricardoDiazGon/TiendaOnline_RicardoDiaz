/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Imagen;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public interface IIMagenesDAO {
    
    public int addImagenes(Imagen imagen);

    public ArrayList<Imagen> getImagenes(String clausulaWhere);

    public int updImagenes(Imagen imagen);

    public int delImagenes(String clausulaWhere);

    public void closeConnection();    
}
