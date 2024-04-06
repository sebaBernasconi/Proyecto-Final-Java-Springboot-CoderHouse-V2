package com.coderhouse.proyectofinal.controllers;

import com.coderhouse.proyectofinal.model.transactions.Compra;
import com.coderhouse.proyectofinal.model.transactions.Venta;
import com.coderhouse.proyectofinal.service.transactions.CompraService;
import com.coderhouse.proyectofinal.service.transactions.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/transaction")
public class ControllerTransaccion {

    //Instanciando los servicios
    @Autowired
    CompraService compraService;

    @Autowired
    VentaService ventaService;

    //Instanciando el controller
    private static ControllerTransaccion instancia;

    //getInstance para que sea singleton
    public ControllerTransaccion getInstancia(){
        if (instancia == null) {
            return instancia = new ControllerTransaccion();
        }else {
            return instancia;
        }
    }

    //Metodos de la clase

    @PostMapping(value = "/guardarCompra", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Compra>guardarCompra(@RequestBody Compra compra){
        compraService.guardarCompra(compra);
        return new ResponseEntity<>(compra, HttpStatus.CREATED);
    }

    @PostMapping(value = "/guardarVenta", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Venta>guardarVenta(@RequestBody Venta venta){
        ventaService.guardarVenta(venta);
        return new ResponseEntity<>(venta,HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/eliminarCompra/{id}")
    public ResponseEntity<Void>eliminarCompra(@PathVariable("id")Integer idCompra){
        boolean compraEliminada = compraService.eliminarCompra(idCompra);

        if (compraEliminada) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/eliminarVenta/{id}")
    public ResponseEntity<Void>elimianrVenta(@PathVariable("id")Integer idVenta){
        boolean ventaEliminada = ventaService.eliminarVenta(idVenta);

        if (ventaEliminada) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/listarCompras", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Compra>>listarCompras(){
        try {
            List<Compra>listadoCompras = compraService.listarCompras();
            return new ResponseEntity<>(listadoCompras,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/listarCompras/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Compra>>listarComprasCliente(@PathVariable("id")Integer cuil){
        try {
            List<Compra>comprasCliente = compraService.ComprasDeUnCliente(cuil);
            return new ResponseEntity<>(comprasCliente,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/listarVentas",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Venta>>listarVentas(){
        try {
            List<Venta>listadoVentas = ventaService.listarVentas();
            return new ResponseEntity<>(listadoVentas,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/listarVentas/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Venta>>listarVentasAdmin(@PathVariable("id") Integer cuil){
        try {
            List<Venta>ventasAdmin = ventaService.VentasDeUnAdmin(cuil);
            return new ResponseEntity<>(ventasAdmin,HttpStatus.OK);
        }catch (Exception e){{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }}
    }

}

