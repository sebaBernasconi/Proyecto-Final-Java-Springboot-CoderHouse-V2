package com.coderhouse.proyectofinal.model.product;

import jakarta.persistence.*;

@Entity
public abstract class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idProd;
    @Column(name = "codigo_de_producto")
    private int codigoDeProducto;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "stock")
    private int stock;
    @Column(name = "precio")
    private float precio;

    //Constructor


    public Producto( int codigoDeProducto, String nombre,
                    String descripcion, int stock, float precio) {
        this.codigoDeProducto = codigoDeProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
    }

    //Constructor vacio para la persistencia
    public Producto() {

    }

    //Metodos de la clase
    public void actualizarStock() {
        System.out.println("Stock anterior: " + this.stock);
        this.stock--;
        System.out.println("Stock actualizado! \n nuevo stock: " +
                this.stock);
    }

    public void modificarPrecio(float nuevoPrecio) {
        if (this.precio != nuevoPrecio) {
            this.precio = nuevoPrecio;
        } else {
            System.out.println("El precio nuevo es igual al anterior." +
                    " No se realizo ninguna modificacion");
        }
    }

    //Getters y Setters

    public int getIdProd() {
        return idProd;
    }

    public void setIdProd(int idProd) {
        this.idProd = idProd;
    }

    public int getCodigoDeProducto() {
        return codigoDeProducto;
    }

    public void setCodigoDeProducto(int codigoDeProducto) {
        this.codigoDeProducto = codigoDeProducto;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }


}
