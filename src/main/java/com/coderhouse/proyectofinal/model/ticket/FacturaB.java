package com.coderhouse.proyectofinal.model.ticket;

import com.coderhouse.proyectofinal.model.product.Producto;
import com.coderhouse.proyectofinal.model.user.Client;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "factura_b")
public class FacturaB extends Factura{
    public FacturaB( int nroFactura, Client client, int cuilCliente,
                    int cantidadDeProductos, List<Producto> productos, LocalDate fecha, float total) {
        super( nroFactura, client, cuilCliente, cantidadDeProductos, productos, fecha, total);
    }

    //Constructor vacio para persistencia
    public FacturaB() {

    }
}
