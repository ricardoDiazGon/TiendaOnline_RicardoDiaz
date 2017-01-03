package es.albarregas.beans;

import java.io.Serializable;


public class Direccion implements Serializable{
    
    private int IdDireccion;
    private int IdCliente;
    private String NombreDireccion;
    private String Direccion;
    private String CodigoPostal;
    private Pueblo pueblo;
    private String Telefono;

    public int getIdDireccion() {
        return IdDireccion;
    }

    public void setIdDireccion(int IdDireccion) {
        this.IdDireccion = IdDireccion;
    }

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getNombreDireccion() {
        return NombreDireccion;
    }

    public void setNombreDireccion(String NombreDireccion) {
        this.NombreDireccion = NombreDireccion;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCodigoPostal() {
        return CodigoPostal;
    }

    public void setCodigoPostal(String CodigoPostal) {
        this.CodigoPostal = CodigoPostal;
    }

    public Pueblo getPueblo() {
        return pueblo;
    }

    public void setPueblo(Pueblo pueblo) {
        this.pueblo = pueblo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    

}
