package com.coderhouse.proyectofinal.model.transactions;

import com.coderhouse.proyectofinal.model.user.Admin;
import com.coderhouse.proyectofinal.model.user.Client;
import com.coderhouse.proyectofinal.model.payment.MedioDePago;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "venta")
public class Venta extends Transaccion {

    private Client client;
    private Admin vendedor;

    //Constructor

    public Venta( Date fecha, MedioDePago medioDePago,
                 float total, Client client, Admin vendedor) {
        super( fecha, medioDePago, total);
        this.client = client;
        this.vendedor = vendedor;
    }

    //Constructor vacio para la persistencia
    public Venta() {

    }
}
