/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Ricardo
 */
public class Pedido implements Serializable {
    
    private int IdPedido;
    private Date Fecha;
    private String Estado;
    private int IdCliente;
    private double BaseImponible;
    private double Descuento;
    private double GastosEnvio;
    private double Iva;
    private int IdDireccion;
    private ArrayList<LineaPedido> lineasPedidos;

    public int getIdPedido() {
        return IdPedido;
    }

    public void setIdPedido(int IdPedido) {
        this.IdPedido = IdPedido;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public double getBaseImponible() {
        return BaseImponible;
    }

    public void setBaseImponible(double BaseImponible) {
        this.BaseImponible = BaseImponible;
    }

    public double getDescuento() {
        return Descuento;
    }

    public void setDescuento(double Descuento) {
        this.Descuento = Descuento;
    }

    public double getGastosEnvio() {
        return GastosEnvio;
    }

    public void setGastosEnvio(double GastosEnvio) {
        this.GastosEnvio = GastosEnvio;
    }

    public double getIva() {
        return Iva;
    }

    public void setIva(double Iva) {
        this.Iva = Iva;
    }

    public int getIdDireccion() {
        return IdDireccion;
    }

    public void setIdDireccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }

    public ArrayList<LineaPedido> getLineasPedidos() {
        return lineasPedidos;
    }

    public void setLineasPedidos(ArrayList<LineaPedido> lineasPedidos) {
        this.lineasPedidos = lineasPedidos;
    }
    
    
    
}
