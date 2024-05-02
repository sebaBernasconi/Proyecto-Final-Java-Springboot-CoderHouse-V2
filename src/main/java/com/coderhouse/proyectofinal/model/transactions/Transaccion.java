package com.coderhouse.proyectofinal.model.transactions;

import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.model.ticket.Factura;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.payment.MedioDePago;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.Date;

@Schema(description = "Modelo de Transaccion")
@Entity
public abstract class Transaccion {

    @Schema(description = "Id autogenerado de la transaccion")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idTransaccion;

    @Schema(description = "Carrito asociado a la transaccion",requiredMode = Schema.RequiredMode.REQUIRED)
    @ManyToOne
    @JoinColumn(name = "id_carrito")
    private Carrito carrito;

    @Schema(description = "Fecha de la Transaccion",requiredMode = Schema.RequiredMode.REQUIRED,
    example = "2024-05-30")
    @Column(name = "fecha")
    private Date fecha;

    @Schema(description = "Total de la Transaccion",requiredMode = Schema.RequiredMode.REQUIRED,
    example = "234.53")
    @Column(name = "total")
    private float total;

    @Schema(description = "Factura asociada a la Transaccion(si la tiene)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ManyToOne
    @JoinColumn(name = "nro_factura")
    private Factura factura;

    //Constructor

    public Transaccion(Date fecha, Carrito carrito,float total, Factura factura) {
        this.fecha = fecha;
        this.carrito = carrito;
        this.total = total;
        this.factura = factura;
    }

    //Constructor vacio para la persistencia
    public Transaccion() {

    }

    //Getters y Setters


    public int getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(int idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
}
