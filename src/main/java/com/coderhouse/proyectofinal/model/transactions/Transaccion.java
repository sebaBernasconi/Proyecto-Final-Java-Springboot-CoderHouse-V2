package com.coderhouse.proyectofinal.model.transactions;

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

    private Carrito carrito;
    @Column(name = "fecha")
    private Date fecha;
    private Factura factura;
    private MedioDePago medioDePago;
    @Column(name = "total")
    private float total;

    //Constructor

    public Transaccion(Date fecha, MedioDePago medioDePago, float total) {
        this.fecha = fecha;
        this.medioDePago = medioDePago;
        this.total = total;
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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public MedioDePago getMedioDePago() {
        return medioDePago;
    }

    public void setMedioDePago(MedioDePago medioDePago) {
        this.medioDePago = medioDePago;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
