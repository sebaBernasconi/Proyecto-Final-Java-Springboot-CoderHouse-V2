package com.coderhouse.proyectofinal.model.user;

import com.coderhouse.proyectofinal.model.product.Producto;

import java.util.List;

public class Carrito {

    private int idCarrito;
    private Client cliente;
    private int cantidadDeArticulos;
    private List<Producto> productos;
    private boolean pagado;
    private float total;

    //Constructor

    public Carrito(Client cliente, int cantidadDeArticulos,
                   List<Producto> productos, boolean pagado, float total) {
        this.cliente = cliente;
        this.cantidadDeArticulos = cantidadDeArticulos;
        this.productos = productos;
        this.pagado = pagado;
        this.total = total;
    }

    //Metodos de la clase
    public void pagarCarrito(){

        this.pagado = true;
    }

    public void agregarAlCarrito(Producto p){
        this.productos.add(p);
        this.total += p.getPrecio();

        System.out.println("Producto: " + p.getNombre() +
                " agregado!");
        System.out.println("Nuevo total: " + this.total);
    }

    public boolean estaEnElCarrito(int codigoDeProducto){
        for (Producto p :
                productos) {
            if (p.getCodigoDeProducto() == codigoDeProducto) {
                return true;
            }
        }
        return false;
    }

    public void sacarDelCarrito(Producto p){
        this.productos.remove(p.getIdProd());
        this.total -= p.getPrecio();
        System.out.println("Producto: " + p.getNombre() +
                " eliminado del carrito!");
        System.out.println("Nuevo total: " + this.total);
    }

    public void verProductosEnElCarrito(){
        System.out.println("Id  |   Nombre  |   precio");
        for (Producto p :
                this.productos) {
            System.out.println(p.getIdProd() +
                    p.getNombre() + p.getPrecio());
        }
    }
    //Getters y Setters

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Client getCliente() {
        return cliente;
    }

    public void setCliente(Client cliente) {
        this.cliente = cliente;
    }

    public int getCantidadDeArticulos() {
        return cantidadDeArticulos;
    }

    public void setCantidadDeArticulos(int cantidadDeArticulos) {
        this.cantidadDeArticulos = cantidadDeArticulos;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public boolean isPagado() {
        return pagado;
    }

    public void setPagado(boolean pagado) {
        this.pagado = pagado;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}
