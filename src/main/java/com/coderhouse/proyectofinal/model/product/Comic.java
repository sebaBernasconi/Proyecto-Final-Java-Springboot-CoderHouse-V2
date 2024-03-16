package com.coderhouse.proyectofinal.model.product;

public class Comic extends Producto{

    private String autor;
    private String idioma;
    private boolean tapaDura;

    //Constructor

    public Comic(int idProd, int codigoDeProducto, String nombre,
                 String descripcion, int stock, float precio, String autor, String idioma, boolean tapaDura) {
        super(idProd, codigoDeProducto, nombre, descripcion, stock, precio);
        this.autor = autor;
        this.idioma = idioma;
        this.tapaDura = tapaDura;
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
