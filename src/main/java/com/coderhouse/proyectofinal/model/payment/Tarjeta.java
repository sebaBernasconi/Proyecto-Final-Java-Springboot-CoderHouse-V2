package com.coderhouse.proyectofinal.model.payment;

public abstract class Tarjeta extends MedioDePago{
    private int nroTarjeta;
    private String titular;
    private int codigoDeSeguridad;

    //Constructor
    public Tarjeta(int nroTarjeta, String titular, int codigoDeSeguridad) {
        this.nroTarjeta = nroTarjeta;
        this.titular = titular;
        this.codigoDeSeguridad = codigoDeSeguridad;
    }

    //Al ser abtracta no tengo que implementar el pagar del medio de pago
    //lo implemento en Debito que es la que hereda de Tarjeta

    //Getters y Setters


    public int getNroTarjeta() {
        return nroTarjeta;
    }

    public void setNroTarjeta(int nroTarjeta) {
        this.nroTarjeta = nroTarjeta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public int getCodigoDeSeguridad() {
        return codigoDeSeguridad;
    }

    public void setCodigoDeSeguridad(int codigoDeSeguridad) {
        this.codigoDeSeguridad = codigoDeSeguridad;
    }
}
