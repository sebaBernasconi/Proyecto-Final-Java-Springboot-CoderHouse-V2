package com.coderhouse.proyectofinal.controllers;

import com.coderhouse.proyectofinal.exception.ProductNotFoundException;
import com.coderhouse.proyectofinal.model.product.Comic;
import com.coderhouse.proyectofinal.model.product.FiguraDeAccion;
import com.coderhouse.proyectofinal.service.product.ComicService;
import com.coderhouse.proyectofinal.service.product.FiguraDeAccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/productos")
public class ControllerProducto {

    //Declarando los servicios que voy a necesitar

    @Autowired
    private ComicService comicService;

    @Autowired
    private FiguraDeAccionService figuraDeAccionService;

    //Creando instancia de controller
    private static ControllerProducto intancia;

    //Constructor
    public ControllerProducto(){

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

    @PostMapping(value = "/agregarFigura", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FiguraDeAccion> registrarFiguraDeAccion(@RequestBody FiguraDeAccion figuraDeAccion)  {

        figuraDeAccionService.guardarFiguraDeAccion(figuraDeAccion);
         return new ResponseEntity<>(figuraDeAccion, HttpStatus.CREATED);

    }

    @PostMapping(value = "/agregarComic", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Comic> registrarComic(@RequestBody Comic comic) {
        comicService.guardarComic(comic);
        return new ResponseEntity<>(comic,HttpStatus.CREATED);
    }


    @PutMapping(value = "/actualizarStockFigura/{id}")
    public ResponseEntity<FiguraDeAccion> actualizarStockFiguraDeAccion(@PathVariable("id") Integer codigoDeProducto) {
        try {
            FiguraDeAccion figuraDeAccion = figuraDeAccionService.actualizarStockPostVenta(codigoDeProducto);
            return new ResponseEntity<>(figuraDeAccion,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/actualizarStockComic/{id}")
    public ResponseEntity<Comic> actualizarStockComic(@PathVariable("id") Integer codigoDeProducto) {
        try {
            Comic comic = comicService.actualizarStockPostVenta(codigoDeProducto);
            return new ResponseEntity<>(comic,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/editarFigura/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FiguraDeAccion> editarFiguraDeAccion(@PathVariable("id") Integer codigoDeProducto) {
      //Consultar que onda
        return null;
    }

    public void editarComic(int codigoDeProdcto,String nuevoNombre ,
                            String nuevaDescripcion, String nuevoAutor,
                            String nuevoIdioma, boolean nuevoTapaDura)
            throws ProductNotFoundException {
       //Consultar que onda
    }


    @PutMapping(value = "/actualizarPrecioFigura/{id}/{precio}")
    public ResponseEntity<FiguraDeAccion> modificarPrecioFiguraDeAccion(@PathVariable("id")
                                                                            Integer codigoDeProducto,
                                                                       @PathVariable("precio") float nuevoPrecio) {
        try {
            FiguraDeAccion figuraDeAccion = figuraDeAccionService.modificarPrecio(codigoDeProducto,nuevoPrecio);
            return new ResponseEntity<>(figuraDeAccion,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping(value = "/actualizarPrecioComic/{id}/{precio}")
    public ResponseEntity<Comic> modificarPrecioComic(@PathVariable("id") Integer codigoDeProducto,
                                     @PathVariable("precio") float nuevoPrecio){
        try {
            Comic comic = comicService.modificarPrecio(codigoDeProducto,nuevoPrecio);
            return new ResponseEntity<>(comic,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping(value = "/eliminarComic/{id}")
    public ResponseEntity<Void>eliminarComic(@PathVariable("id") Integer codigo){
        boolean comicEliminado = comicService.eliminarComic(codigo);
        if (comicEliminado){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/eliminarFigura/{id}")
    public ResponseEntity<Void>eliminarFiguraDeAccion(@PathVariable("id") Integer codigo){
        boolean figuraEliminada = figuraDeAccionService.eliminarFiguraDeAccion(codigo);

        if (figuraEliminada){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Getter de los productos
    //Mala practica(?
    @GetMapping(value = "/buscarFigura/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<FiguraDeAccion> obtenerFiguraPorCodigo(@PathVariable("id") Integer codigoDeProducto) {
        try {
            FiguraDeAccion figuraDeAccion = figuraDeAccionService.buscarFiguraPorCodigo(codigoDeProducto);

            if (figuraDeAccion != null){
                return new ResponseEntity<>(figuraDeAccion,HttpStatus.OK);
            }else {
                return new ResponseEntity<>(figuraDeAccion,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/buscarComic/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Comic> obtenerComicPorCodigo(@PathVariable("id") Integer codigoDeProducto) {
        try {
            Comic comic = comicService.buscarComicPorCodigo(codigoDeProducto);
            if (comic != null){
                return new ResponseEntity<>(comic,HttpStatus.OK);
            }else {
                return new ResponseEntity<>(comic,HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Metodos privados que devuelven objetos que el cliente nunca debe ver
    @GetMapping(value = "/listarFiguras", produces = {MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity<List<FiguraDeAccion>> obtenerFigurasDeAccion() {
        try {
            List<FiguraDeAccion> listadoFiguras = figuraDeAccionService.listarFigurasDeAccion();
            return new ResponseEntity<>(listadoFiguras,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/listarComics", produces = {MediaType.APPLICATION_JSON_VALUE})
    private ResponseEntity<List<Comic>> obtenerComics() {
      try {
          List<Comic> listadoComics = comicService.listarComics();
          return new ResponseEntity<>(listadoComics,HttpStatus.OK);
      }catch (Exception e){
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }


    }
}
