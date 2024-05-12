package com.coderhouse.proyectofinal.model.user;

import com.coderhouse.proyectofinal.model.payment.Debito;
import com.coderhouse.proyectofinal.model.product.Producto;
import com.coderhouse.proyectofinal.model.transactions.Compra;
import com.coderhouse.proyectofinal.model.payment.MedioDePago;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.util.List;

@Schema(description = "Modelo del Cliente")
@Entity
@Table(name = "clientes")
public class Client extends User {

    @Schema(description = "Tarjeta asociada al Cliente", requiredMode = Schema.RequiredMode.REQUIRED)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "nro_tarjeta")
    private Debito tDebito;

    @Schema(description = "Carrito asociado al Cliente", requiredMode = Schema.RequiredMode.REQUIRED)
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_carrito")
    private Carrito carrito;

    @Schema(description = "Comrpas asociadas al Cliente", requiredMode = Schema.RequiredMode.REQUIRED)
    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Compra> compras;

    //Constructor

    public Client(int cuil, String nombre, String mail, String password,
                  Debito tDebito, Carrito carrito, List<Compra> compras) {
        super(cuil, nombre, mail, password);
        this.tDebito = tDebito;
        this.carrito = carrito;
        this.compras = compras;
    }

    //Constructor vacio para poder persistir
    public Client(){
        super();

    }

    //Metodos de la clase
    public void agregarAlCarrito(Producto p){
        //Mensjae en el metodo de carrito
        this.carrito.agregarAlCarrito(p);
        p.actualizarStock();

    }

    public void sacarDelCarrito(Producto p){
        //Mensjae en el metodo de carrito
        this.carrito.sacarDelCarrito(p);
    }

    public void pagarCarrito(){
        //Mensjae en el metodo de cada medio de pago
        this.tDebito.pagar(this.carrito.getTotal());
    }

    //Getters y Setters

    public Debito gettDebito() {
        return tDebito;
    }

    public void settDebito(Debito tDebito) {
        this.tDebito = tDebito;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public void mostrar(){
        System.out.println(

                " Cuil: " + this.getCuil() +
                "\n Nombre: " + this.getNombre() +
                "\n Mail: " + this.getMail() +
                "\n Contraseña: " + this.getPassword() +
                "\n tDebito=" + tDebito.getNroTarjeta() +
                "\n carrito=" + carrito.getIdCarrito() );
    }

}
