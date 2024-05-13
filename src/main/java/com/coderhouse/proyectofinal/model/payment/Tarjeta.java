package com.coderhouse.proyectofinal.model.payment;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Schema(description = "Modelo de tarjeta")
@Entity
public abstract class Tarjeta extends MedioDePago{

    @Schema(description = "Numero de tarjeta", requiredMode = Schema.RequiredMode.REQUIRED, example = "23423423")
    @Id
    @Column(name = "nro_tarjeta")
    private int nroTarjeta;

    @Schema(description = "Nombre del titular", requiredMode = Schema.RequiredMode.REQUIRED, example = "Sebastian Bernasconi")
    @Column(name = "nombre_titular")
    private String titular;

    @Schema(description = "codigo de seguridad de la tarjeta", requiredMode = Schema.RequiredMode.REQUIRED, example = "363")

    private int codigoDeSeguridad;

    //Constructor
    public Tarjeta(int nroTarjeta, String titular, int codigoDeSeguridad) {
        this.nroTarjeta = nroTarjeta;
        this.titular = titular;
        this.codigoDeSeguridad = codigoDeSeguridad;
    }

    //Constructor vacio para la persistencia
    public Tarjeta() {

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

    @Override
    public String toString() {
        return
                "nroTarjeta=" + nroTarjeta +
                "\n titular='" + titular + '\'' +
                "\n codigoDeSeguridad=" + codigoDeSeguridad;
    }
}
