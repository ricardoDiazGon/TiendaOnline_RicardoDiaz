/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.dao;

import es.albarregas.beans.Producto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ricardo
 */
public class ProductosDAO implements IProductosDAO{

    @Override
    public int addProductos(Producto producto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Producto> getProductos(String clausulaWhere) {
        String sql = "SELECT * FROM Productos P LEFT JOIN Categorias C ON  P.IdCategoria =  C.IdCategoria "
                + "LEFT JOIN Marcas M ON P.IdMarca = M.IdMarca " + clausulaWhere;
        Statement sentencia = null;
        ResultSet resultado = null;
        ArrayList<Producto> listaProductos = null;
        try {

            sentencia = ConnectionFactory.getConnection().createStatement();
            resultado = sentencia.executeQuery(sql);

            listaProductos = new ArrayList();
            Producto producto = null;
            while (resultado.next()) {
                producto = new Producto();
                producto.setIdProducto(resultado.getInt("P.IdProducto"));
                producto.setIdCategoria(resultado.getInt("C.IdCategoria"));
                producto.setNombreCategoria(resultado.getString("C.Nombre"));
                producto.setIdMarca(resultado.getInt("M.IdMarca"));
                producto.setDenoMarca(resultado.getString("M.Denominacion"));
                producto.setDenominacion(resultado.getString("P.Denominacion"));
                producto.setDescripcion(resultado.getString("P.Descripcion"));
                producto.setIdProveedor(resultado.getInt("P.IdProveedor"));
                producto.setPrecioUnitario(resultado.getDouble("P.PrecioUnitario"));
                producto.setStock(resultado.getInt("P.Stock"));
                producto.setStockMinimo(resultado.getInt("P.StockMinimo"));
                producto.setFechaAlta(resultado.getDate("P.FechaAlta"));
                producto.setOferta(resultado.getString("P.Oferta"));
                producto.setFueraCatalogo(resultado.getString("P.FueraCatalogo"));
                producto.setRating(resultado.getInt("P.Rating"));
                listaProductos.add(producto);
            }
            sentencia.close();
            resultado.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeConnection();
        return listaProductos;
    }

    @Override
    public int updProductos(Producto producto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int delProductos(String clausulaWhere) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void closeConnection() {
        ConnectionFactory.closeConnection();
    }
    
}
