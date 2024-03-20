package com.coderhouse.proyectofinal.model.transactions;

import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.model.user.Admin;
import com.coderhouse.proyectofinal.model.payment.MedioDePago;
import com.coderhouse.proyectofinal.model.user.Carrito;

import java.util.Calendar;
import java.util.Date;

public class Compra extends Transaccion {
    private Admin vendedor;

    //Constructor

    public Compra(Date fecha, Carrito carrito,
                   float total, Admin vendedor) {
        super( fecha,carrito, total);
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
}
