package com.coderhouse.proyectofinal.model.transactions;

import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.model.user.Admin;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;
import com.coderhouse.proyectofinal.model.payment.MedioDePago;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "venta")
public class Venta extends Transaccion {

    @ManyToOne
    @JoinColumn(name = "cuil_cliente")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "cuil_admin")
    private Admin vendedor;

    //Constructor

    public Venta( Date fecha, Carrito carrito,
                 float total, Client client, Admin vendedor) {
        super( fecha, carrito, total);
        this.client = client;
        this.vendedor = vendedor;
    }

    //Constructor vacio para la persistencia
    public Venta() {

    }

    //Getters y Setters

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Admin getVendedor() {
        return vendedor;
    }

    public void setVendedor(Admin vendedor) {
        this.vendedor = vendedor;
    }
}
