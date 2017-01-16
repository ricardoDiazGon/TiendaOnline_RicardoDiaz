/*
    Bean de General, donde definimos gastos de envío e IVA
 */
package es.albarregas.beans;

import java.io.Serializable;

public class General implements Serializable{
    
    private double GastosEnvio;
    private double IVA;

    public double getGastosEnvio() {
        return GastosEnvio;
    }

    public void setGastosEnvio(double GastosEnvio) {
        this.GastosEnvio = GastosEnvio;
    }

    public double getIVA() {
        return IVA;
    }

    public void setIVA(double IVA) {
        this.IVA = IVA;
    }
 

}
