package com.coderhouse.proyectofinal.model.transactions;

import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.model.ticket.Factura;
import com.coderhouse.proyectofinal.model.ticket.FacturaA;
import com.coderhouse.proyectofinal.model.ticket.FacturaB;
import com.coderhouse.proyectofinal.model.ticket.FacturaC;
import com.coderhouse.proyectofinal.model.user.Admin;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;
import com.coderhouse.proyectofinal.model.payment.MedioDePago;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Schema(description = "Modelo de Venta")
@Entity
@Table(name = "venta")
public class Venta extends Transaccion {

    @Schema(description = "Cliente asociado a la Venta", requiredMode = Schema.RequiredMode.REQUIRED)
    @ManyToOne
    @JoinColumn(name = "cuil_cliente")
    private Client client;

    @Schema(description = "Admin asociado a la Venta",requiredMode = Schema.RequiredMode.REQUIRED)
    @ManyToOne
    @JoinColumn(name = "cuil_admin")
    private Admin vendedor;

    //Constructor

    public Venta( Carrito carrito,
                 float total, Factura factura, Client client, Admin vendedor) {
        super( carrito, total,factura);
        this.client = client;
        this.vendedor = vendedor;
    }

    //Constructor vacio para la persistencia
    public Venta() {

    }

    //Metodos de la clase
    public FacturaA GenerarFacturaA(){
        return  new FacturaA(getIdTransaccion(),getClient(),getClient().getCuil(),getCarrito().getCantidadDeArticulos(),
                getCarrito().getProductos(), LocalDate.now(),getTotal());
    }

    public FacturaB GenerarFacturaB(){
        return  new FacturaB(getIdTransaccion(),getClient(),getClient().getCuil(),getCarrito().getCantidadDeArticulos(),
                getCarrito().getProductos(), LocalDate.now(),getTotal());

    }

    public FacturaC GenerarFacturaC(){
        return  new FacturaC(getIdTransaccion(),getClient(),getClient().getCuil(),getCarrito().getCantidadDeArticulos(),
                getCarrito().getProductos(), LocalDate.now(),getTotal());
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


    @Override
    public String toString() {
        return
                super.toString() +
                        "\n client=" + client.getCuil() +
                        "\n vendedor=" + vendedor.getCuil();
    }
}
