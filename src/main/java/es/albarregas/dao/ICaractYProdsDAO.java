/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.CaractYProd;
import java.util.ArrayList;

/**
 *
 * @author Ricardo
 */
public interface ICaractYProdsDAO {
    
    public int addCaractYProds(CaractYProd caractyprod);

    public ArrayList<CaractYProd> getCaractYProds(String clausulaWhere);

    public int updCaractYProds(CaractYProd caractyprod);

    public int delCaractYProds(String clausulaWhere);

    public void closeConnection();    
}
