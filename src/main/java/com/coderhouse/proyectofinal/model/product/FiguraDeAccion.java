package com.coderhouse.proyectofinal.model.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Schema(description = "Modelo de las FigurasDeAccion")
@Entity
@Table(name = "figura_de_accion")
public class FiguraDeAccion extends Producto {

    @Schema(description = "Empresa fabricante de la Figura",requiredMode = Schema.RequiredMode.REQUIRED, example = "Mattel")
    @Column(name = "fabricante")
    private String fabricante;

    @Schema(description = "Caracteristica de la Figura",requiredMode = Schema.RequiredMode.REQUIRED, example = "false")
    @Column(name = "esArticulado")
    private boolean esArticulado;

    //Constructor

    public FiguraDeAccion(int codigoDeProducto, String nombre, String descripcion, int stock,
                          float precio, String fabricante, boolean esArticulado) {
        super(codigoDeProducto, nombre, descripcion, stock, precio);
        this.fabricante = fabricante;
        this.esArticulado = esArticulado;
    }

    //Constructor vacio para la persistencia
    public FiguraDeAccion() {

    }

    public void editarFiguraDeAccion(String nuevoNombre ,String nuevaDescripcion,
                               String nuevoFabricante, boolean nuevoEsArticulado) {
        if (this.getNombre() != nuevoNombre && nuevoNombre != ""){
            this.setNombre(nuevoNombre);
        }

        if (this.getDescripcion() != nuevaDescripcion && nuevaDescripcion != ""){
            this.setDescripcion(nuevaDescripcion);
        }

        if (this.fabricante != nuevoFabricante && nuevoFabricante != ""){
            this.fabricante = nuevoFabricante;
        }

        if (this.esArticulado != nuevoEsArticulado){
            this.esArticulado = nuevoEsArticulado;
        }

    }

    //To String

    @Override
    public String toString() {

        return
                super.toString() +
                "\nfabricante='" + fabricante +
                "\n esArticulado=" + esArticulado;
    }

    //Getters y Setters

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public boolean isEsArticulado() {
        return esArticulado;
    }

    public void setEsArticulado(boolean esArticulado) {
        this.esArticulado = esArticulado;
    }
}
