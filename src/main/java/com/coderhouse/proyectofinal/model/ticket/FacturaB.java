package com.coderhouse.proyectofinal.model.ticket;

import java.util.Date;

public class FacturaB extends Factura{
    public FacturaB(int idFactura, int nroFactura,
                    int cuilCliente, Date fecha, float total) {
        super(idFactura, nroFactura, cuilCliente, fecha, total);
    }
}
