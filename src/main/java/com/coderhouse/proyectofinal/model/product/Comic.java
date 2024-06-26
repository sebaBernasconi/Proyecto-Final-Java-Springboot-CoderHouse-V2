package com.coderhouse.proyectofinal.model.product;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Schema(description = "Modelo de Comic")
@Entity
@Table(name = "comic")
public class Comic extends Producto{

    @Schema(description = "Autor del Comic",requiredMode = Schema.RequiredMode.REQUIRED, example = "Mark Weid")
    @Column(name = "autor")
    private String autor;

    @Schema(description = "Idioma del Comic",requiredMode = Schema.RequiredMode.REQUIRED, example = "Ingles")
    @Column(name = "idioma")
    private String idioma;

    @Schema(description = "Caracteristica del Comic",requiredMode = Schema.RequiredMode.REQUIRED, example = "true")
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
        if (this.getNombre() != nuevoNombre && nuevoNombre != " "){
            this.setNombre(nuevoNombre);
        }

        if (this.getDescripcion() != nuevaDescripcion && nuevaDescripcion != " "){
            this.setDescripcion(nuevaDescripcion);
        }

        if (this.getAutor() != nuevoAutor && nuevoAutor != " ") {
            this.setAutor(nuevoAutor);
        }

        if (this.getIdioma() != nuevoIdioma && nuevoIdioma != " ") {
            this.setIdioma(nuevoIdioma);
        }

        if (this.autor != nuevoAutor && nuevoAutor != " "){
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

    @Override
    public String toString() {
        return
                super.toString() +
                "\n autor='" + autor  +
                "\n idioma='" + idioma +
                "\n tapaDura=" + tapaDura;
    }
}
