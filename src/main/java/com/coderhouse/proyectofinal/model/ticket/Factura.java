package com.coderhouse.proyectofinal.model.ticket;

import com.coderhouse.proyectofinal.model.product.Producto;
import com.coderhouse.proyectofinal.model.user.Client;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Schema(description = "Modelo de Factura")
@Entity
public abstract class Factura {
    //idFactura es el comprobante interno del sistema
    @Schema(description = "id de los productos")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idFactura;

    //nroFactura es el numero real de la factura que asigna afip
    @Schema(description = "Numero de la Factura", requiredMode = Schema.RequiredMode.REQUIRED,example = "34522342")
    @Column(name = "nro_factura")
    private int nroFactura;


    @Schema(description = "Cliente",requiredMode = Schema.RequiredMode.REQUIRED)
    @ManyToOne
    @JoinColumn(name = "cuil_cliente")
    private Client client;

    @Schema(description = "Cuil del cliente al cual se le Factura", requiredMode = Schema.RequiredMode.REQUIRED,example = "20123456780")
    @Column(name = "id_cliente")
    private int cuilCliente;

    @Schema(description = "Cantidad de productos",requiredMode = Schema.RequiredMode.REQUIRED,example = "6")
    @Column(name = "cantidad_prods")
    private int cantidadDeProductos;

    @Schema(description = "Listado de Productos a facturar", requiredMode = Schema.RequiredMode.REQUIRED)
    @ManyToMany
    @JoinTable(name = "factura_producto",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name ="id_producto"))
    private List<Producto> productos;

    @Schema(description = "Fecha de la Factura",requiredMode = Schema.RequiredMode.REQUIRED, example = "2024-04-15")
    @Column(name = "fecha")
    private LocalDate fecha;

    @Schema(description = "Importe de la Factura",requiredMode = Schema.RequiredMode.REQUIRED, example = "234.32")
    @Column(name = "total")
    private float total;

    //Constructor


    public Factura(int idFactura, int nroFactura, Client client, int cuilCliente,
                   int cantidadDeProductos, List<Producto> productos, LocalDate fecha, float total) {
        this.idFactura = idFactura;
        this.nroFactura = nroFactura;
        this.client = client;
        this.cuilCliente = cuilCliente;
        this.cantidadDeProductos = cantidadDeProductos;
        this.productos = productos;
        this.fecha = fecha;
        this.total = total;
    }

    //Constructor vacio para persistencia
   public Factura(){

   }

    //Getters y Setters

    public int getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(int idFactura) {
        this.idFactura = idFactura;
    }

    public int getNroFactura() {
        return nroFactura;
    }

    public void setNroFactura(int nroFactura) {
        this.nroFactura = nroFactura;
    }

    public int getCuilCliente() {
        return cuilCliente;
    }

    public void setCuilCliente(int cuilCliente) {
        this.cuilCliente = cuilCliente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
