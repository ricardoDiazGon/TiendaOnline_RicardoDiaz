package es.albarregas.beans;

import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable {

    private int IdUsuario;
    private String UserName;
    private String Clave;
    private Date UltimoAcceso;
    private char Tipo;
    private char Bloqueado;

    public int getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(int IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
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

    public char getTipo() {
        return Tipo;
    }

    public void setTipo(char Tipo) {
        this.Tipo = Tipo;
    }

    public char getBloqueado() {
        return Bloqueado;
    }

    public void setBloqueado(char Bloqueado) {
        this.Bloqueado = Bloqueado;
    }

}
