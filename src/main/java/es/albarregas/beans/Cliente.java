package es.albarregas.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Cliente implements Serializable{

    private int IdCliente;
    private String Nombre;
    private String Apellidos;
    private String NIF;
    private Date FechaNacimiento;
    private Date FechaAlta;
    private ArrayList<Direccion> ListaDirecciones;
    
    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public Date getFechaAlta() {
        return FechaAlta;
    }

    public void setFechaAlta(Date FechaAlta) {
        this.FechaAlta = FechaAlta;
    }

    public ArrayList<Direccion> getListaDirecciones() {
        return ListaDirecciones;
    }

    public void setListaDirecciones(ArrayList<Direccion> listaDirecciones) {
        this.ListaDirecciones = listaDirecciones;
    }
            
}
