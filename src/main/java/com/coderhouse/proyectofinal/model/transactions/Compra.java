package com.coderhouse.proyectofinal.model.transactions;

import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.model.user.Admin;
import com.coderhouse.proyectofinal.model.payment.MedioDePago;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Calendar;
import java.util.Date;

public class Compra extends Transaccion {
    @ManyToOne
    @JoinColumn(name = "cuil_cliente")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "cuil_admin")
    private Admin vendedor;

    //Constructor

    public Compra(Date fecha, Carrito carrito,
                   float total, Client client,Admin vendedor) {
        super( fecha,carrito, total);
        this.client = client;
        this.vendedor = vendedor;
    }

    //Metodos de la clase
    public void verDetalleDeCompra(){
        this.getCarrito().verProductosEnElCarrito();
    }

    //Getters y Setters


    public Admin getVendedor() {
        return vendedor;
    }

    public void setVendedor(Admin vendedor) {
        this.vendedor = vendedor;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
