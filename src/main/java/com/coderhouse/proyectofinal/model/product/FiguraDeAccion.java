package com.coderhouse.proyectofinal.model.product;

public class FiguraDeAccion extends Producto {

    private String fabricante;
    private boolean esArticulado;

    //Constructor

    public FiguraDeAccion(int codigoDeProducto, String nombre, String descripcion, int stock,
                          float precio, String fabricante, boolean esArticulado) {
        super(codigoDeProducto, nombre, descripcion, stock, precio);
        this.fabricante = fabricante;
        this.esArticulado = esArticulado;
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

        return super.toString() + "\nFiguraDeAccion{" +
                "fabricante='" + fabricante + '\'' +
                ", esArticulado=" + esArticulado +
                '}';
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
