/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Caracteristica;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public interface ICaracteristicasDAO {

    public int addCaracteristicas(Caracteristica caracteristica);

    public ArrayList<Caracteristica> getCaracteristicas(String clausulaWhere);

    public int updCaracteristicas(Caracteristica caracteristica);

    public int delCaracteristicas(String clausulaWhere);

    public void closeConnection();
}
