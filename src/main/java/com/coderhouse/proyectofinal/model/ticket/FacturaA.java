package com.coderhouse.proyectofinal.model.ticket;

import com.coderhouse.proyectofinal.model.product.Producto;
import com.coderhouse.proyectofinal.model.user.Client;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Schema(description = "Modelo de Factura A")
@Entity
@Table(name = "factura_a")
public class FacturaA extends Factura{

    public FacturaA(int nroFactura, Client client, int cuilCliente,
                    int cantidadDeProductos, List<Producto> productos, LocalDate fecha, float total) {
        super( nroFactura, client, cuilCliente, cantidadDeProductos, productos, fecha, total);
    }

    //Constructor vacio para persistencia
    public FacturaA() {

    }
}
