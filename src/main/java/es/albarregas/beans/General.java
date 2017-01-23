/*
    Bean de General, donde definimos gastos de envío e IVA
 */
package es.albarregas.beans;

import java.io.Serializable;

public class General implements Serializable{
    
    private double GastosEnvio;
    private double Iva;

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
 

}
