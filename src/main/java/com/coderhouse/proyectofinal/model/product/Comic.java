package com.coderhouse.proyectofinal.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "comic")
public class Comic extends Producto{

    @Column(name = "autor")
    private String autor;
    @Column(name = "idioma")
    private String idioma;
    @Column(name = "tapa_dura")
    private boolean tapaDura;

    //Constructor

    public Comic( int codigoDeProducto, String nombre,
                 String descripcion, int stock, float precio, String autor, String idioma, boolean tapaDura) {
        super(codigoDeProducto, nombre, descripcion, stock, precio);
        this.autor = autor;
        this.idioma = idioma;
        this.tapaDura = tapaDura;
    }

    //Constructor vacio para la persistencia
    public Comic() {

    }

    //Metodos de la clase
    public void editarComic(String nuevoNombre ,String nuevaDescripcion,
                                     String nuevoAutor,String nuevoIdioma,
                            boolean nuevoTapaDura) {
        if (this.getNombre() != nuevoNombre && nuevoNombre != ""){
            this.setNombre(nuevoNombre);
        }

        if (this.getDescripcion() != nuevaDescripcion && nuevaDescripcion != ""){
            this.setDescripcion(nuevaDescripcion);
        }

        if (this.getAutor() != nuevoAutor && nuevoAutor != "") {
            this.setAutor(nuevoAutor);
        }

        if (this.getIdioma() != nuevoIdioma && nuevoIdioma != "") {
            this.setIdioma(nuevoIdioma);
        }

        if (this.autor != nuevoAutor && nuevoAutor != ""){
            this.autor = nuevoAutor;
        }

        if (this.tapaDura != nuevoTapaDura){
            this.tapaDura = nuevoTapaDura;
        }

    }

    //Getters y Setters

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public boolean isTapaDura() {
        return tapaDura;
    }

    public void setTapaDura(boolean tapaDura) {
        this.tapaDura = tapaDura;
    }
}
