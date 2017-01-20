/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.beans;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Ricardo
 */
public class Proveedor implements Serializable{
    
    private int IdProveedor;
    private String RazonSocial;
    private String CIF;
    private String Direccion;
    private String CodigoPostal;
    private int IdPueblo;
    private int IdProvincia;
    private String Telefono;
    private String Email;
    private String Web;
    private String PersonaContacto;
    private Date FechaAlta;

    public int getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(int IdProveedor) {
        this.IdProveedor = IdProveedor;
    }

    public String getRazonSocial() {
        return RazonSocial;
    }

    public void setRazonSocial(String RazonSocial) {
        this.RazonSocial = RazonSocial;
    }

    public String getCIF() {
        return CIF;
    }

    public void setCIF(String CIF) {
        this.CIF = CIF;
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

    public int getIdPueblo() {
        return IdPueblo;
    }

    public void setIdPueblo(int IdPueblo) {
        this.IdPueblo = IdPueblo;
    }

    public int getIdProvincia() {
        return IdProvincia;
    }

    public void setIdProvincia(int IdProvincia) {
        this.IdProvincia = IdProvincia;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getWeb() {
        return Web;
    }

    public void setWeb(String Web) {
        this.Web = Web;
    }

    public String getPersonaContacto() {
        return PersonaContacto;
    }

    public void setPersonaContacto(String PersonaContacto) {
        this.PersonaContacto = PersonaContacto;
    }

    public Date getFechaAlta() {
        return FechaAlta;
    }

    public void setFechaAlta(Date FechaAlta) {
        this.FechaAlta = FechaAlta;
    }
    
}
