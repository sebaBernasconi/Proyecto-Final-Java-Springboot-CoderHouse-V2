package com.coderhouse.proyectofinal.model.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Schema(description = "Modelo de Producto")
@Entity
public abstract class Producto {
    @Schema(description = "id de los productos")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int idProd;

    @Schema(description = "Codigo del Producto",requiredMode = Schema.RequiredMode.REQUIRED, example = "2342")
    @Column(name = "codigo_de_producto")
    private int codigoDeProducto;

    @Schema(description = "Nombre del Producto",requiredMode = Schema.RequiredMode.REQUIRED, example = "Flash: Born to Run")
    @Column(name = "nombre")
    private String nombre;

    @Schema(description = "Descripcion del Producto",requiredMode = Schema.RequiredMode.REQUIRED,
            example = "El comic trata sobre los primeros años de Wally West como velocista y las " +
                    "adversidades que enfrento en su carrera como compañero de Barry Allen, el segundo" +
                    "portador del manto de Flash ")
    @Column(name = "descripcion")
    private String descripcion;

    @Schema(description = "Stock del Producto",requiredMode = Schema.RequiredMode.REQUIRED, example = "3")
    @Column(name = "stock")
    private int stock;

    @Schema(description = "Precio del Producto",requiredMode = Schema.RequiredMode.REQUIRED, example = "233.43")
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

    @Override
    public String toString() {
        return
                "idProd=" + idProd +
                "\n codigoDeProducto=" + codigoDeProducto +
                "\n nombre='" + nombre + '\'' +
                "\n descripcion='" + descripcion + '\'' +
                "\n stock=" + stock +
                "\n precio=" + precio;
    }
}
