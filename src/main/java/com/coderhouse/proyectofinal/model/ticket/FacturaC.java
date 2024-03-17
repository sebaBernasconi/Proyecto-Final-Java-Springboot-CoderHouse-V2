package com.coderhouse.proyectofinal.model.ticket;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "factura_c")
public class FacturaC extends Factura {

    public FacturaC(int nroFactura,
                    int cuilCliente, LocalDate fecha, float total) {
        super(nroFactura, cuilCliente, fecha, total);
    }

    //Constructor vacio para persistencia
    public FacturaC() {

    }
}
