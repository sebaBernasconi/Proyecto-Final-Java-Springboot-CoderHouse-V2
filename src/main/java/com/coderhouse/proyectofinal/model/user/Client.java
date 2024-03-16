package com.coderhouse.proyectofinal.model.user;

import com.coderhouse.proyectofinal.model.product.Producto;
import com.coderhouse.proyectofinal.model.transactions.Compra;
import com.coderhouse.proyectofinal.model.payment.MedioDePago;

import java.util.List;

public class Client extends User {

    private MedioDePago medioDePago;
    private Carrito carrito;
    private List<Compra> compras;

    //Constructor

    public Client(int idUsr, int cuil, String nombre, String mail, String password,
                  MedioDePago medioDePago, Carrito carrito, List<Compra> compras) {
        super(idUsr, cuil, nombre, mail, password);
        this.medioDePago = medioDePago;
        this.carrito = carrito;
        this.compras = compras;
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
        this.medioDePago.pagar(this.carrito.getTotal());
    }

    //Getters y Setters

    public MedioDePago getMedioDePago() {
        return medioDePago;
    }

    public void setMedioDePago(MedioDePago medioDePago) {
        this.medioDePago = medioDePago;
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
}
