/*
    Bean de productos
 */
package es.albarregas.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Producto implements Serializable {

    private int IdProducto;
    private int IdCategoria;
    private String NombreCategoria;
    private String DenoMarca;
    private int IdMarca;
    private String Denominacion;
    private String Descripcion;
    private int IdProveedor;
    private double PrecioUnitario;
    private int Stock;
    private int StockMinimo;
    private Date FechaAlta;
    private String Oferta;
    private String FueraCatalogo;
    private int Rating;
    private ArrayList<Imagen> Imagenes;

    public int getIdProducto() {
        return IdProducto;
    }

    public void setIdProducto(int IdProducto) {
        this.IdProducto = IdProducto;
    }

    public int getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(int IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    public String getNombreCategoria() {
        return NombreCategoria;
    }

    public void setNombreCategoria(String NombreCategoria) {
        this.NombreCategoria = NombreCategoria;
    }

    public String getDenoMarca() {
        return DenoMarca;
    }

    public void setDenoMarca(String DenoMarca) {
        this.DenoMarca = DenoMarca;
    }

    public int getIdMarca() {
        return IdMarca;
    }

    public void setIdMarca(int IdMarca) {
        this.IdMarca = IdMarca;
    }

    public String getDenominacion() {
        return Denominacion;
    }

    public void setDenominacion(String Denominacion) {
        this.Denominacion = Denominacion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public int getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(int IdProveedor) {
        this.IdProveedor = IdProveedor;
    }

    public double getPrecioUnitario() {
        return PrecioUnitario;
    }

    public void setPrecioUnitario(double PrecioUnitario) {
        this.PrecioUnitario = PrecioUnitario;
    }

    public int getStock() {
        return Stock;
    }

    public void setStock(int Stock) {
        this.Stock = Stock;
    }

    public int getStockMinimo() {
        return StockMinimo;
    }

    public void setStockMinimo(int StockMinimo) {
        this.StockMinimo = StockMinimo;
    }

    public Date getFechaAlta() {
        return FechaAlta;
    }

    public void setFechaAlta(Date FechaAlta) {
        this.FechaAlta = FechaAlta;
    }

    public String getOferta() {
        return Oferta;
    }

    public void setOferta(String Oferta) {
        this.Oferta = Oferta;
    }

    public String getFueraCatalogo() {
        return FueraCatalogo;
    }

    public void setFueraCatalogo(String FueraCatalogo) {
        this.FueraCatalogo = FueraCatalogo;
    }

    public int getRating() {
        return Rating;
    }

    public void setRating(int Rating) {
        this.Rating = Rating;
    }

    public ArrayList<Imagen> getImagenes() {
        return Imagenes;
    }

    public void setImagenes(ArrayList<Imagen> Imagenes) {
        this.Imagenes = Imagenes;
    }

}
