package com.coderhouse.proyectofinal.controllers;

import com.coderhouse.proyectofinal.exception.CarritoNotFoundException;
import com.coderhouse.proyectofinal.exception.UserNotFoundException;
import com.coderhouse.proyectofinal.model.product.Comic;
import com.coderhouse.proyectofinal.model.product.FiguraDeAccion;
import com.coderhouse.proyectofinal.model.product.Producto;
import com.coderhouse.proyectofinal.model.user.Carrito;
import com.coderhouse.proyectofinal.model.user.Client;
import com.coderhouse.proyectofinal.service.user.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/carrito")
public class ControllerCarrito {

    @Autowired
    private CarritoService carritoService;

    public static ControllerCarrito instancia;

    //Constructor
    public ControllerCarrito(){

    }

    //Metodo get instancia para que sea un singleton
    public static ControllerCarrito getInstancia(){
        if (instancia == null){
            return instancia = new ControllerCarrito();
        }else {
            return instancia;
        }
    }

    //Metodos del controller
    @PostMapping(value = "/agregar", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Carrito>agregarCarrito(@RequestBody Carrito carrito){

        carritoService.guardarCarrito(carrito);
        return new ResponseEntity<>(carrito, HttpStatus.CREATED);
    }

    @PutMapping(value = "/pagar/{id}")
    public ResponseEntity<Carrito>pagarCarrito(@PathVariable("id") Integer codigo){
        try {
            Carrito carritoPagado = carritoService.pagarCarrito(codigo);
            return new ResponseEntity<>(carritoPagado,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/agregarComic/{id}", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Carrito>ageregarComic(@PathVariable("id")Integer codigoCarrito,
                                                @RequestBody Comic comic){
        try {
            Carrito carrito = carritoService.agregarComic(codigoCarrito,comic);
            return new ResponseEntity<>(carrito,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/agregarFigura/{id}" ,consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Carrito>agregarFiguraDeAccion(@PathVariable("id")Integer codigoCarrito,
                                                        @RequestBody FiguraDeAccion figuraDeAccion){
        try {
            Carrito carrito = carritoService.agregarFiguraDeAccion(codigoCarrito,figuraDeAccion);
            return new ResponseEntity<>(carrito,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/eliminar/{id}")
    public ResponseEntity<Void>eliminarCarrito(@PathVariable("id") Integer codigo){
        boolean carritoEliminado = carritoService.borrarCarrito(codigo);

        if (carritoEliminado){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listar",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Carrito>>listarCarritos(){
        try {
            List<Carrito>listadoDeCarritos = carritoService.listarCarritos();
            return new ResponseEntity<>(listadoDeCarritos,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/listarProductos/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Producto>>listarProductos(@PathVariable("id")Integer id){
        try {
            List<Producto>productosDelCarrito = carritoService.verProductosDelCarrito(id);
            return new ResponseEntity<>(productosDelCarrito,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
