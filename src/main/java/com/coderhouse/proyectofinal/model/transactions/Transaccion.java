package com.coderhouse.proyectofinal.model.transactions;

import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.model.ticket.Factura;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.payment.MedioDePago;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public abstract class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idTransaccion;

    @ManyToOne
    @JoinColumn(name = "id_carrito")
    private Carrito carrito;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "total")
    private float total;

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
