package com.coderhouse.proyectofinal.model.ticket;

import java.util.Date;

public abstract class Factura {
    //idFactura es el comprobante interno del sistema
    private int idFactura;

    //nroFactura es el numero real de la factura que asigna afip
    private int nroFactura;

    private int cuilCliente;

    private Date fecha;

    private float total;

    //Constructor

    public Factura(int idFactura, int nroFactura,
                   int cuilCliente, Date fecha, float total) {
        this.idFactura = idFactura;
        this.nroFactura = nroFactura;
        this.cuilCliente = cuilCliente;
        this.fecha = fecha;
        this.total = total;
    }

    //Getters y Setters

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(int nroFactura) {
        this.nroFactura = nroFactura;
    }

    public int getCuilCliente() {
        return cuilCliente;
    }

    public void setCuilCliente(int cuilCliente) {
        this.cuilCliente = cuilCliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
