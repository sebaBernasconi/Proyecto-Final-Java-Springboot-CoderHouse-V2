package com.coderhouse.proyectofinal.model.ticket;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Schema(description = "Modelo de Factura")
@Entity
public abstract class Factura {
    //idFactura es el comprobante interno del sistema
    @Schema(description = "id de los productos")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idFactura;

    //nroFactura es el numero real de la factura que asigna afip
    @Schema(description = "Numero de la Factura", requiredMode = Schema.RequiredMode.REQUIRED,example = "34522342")
    @Column(name = "nro_factura")
    private int nroFactura;

    @Schema(description = "Cuil del cliente al cual se le Factura", requiredMode = Schema.RequiredMode.REQUIRED,example = "20123456780")
    @Column(name = "cuil_cliente")
    private int cuilCliente;

    @Schema(description = "Fecha de la Factura",requiredMode = Schema.RequiredMode.REQUIRED, example = "2024-04-15")
    @Column(name = "fecha")
    private LocalDate fecha;

    @Schema(description = "Importe de la Factura",requiredMode = Schema.RequiredMode.REQUIRED, example = "234.32")
    @Column(name = "total")
    private float total;

    //Constructor

    public Factura(int nroFactura,
                   int cuilCliente, LocalDate fecha, float total) {
        this.nroFactura = nroFactura;
        this.cuilCliente = cuilCliente;
        this.fecha = fecha;
        this.total = total;
    }

    //Constructor vacio para persistencia
   public Factura(){

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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
