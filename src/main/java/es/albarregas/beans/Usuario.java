package es.albarregas.beans;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {

    private int IdUsuario;
    private String Email;
    private String Clave;
    private Date UltimoAcceso;
    private String Tipo;
    private String Bloqueado;
    private Cliente cliente;


    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Clave;
    }

    public Date getUltimoAcceso() {
        return UltimoAcceso;
    }

    public void setUltimoAcceso(Date UltimoAcceso) {
        this.UltimoAcceso = UltimoAcceso;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getBloqueado() {
        return Bloqueado;
    }

    public void setBloqueado(String Bloqueado) {
        this.Bloqueado = Bloqueado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
        
    
}
