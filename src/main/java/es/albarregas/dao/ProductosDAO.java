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
        int errorSQL = -1; //Ponemos a -1, para saber hemos realizado la consulta o no
        boolean set = false;
        ArrayList<Producto> listaProductos = this.getProductos("WHERE IdProducto = '" + producto.getIdProducto() + "'");
        Producto producto2 = listaProductos.get(0);
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Productos ");
        /* 
            Actualizamos solamente los datos que nos vengan diferentes, para ello comparamos con los datos obtenidos
            de la base de datos. Los que sean iguales se dejan.
         */
        if (producto.getPrecioUnitario() != producto2.getPrecioUnitario()) {
            sql.append("SET PrecioUnitario = '" + producto.getPrecioUnitario() + "'");
            set = true;
        }

        if (producto.getStock() != producto2.getStock()) {
            sql.append("SET Stock = '" + producto.getStock() + "'");
            set = true;
        }

        if (producto.getStockMinimo() != producto2.getStockMinimo()) {
            sql.append("SET StockMinimo = '" + producto.getStockMinimo() + "'");
            set = true;
        }
        
        if (!producto.getDenominacion().equals(producto2.getDenominacion())) {
            if (set) {
                sql.append(", Denominacion= '" + producto.getDenominacion() + "'");
            } else {
                sql.append("SET Denominacion = '" + producto.getDenominacion() + "'");
                set = true;
            }
        }

        if (!producto.getDescripcion().equals(producto2.getDescripcion())) {
            if (set) {
                sql.append(", Descripcion = '" + producto.getDescripcion() + "'");
            } else {
                sql.append("SET Descripcion = '" + producto.getDescripcion() + "'");
                set = true;
            }
        }
        
        if (!producto.getOferta().equals(producto2.getOferta())) {
            if (set) {
                sql.append(", Oferta = '" + producto.getOferta() + "'");
            } else {
                sql.append("SET Oferta = '" + producto.getOferta() + "'");
                set = true;
            }
        }
        
        if (!producto.getFueraCatalogo().equals(producto2.getFueraCatalogo())) {
            if (set) {
                sql.append(", FueraCatalogo = '" + producto.getFueraCatalogo() + "'");
            } else {
                sql.append("SET FueraCatalogo = '" + producto.getFueraCatalogo() + "'");
                set = true;
            }
        }
        if (set) {
            sql.append(" WHERE IdProducto = '" + producto.getIdProducto() + "'");
            Statement sentencia = null;
            try {
                sentencia = ConnectionFactory.getConnection().createStatement();
                sentencia.executeUpdate(sql.toString());
                sentencia.close();
                errorSQL = 0;
            } catch (SQLException ex) {
                Logger.getLogger(ProductosDAO.class.getName()).log(Level.SEVERE, null, ex);
                errorSQL = ex.getErrorCode();
            }
        }
        this.closeConnection();
        return errorSQL;
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
