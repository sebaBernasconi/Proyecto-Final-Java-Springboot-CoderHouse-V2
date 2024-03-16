package com.coderhouse.proyectofinal.controllers;

import com.coderhouse.proyectofinal.exception.ProductNotFoundException;
import com.coderhouse.proyectofinal.model.product.Comic;
import com.coderhouse.proyectofinal.model.product.FiguraDeAccion;

import java.util.ArrayList;
import java.util.List;

public class ControllerProducto {

    private int idFiguraDeAccion = 0;
    private int idComic = 0;

    private List<FiguraDeAccion>listadoDeFigurasDeAccion;
    private List<Comic> listadoDeComics;

    private static ControllerProducto intancia;

    //Constructor
    public ControllerProducto(){
        this.listadoDeFigurasDeAccion = new ArrayList<>();
        this.listadoDeComics = new ArrayList<>();
    }

    //getInstancia para que sea singleton
    public static ControllerProducto getIntancia(){
        if (intancia == null){
            return intancia = new ControllerProducto();
        }else {
            return intancia;
        }
    }

    //Metodos del controller

    public void registrarFiguraDeAccion(int codigoDeProducto, String nombre, String descripcion,
                                        int stock, float precio, String fabricante,
                                        boolean esArticulado) throws ProductNotFoundException {
        if (buscarFiguraDeAccion(codigoDeProducto) == null){
            FiguraDeAccion figuraDeAccion = new FiguraDeAccion( idFiguraDeAccion, codigoDeProducto,nombre,descripcion,
                    stock, precio,fabricante,esArticulado);
            listadoDeFigurasDeAccion.add(figuraDeAccion);
            this.idFiguraDeAccion ++;
        }else {
            System.out.println("Ya hay una figura de accion registrada " +
                    "con el codigo: " + codigoDeProducto);
        }
    }

    public void registrarComic(int codigoDeProducto, String nombre,
                               String descripcion, int stock, float precio,
                               String autor, String idioma, boolean tapaDura)
    throws ProductNotFoundException {
        if (buscarComic(codigoDeProducto) == null){
            Comic c = new Comic(idComic,codigoDeProducto,nombre,descripcion,
                    stock,precio,autor,idioma,tapaDura);
            listadoDeComics.add(c);
            this.idComic ++;
        }else {
            System.out.println("Ya hay un comic registrado con el" +
                    " codigo: " + codigoDeProducto);
        }
    }

    public void actualizarStockFiguraDeAccion(int codigoDeProducto)
            throws ProductNotFoundException {
        FiguraDeAccion f = buscarFiguraDeAccion(codigoDeProducto);
        f.actualizarStock();

    }

    public void actualizarStockComic(int codigoDeProducto)
            throws ProductNotFoundException {
        Comic c = buscarComic(codigoDeProducto);
        c.actualizarStock();
    }
    public void editarFiguraDeAccion(int codigoDeProducto,String nuevoNombre,String nuevaDescripcion,
                                     String nuevoFabricante, boolean nuevoEsArticulado)
            throws ProductNotFoundException {
        FiguraDeAccion f = buscarFiguraDeAccion(codigoDeProducto);
        f.editarFiguraDeAccion(nuevoNombre,nuevaDescripcion,nuevoFabricante,nuevoEsArticulado);
    }

    public void editarComic(int codigoDeProdcto,String nuevoNombre ,
                            String nuevaDescripcion, String nuevoAutor,
                            String nuevoIdioma, boolean nuevoTapaDura)
            throws ProductNotFoundException {
        Comic c = buscarComic(codigoDeProdcto);
        c.editarComic(nuevoNombre,nuevaDescripcion,
                nuevoAutor,nuevoIdioma,nuevoTapaDura);
    }

    public void modificarPrecioFiguraDeAccion(int codigoDeProducto,
                                              float nuevoPrecio)
            throws ProductNotFoundException {
        FiguraDeAccion f = buscarFiguraDeAccion(codigoDeProducto);
        f.modificarPrecio(nuevoPrecio);
    }

    public void modificarPrecioComic(int codigoDeProducto,
                                     float nuevoPrecio)
        throws ProductNotFoundException {
        Comic c = buscarComic(codigoDeProducto);
        c.modificarPrecio(nuevoPrecio);
    }

    //Getter de los productos
    //Mala practica(?
    public FiguraDeAccion getFiguraDeAccion(int codigoDeProducto) throws ProductNotFoundException {
        return buscarFiguraDeAccion(codigoDeProducto);
    }

    public Comic getComic(int codigoDeProducto) throws ProductNotFoundException {
        return buscarComic(codigoDeProducto);
    }

    //Metodos privados que devuelven objetos que el cliente nunca debe ver
    private FiguraDeAccion buscarFiguraDeAccion(int codigoDeProducto)
            throws ProductNotFoundException {
        for (FiguraDeAccion f:
                listadoDeFigurasDeAccion) {
            if (codigoDeProducto == f.getCodigoDeProducto()) {
                return f;
            }
        }
        throw new ProductNotFoundException("No hay ninguna figura de accion registrada " +
                "con el codigo: " + codigoDeProducto);
    }

    private Comic buscarComic(int codigoDeProducto)
            throws ProductNotFoundException {
        for (Comic c :
                listadoDeComics) {
            if (codigoDeProducto == c.getCodigoDeProducto()) {
                return c;
            }
        }
        throw new ProductNotFoundException("No hay ningun comic registrado con " +
                " el codigo: " + codigoDeProducto);


    }
}
