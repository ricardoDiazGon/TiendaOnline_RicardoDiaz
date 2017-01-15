package es.albarregas.beans;

import java.io.Serializable;


public class Direccion implements Serializable{
    
    private int IdDireccion;
    private int IdCliente;
    private String NombreDireccion;
    private String Direccion;
    private String CodigoPostal;
    private int IdPueblo;
    private String NombrePueblo;
    private int IdProvincia;
    private String NombreProvincia;
    private String Telefono;

    public int getIdPueblo() {
        return IdPueblo;
    }

    public void setIdPueblo(int IdPueblo) {
        this.IdPueblo = IdPueblo;
    }

    public String getNombrePueblo() {
        return NombrePueblo;
    }

    public void setNombrePueblo(String NombrePueblo) {
        this.NombrePueblo = NombrePueblo;
    }

    public int getIdProvincia() {
        return IdProvincia;
    }

    public void setIdProvincia(int IdProvincia) {
        this.IdProvincia = IdProvincia;
    }

    public String getNombreProvincia() {
        return NombreProvincia;
    }

    public void setNombreProvincia(String NombreProvincia) {
        this.NombreProvincia = NombreProvincia;
    }   
    
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

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }
    

}
